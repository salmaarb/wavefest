package fr.wavefest.acces.application;

/** Port : mémoire des messages déjà traités (clé d'idempotence = id du message). */
public interface MessagesTraites {

    /** @return vrai si le message n'avait jamais été traité (et le marque comme traité). */
    boolean marquerSiNouveau(String messageId);
}
