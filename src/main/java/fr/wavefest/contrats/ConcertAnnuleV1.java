package fr.wavefest.contrats;

/** Événement d'intégration publié par le contexte Programmation. */
public record ConcertAnnuleV1(String eventId, String artiste, String jour, String survenuLe) {
}
