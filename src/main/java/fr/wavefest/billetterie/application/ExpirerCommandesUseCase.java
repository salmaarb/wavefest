package fr.wavefest.billetterie.application;

import fr.wavefest.billetterie.application.ports.Commandes;
import fr.wavefest.billetterie.application.ports.GrillesTarifaires;
import fr.wavefest.billetterie.application.ports.Horloge;
import fr.wavefest.billetterie.application.ports.PublicateurEvenements;
import fr.wavefest.billetterie.domaine.commande.Commande;
import fr.wavefest.billetterie.domaine.commande.LigneCommande;
import fr.wavefest.billetterie.domaine.offre.GrilleTarifaire;

import java.time.Instant;
import java.util.List;

/**
 * R4 : exécuté périodiquement (toutes les minutes en production).
 * Fait expirer les commandes non payées sous 15 minutes et rend leurs places aux quotas.
 */
public class ExpirerCommandesUseCase {

    private final Commandes commandes;
    private final GrillesTarifaires grilles;
    private final PublicateurEvenements publicateur;
    private final Horloge horloge;

    public ExpirerCommandesUseCase(Commandes commandes, GrillesTarifaires grilles,
                                   PublicateurEvenements publicateur, Horloge horloge) {
        this.commandes = commandes;
        this.grilles = grilles;
        this.publicateur = publicateur;
        this.horloge = horloge;
    }

    /** @return le nombre de commandes expirées. */
    public int executer() {
        // TODO TP3 R4 : faire expirer les commandes concernées et libérer leurs places
        throw new UnsupportedOperationException("TODO TP3 R4");
    }
}
