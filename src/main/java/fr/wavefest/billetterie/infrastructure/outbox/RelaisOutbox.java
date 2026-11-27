package fr.wavefest.billetterie.infrastructure.outbox;

import fr.wavefest.billetterie.application.ports.Horloge;
import fr.wavefest.plateforme.messagerie.Broker;
import fr.wavefest.plateforme.messagerie.BrokerIndisponibleException;
import fr.wavefest.plateforme.messagerie.Message;

/**
 * TP4 : le relais lit l'outbox et publie sur le broker (en production : toutes les secondes).
 * Garantie « au moins une fois » : si le broker tombe, le message reste en attente et sera
 * republié au prochain passage. Les consommateurs doivent donc être idempotents.
 */
public class RelaisOutbox {

    private final Outbox outbox;
    private final Broker broker;
    private final Horloge horloge;

    public RelaisOutbox(Outbox outbox, Broker broker, Horloge horloge) {
        this.outbox = outbox;
        this.broker = broker;
        this.horloge = horloge;
    }

    /** @return le nombre de messages publiés lors de ce passage. */
    public int publierEnAttente() {
        // TODO TP4 : publier chaque message en attente puis le marquer publié ; s'arrêter si le broker est indisponible
        throw new UnsupportedOperationException("TODO TP4");
    }
}
