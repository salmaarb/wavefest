package fr.wavefest.billetterie.application.ports;

import java.time.Instant;

/** Port : le temps est une dépendance comme une autre (indispensable pour tester R4 et R5). */
public interface Horloge {
    Instant maintenant();
}
