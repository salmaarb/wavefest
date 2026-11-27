package fr.wavefest.billetterie.infrastructure.evenements;

import fr.wavefest.billetterie.application.ports.PublicateurEvenements;
import fr.wavefest.billetterie.domaine.commun.DomainEvent;

import java.util.ArrayList;
import java.util.List;

/** TP3 : garde les événements en mémoire (pratique pour les tests). */
public class PublicateurEvenementsEnMemoire implements PublicateurEvenements {

    private final List<DomainEvent> publies = new ArrayList<>();

    @Override
    public void publier(List<DomainEvent> evenements) {
        publies.addAll(evenements);
    }

    public List<DomainEvent> publies() {
        return List.copyOf(publies);
    }
}
