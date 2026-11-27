package fr.wavefest.billetterie.infrastructure.outbox;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/** La table outbox, en mémoire. */
public class Outbox {

    private final List<MessageOutbox> messages = new ArrayList<>();

    public synchronized void ajouter(MessageOutbox message) {
        messages.add(message);
    }

    /** Messages non encore publiés, dans l'ordre d'écriture. */
    public synchronized List<MessageOutbox> enAttente() {
        return messages.stream().filter(m -> !m.estPublie()).toList();
    }

    public synchronized void marquerPublie(MessageOutbox message, Instant instant) {
        message.marquerPublie(instant);
    }

    public synchronized List<MessageOutbox> tous() {
        return List.copyOf(messages);
    }
}
