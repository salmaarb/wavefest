package fr.wavefest.plateforme.messagerie;

/** Une enveloppe sur le broker. L'id sert de clé d'idempotence aux consommateurs. */
public record Message(String id, String topic, Object contenu) {
}
