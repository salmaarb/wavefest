package fr.wavefest.billetterie.application.ports;

import fr.wavefest.billetterie.domaine.commun.CommandeId;

/** Résultat d'un paiement, dans NOTRE langage (et non celui du PSP). */
public sealed interface ResultatPaiement {

    CommandeId commande();

    record PaiementConfirme(CommandeId commande, String referenceExterne) implements ResultatPaiement {
    }

    record PaiementRefuse(CommandeId commande, String motif) implements ResultatPaiement {
    }
}
