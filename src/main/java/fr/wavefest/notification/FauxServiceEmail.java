package fr.wavefest.notification;

import java.util.ArrayList;
import java.util.List;

/** Generic subdomain : en vrai, un prestataire d'e-mails. Ici on garde les envois en mémoire. */
public class FauxServiceEmail implements ServiceEmail {

    public record EmailEnvoye(String destinataire, String sujet, String corps) {
    }

    private final List<EmailEnvoye> envoyes = new ArrayList<>();

    @Override
    public synchronized void envoyer(String destinataire, String sujet, String corps) {
        envoyes.add(new EmailEnvoye(destinataire, sujet, corps));
    }

    public synchronized List<EmailEnvoye> envoyes() {
        return List.copyOf(envoyes);
    }
}
