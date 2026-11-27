package fr.wavefest.billetterie.infrastructure.horloge;

import fr.wavefest.billetterie.application.ports.Horloge;

import java.time.Instant;

public class HorlogeSysteme implements Horloge {
    @Override
    public Instant maintenant() {
        return Instant.now();
    }
}
