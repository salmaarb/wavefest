package fr.wavefest.billetterie.infrastructure.messagerie;

import fr.wavefest.billetterie.application.annulation.SagaAnnulationConcert;
import fr.wavefest.contrats.ConcertAnnuleV1;
import fr.wavefest.plateforme.messagerie.Message;

import java.util.function.Consumer;

/** Branche la saga de la Billetterie sur le topic publié par la Programmation. */
public class ConsommateurConcertAnnule implements Consumer<Message> {

    private final SagaAnnulationConcert saga;

    public ConsommateurConcertAnnule(SagaAnnulationConcert saga) {
        this.saga = saga;
    }

    @Override
    public void accept(Message message) {
        saga.quandConcertAnnule((ConcertAnnuleV1) message.contenu());
    }
}
