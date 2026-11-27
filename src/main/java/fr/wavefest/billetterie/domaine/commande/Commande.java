package fr.wavefest.billetterie.domaine.commande;

import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.Email;
import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;
import fr.wavefest.billetterie.domaine.commun.FestivalierId;
import fr.wavefest.billetterie.domaine.commun.Montant;
import fr.wavefest.billetterie.domaine.commun.RacineAgregat;
import fr.wavefest.billetterie.domaine.commun.Titulaire;
import fr.wavefest.billetterie.domaine.offre.Formule;
import fr.wavefest.billetterie.domaine.offre.Tranche;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Racine d'agrégat. Invariants protégés :
 *  - R1 : 6 billets maximum par commande ;
 *  - R4 : une commande non payée sous 15 minutes expire (ses places sont libérées) ;
 *  - aucune modification après paiement ou expiration ;
 *  - le total est toujours la somme des lignes.
 */
public class Commande extends RacineAgregat {

    public static final int MAX_BILLETS = 6;
    public static final Duration DELAI_DE_PAIEMENT = Duration.ofMinutes(15);

    private final CommandeId id;
    private final FestivalierId acheteur;
    private final Email contact;
    private final Titulaire titulaire;
    private final Instant ouverteLe;
    private final List<LigneCommande> lignes = new ArrayList<>();
    private final List<BilletAttribue> billets = new ArrayList<>();
    private StatutCommande statut = StatutCommande.EN_COURS;

    private Commande(CommandeId id, FestivalierId acheteur, Email contact, Titulaire titulaire, Instant ouverteLe) {
        this.id = Objects.requireNonNull(id);
        this.acheteur = Objects.requireNonNull(acheteur);
        this.contact = Objects.requireNonNull(contact);
        this.titulaire = Objects.requireNonNull(titulaire);
        this.ouverteLe = Objects.requireNonNull(ouverteLe);
    }

    public static Commande ouvrir(CommandeId id, FestivalierId acheteur, Email contact, Titulaire titulaire, Instant maintenant) {
        return new Commande(id, acheteur, contact, titulaire, maintenant);
    }

    /** Ajoute en une seule fois toutes les tranches d'une réservation (tout ou rien). */
    public void ajouterBillets(Formule formule, List<Tranche> tranches) {
        exigerStatut(StatutCommande.EN_COURS);
        int demandes = tranches.stream().mapToInt(t -> t.quantite().valeur()).sum();
        // TODO TP3 R1 : refuser l'ajout si la commande dépasse 6 billets (LimiteDeBilletsDepasseeException)
        tranches.forEach(t -> lignes.add(new LigneCommande(formule, t.quantite(), t.prixUnitaire(), t.vague())));
    }

    public int nombreDeBillets() {
        return lignes.stream().mapToInt(l -> l.quantite().valeur()).sum();
    }

    public Montant total() {
        return lignes.stream().map(LigneCommande::sousTotal).reduce(Montant.ZERO_EURO, Montant::ajouter);
    }

    /** R4 : vrai si la commande est encore en cours alors que le délai de paiement est écoulé. */
    public boolean estExpireeA(Instant maintenant) {
        // TODO TP3 R4 : en cours ET 15 minutes écoulées depuis l'ouverture
        throw new UnsupportedOperationException("TODO TP3 R4");
    }

    /** À appeler AVANT de solliciter le PSP : on ne fait pas payer une commande expirée ou vide. */
    public void verifierPayableA(Instant maintenant) {
        exigerStatut(StatutCommande.EN_COURS);
        // TODO TP3 R4 : refuser le paiement d'une commande expirée (CommandeExpireeException)
        if (lignes.isEmpty()) {
            throw new ExceptionMetier("Impossible de payer une commande vide");
        }
    }

    /** Le paiement est confirmé : on attribue un billet par unité achetée et on publie CommandePayee. */
    public void confirmerPaiement(Instant maintenant) {
        verifierPayableA(maintenant);
        for (LigneCommande ligne : lignes) {
            for (int i = 0; i < ligne.quantite().valeur(); i++) {
                billets.add(new BilletAttribue(BilletId.nouveau(), ligne.formule(), ligne.prixUnitaire()));
            }
        }
        statut = StatutCommande.PAYEE;
        enregistrer(new CommandePayee(id, acheteur, contact, titulaire, total(), List.copyOf(billets), maintenant));
    }

    /** R4 : la commande expire ; l'événement porte les lignes pour que l'on puisse libérer les quotas. */
    public void expirer(Instant maintenant) {
        // TODO TP3 R4 : vérifier l'expiration, passer au statut EXPIREE, enregistrer CommandeExpiree
    }

    private void exigerStatut(StatutCommande attendu) {
        if (statut != attendu) {
            throw new CommandeNonModifiableException(id, statut);
        }
    }

    public CommandeId id() {
        return id;
    }

    public FestivalierId acheteur() {
        return acheteur;
    }

    public Email contact() {
        return contact;
    }

    public Titulaire titulaire() {
        return titulaire;
    }

    public Instant ouverteLe() {
        return ouverteLe;
    }

    public StatutCommande statut() {
        return statut;
    }

    /** Vue non modifiable : impossible de contourner les règles depuis l'extérieur. */
    public List<LigneCommande> lignes() {
        return List.copyOf(lignes);
    }

    public List<BilletAttribue> billetsAttribues() {
        return List.copyOf(billets);
    }
}
