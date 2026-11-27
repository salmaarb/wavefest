package fr.wavefest.notification;

public interface ServiceEmail {
    void envoyer(String destinataire, String sujet, String corps);
}
