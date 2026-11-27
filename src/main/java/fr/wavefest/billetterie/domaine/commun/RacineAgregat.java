package fr.wavefest.billetterie.domaine.commun;

import java.util.ArrayList;
import java.util.List;

/** Classe de base des racines d'agrégat : elles enregistrent leurs événements du domaine. */
public abstract class RacineAgregat {

    private final List<DomainEvent> evenements = new ArrayList<>();

    protected void enregistrer(DomainEvent evenement) {
        evenements.add(evenement);
    }

    /** Événements enregistrés depuis le dernier extraireEvenements(). */
    public List<DomainEvent> evenements() {
        return List.copyOf(evenements);
    }

    /** Renvoie les événements et vide la liste (appelé par l'application service). */
    public List<DomainEvent> extraireEvenements() {
        List<DomainEvent> copie = List.copyOf(evenements);
        evenements.clear();
        return copie;
    }
}
