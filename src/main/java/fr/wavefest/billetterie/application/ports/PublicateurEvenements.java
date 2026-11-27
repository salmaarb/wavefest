package fr.wavefest.billetterie.application.ports;

import fr.wavefest.billetterie.domaine.commun.DomainEvent;

import java.util.List;

/**
 * Port : que faire des événements du domaine une fois l'agrégat enregistré ?
 * TP3 : une implémentation en mémoire suffit. TP4 : on les écrit dans l'outbox.
 */
public interface PublicateurEvenements {
    void publier(List<DomainEvent> evenements);
}
