package fr.wavefest.billetterie.domaine.commun;

import java.time.Instant;

/** Un fait métier passé, immuable, nommé au passé. */
public interface DomainEvent {
    Instant survenuLe();
}
