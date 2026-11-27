package fr.wavefest.billetterie.domaine.billet;

import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;
import fr.wavefest.billetterie.domaine.commun.Montant;
import fr.wavefest.billetterie.domaine.commun.RacineAgregat;
import fr.wavefest.billetterie.domaine.commun.Titulaire;
import fr.wavefest.billetterie.domaine.offre.Formule;
import fr.wavefest.billetterie.domaine.offre.JourFestival;
import fr.wavefest.billetterie.domaine.offre.TypePass;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

/**
 * Racine d'agrégat : un billet nominatif.
 *  - R5 : changement de titulaire gratuit jusqu'à J-7, refusé ensuite ;
 *  - R6 : revente au plus au prix d'achat, commission de 5 % ;
 *  - (TP4) un billet n'est remboursé qu'une seule fois.
 */
public class Billet extends RacineAgregat {

    private final BilletId id;
    private final CommandeId commande;
    private final Formule formule;
    private Titulaire titulaire;
    private Montant prixPaye;
    private StatutBillet statut = StatutBillet.VALIDE;
    private OffreDeRevente offreEnCours;
    private Montant montantRembourse;

    private Billet(BilletId id, CommandeId commande, Formule formule, Titulaire titulaire, Montant prixPaye) {
        this.id = Objects.requireNonNull(id);
        this.commande = Objects.requireNonNull(commande);
        this.formule = Objects.requireNonNull(formule);
        this.titulaire = Objects.requireNonNull(titulaire);
        this.prixPaye = Objects.requireNonNull(prixPaye);
    }

    public static Billet emettre(BilletId id, CommandeId commande, Formule formule, Titulaire titulaire, Montant prixPaye) {
        return new Billet(id, commande, formule, titulaire, prixPaye);
    }

    /** R5 : changement de nom gratuit jusqu'à J-7 (réponse de l'expert métier : refusé ensuite). */
    public void changerTitulaire(Titulaire nouveau, Instant maintenant, DatesFestival dates) {
        exigerStatut(StatutBillet.VALIDE);
        // TODO TP3 R5 : refuser le changement à partir de J-7 (ChangementDeNomHorsDelaiException)
        this.titulaire = Objects.requireNonNull(nouveau);
    }

    /** R6 : mise en revente sur la bourse officielle, prix plafonné au prix d'achat. */
    public OffreDeRevente mettreEnRevente(Montant prixDemande) {
        exigerStatut(StatutBillet.VALIDE);
        // TODO TP3 R6 : refuser un prix supérieur au prix payé, créer l'offre, passer EN_REVENTE
        throw new UnsupportedOperationException("TODO TP3 R6");
    }

    /** Un acquéreur a payé : le billet change de titulaire et de prix de référence. */
    public void conclureRevente(Titulaire acquereur, Instant maintenant) {
        exigerStatut(StatutBillet.EN_REVENTE);
        Titulaire ancien = titulaire;
        titulaire = Objects.requireNonNull(acquereur);
        prixPaye = offreEnCours.prix();
        statut = StatutBillet.VALIDE;
        enregistrer(new BilletRevendu(id, ancien, acquereur, offreEnCours, maintenant));
        offreEnCours = null;
    }

    public void retirerDeLaRevente() {
        exigerStatut(StatutBillet.EN_REVENTE);
        offreEnCours = null;
        statut = StatutBillet.VALIDE;
    }

    /** Vrai pour un pass 1 jour du jour donné (utilisé par la saga d'annulation, TP4). */
    public boolean estUnPass1JourPour(JourFestival jour) {
        return formule.type() == TypePass.PASS_1_JOUR && formule.jour() == jour;
    }

    /** TP4 : remboursement partiel suite à l'annulation d'une tête d'affiche. Une seule fois. */
    public void rembourserPartiellement(Montant montant) {
        // TODO TP4 : refuser un second remboursement, puis mémoriser le montant remboursé
    }

    private void exigerStatut(StatutBillet attendu) {
        if (statut != attendu) {
            throw new ExceptionMetier("Le billet " + id + " est " + statut + " : opération impossible");
        }
    }

    public BilletId id() {
        return id;
    }

    public CommandeId commande() {
        return commande;
    }

    public Formule formule() {
        return formule;
    }

    public Titulaire titulaire() {
        return titulaire;
    }

    public Montant prixPaye() {
        return prixPaye;
    }

    public StatutBillet statut() {
        return statut;
    }

    public Optional<OffreDeRevente> offreEnCours() {
        return Optional.ofNullable(offreEnCours);
    }

    public Optional<Montant> montantRembourse() {
        return Optional.ofNullable(montantRembourse);
    }
}
