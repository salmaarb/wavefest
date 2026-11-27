package fr.wavefest.billetterie.domaine.billet;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/** Value Object : début du festival (ouverture des portes le premier jour). */
public record DatesFestival(Instant ouverture) {

    public DatesFestival {
        Objects.requireNonNull(ouverture);
    }

    /** R5 : date limite du changement de nom gratuit (J-7). */
    public Instant limiteChangementDeNom() {
        return ouverture.minus(Duration.ofDays(7));
    }
}
