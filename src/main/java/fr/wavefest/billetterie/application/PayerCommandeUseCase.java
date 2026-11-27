package fr.wavefest.billetterie.application;

import fr.wavefest.billetterie.application.ports.Billets;
import fr.wavefest.billetterie.application.ports.Commandes;
import fr.wavefest.billetterie.application.ports.Horloge;
import fr.wavefest.billetterie.application.ports.PortPaiement;
import fr.wavefest.billetterie.application.ports.PublicateurEvenements;
import fr.wavefest.billetterie.application.ports.ResultatPaiement;
import fr.wavefest.billetterie.domaine.billet.Billet;
import fr.wavefest.billetterie.domaine.commande.BilletAttribue;
import fr.wavefest.billetterie.domaine.commande.Commande;
import fr.wavefest.billetterie.domaine.commun.CommandeId;

import java.time.Instant;

/**
 * Payer une commande :
 *  1. charger la commande et vérifier qu'elle est payable (R4) AVANT d'appeler le PSP ;
 *  2. payer via le PortPaiement ;
 *  3. si le paiement est confirmé : confirmer, émettre un Billet par billet attribué,
 *     enregistrer, puis publier les événements du domaine.
 */
public class PayerCommandeUseCase {

    private final Commandes commandes;
    private final Billets billets;
    private final PortPaiement paiement;
    private final PublicateurEvenements publicateur;
    private final Horloge horloge;

    public PayerCommandeUseCase(Commandes commandes, Billets billets, PortPaiement paiement,
                                PublicateurEvenements publicateur, Horloge horloge) {
        this.commandes = commandes;
        this.billets = billets;
        this.paiement = paiement;
        this.publicateur = publicateur;
        this.horloge = horloge;
    }

    public ResultatPaiement executer(CommandeId id) {
        // TODO TP3 : implémenter le cas d'utilisation (voir la Javadoc de la classe)
        throw new UnsupportedOperationException("TODO TP3");
    }
}
