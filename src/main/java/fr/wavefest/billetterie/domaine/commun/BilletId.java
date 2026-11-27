package fr.wavefest.billetterie.domaine.commun;

import java.util.Objects;
import java.util.UUID;

/** Identité typée d'un billet. */
public record BilletId(String valeur) {

    public BilletId {
        Objects.requireNonNull(valeur, "valeur");
    }

    public static BilletId nouveau() {
        return new BilletId(UUID.randomUUID().toString());
    }

    public static BilletId de(String valeur) {
        return new BilletId(valeur);
    }

    @Override
    public String toString() {
        return valeur;
    }
}
