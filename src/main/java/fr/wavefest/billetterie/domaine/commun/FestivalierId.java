package fr.wavefest.billetterie.domaine.commun;

import java.util.Objects;
import java.util.UUID;

/** Identité typée d'un festivalier (le compte client). Référence par identité entre agrégats. */
public record FestivalierId(String valeur) {

    public FestivalierId {
        Objects.requireNonNull(valeur, "valeur");
    }

    public static FestivalierId nouveau() {
        return new FestivalierId(UUID.randomUUID().toString());
    }

    public static FestivalierId de(String valeur) {
        return new FestivalierId(valeur);
    }

    @Override
    public String toString() {
        return valeur;
    }
}
