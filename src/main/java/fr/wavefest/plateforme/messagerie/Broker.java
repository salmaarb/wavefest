package fr.wavefest.plateforme.messagerie;

import java.util.function.Consumer;

/** Port technique du broker de messages (Kafka, RabbitMQ... simulé en mémoire ici). */
public interface Broker {

    void publier(Message message);

    void abonner(String topic, Consumer<Message> consommateur);
}
