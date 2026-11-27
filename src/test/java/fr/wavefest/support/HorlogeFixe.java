package fr.wavefest.support;

import fr.wavefest.billetterie.application.ports.Horloge;

import java.time.Duration;
import java.time.Instant;

/** Horloge de test : le temps n'avance que quand on le décide. */
public class HorlogeFixe implements Horloge {

    private Instant maintenant;

    public HorlogeFixe(Instant depart) {
        this.maintenant = depart;
    }

    public static HorlogeFixe au(String iso) {
        return new HorlogeFixe(Instant.parse(iso));
    }

    @Override
    public Instant maintenant() {
        return maintenant;
    }

    public void avancer(Duration duree) {
        maintenant = maintenant.plus(duree);
    }

    public void allerAu(String iso) {
        maintenant = Instant.parse(iso);
    }
}
