package fr.wavefest.billetterie.application;

import fr.wavefest.billetterie.application.ports.Commandes;
import fr.wavefest.billetterie.application.ports.GrillesTarifaires;
import fr.wavefest.billetterie.application.ports.Horloge;
import fr.wavefest.billetterie.domaine.commande.Commande;
import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;
import fr.wavefest.billetterie.domaine.commun.Quantite;
import fr.wavefest.billetterie.domaine.offre.Formule;
import fr.wavefest.billetterie.domaine.offre.GrilleTarifaire;
import fr.wavefest.billetterie.domaine.offre.Tranche;

import java.time.Instant;
import java.util.List;

/**
 * Application service (EXEMPLE COMPLET) : aucun « if » métier ici.
 * Il charge, délègue au domaine, enregistre.
 */
public class AcheterPassUseCase {

    private final Commandes commandes;
    private final GrillesTarifaires grilles;
    private final Horloge horloge;

    public AcheterPassUseCase(Commandes commandes, GrillesTarifaires grilles, Horloge horloge) {
        this.commandes = commandes;
        this.grilles = grilles;
        this.horloge = horloge;
    }

    /** Ouvre une commande et y place les premiers billets. */
    public CommandeId executer(AcheterPass demande) {
        Instant maintenant = horloge.maintenant();
        Commande commande = Commande.ouvrir(CommandeId.nouveau(), demande.acheteur(), demande.contact(), demande.titulaire(), maintenant);
        ajouter(commande, demande.formule(), demande.quantite(), maintenant);
        return commande.id();
    }

    /** Ajoute des billets à une commande existante. */
    public void ajouterBillets(CommandeId id, Formule formule, Quantite quantite) {
        Commande commande = commandes.parId(id).orElseThrow(() -> new CommandeIntrouvableException(id));
        ajouter(commande, formule, quantite, horloge.maintenant());
    }

    private void ajouter(Commande commande, Formule formule, Quantite quantite, Instant maintenant) {
        GrilleTarifaire grille = grilles.courante();
        List<Tranche> tranches = grille.reserver(formule.type(), quantite, maintenant);
        try {
            commande.ajouterBillets(formule, tranches);
        } catch (ExceptionMetier refus) {
            grille.liberer(tranches); // on rend les places réservées
            throw refus;
        }
        // Compromis assumé (cf. ADR) : deux agrégats modifiés dans le même cas d'utilisation.
        grilles.enregistrer(grille);
        commandes.enregistrer(commande);
    }
}
