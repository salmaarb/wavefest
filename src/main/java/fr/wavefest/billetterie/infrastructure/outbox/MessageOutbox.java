package fr.wavefest.billetterie.infrastructure.outbox;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

/** Une ligne de la table outbox : écrite dans la MÊME transaction que l'agrégat. */
public class MessageOutbox {

    private final String id;
    private final String topic;
    private final Object contenu;
    private final Instant creeLe;
    private Instant publieLe;

    public MessageOutbox(String id, String topic, Object contenu, Instant creeLe) {
        this.id = Objects.requireNonNull(id);
        this.topic = Objects.requireNonNull(topic);
        this.contenu = Objects.requireNonNull(contenu);
        this.creeLe = Objects.requireNonNull(creeLe);
    }

    void marquerPublie(Instant instant) {
        this.publieLe = instant;
    }

    public boolean estPublie() {
        return publieLe != null;
    }

    public String id() {
        return id;
    }

    public String topic() {
        return topic;
    }

    public Object contenu() {
        return contenu;
    }

    public Instant creeLe() {
        return creeLe;
    }

    public Optional<Instant> publieLe() {
        return Optional.ofNullable(publieLe);
    }
}
