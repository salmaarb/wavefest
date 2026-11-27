package fr.wavefest.billetterie.domaine.commun;

import java.util.Objects;
import java.util.UUID;

/** Identité typée : impossible de confondre un identifiant de commande avec un autre. */
public record CommandeId(String valeur) {

    public CommandeId {
        Objects.requireNonNull(valeur, "valeur");
    }

    public static CommandeId nouveau() {
        return new CommandeId(UUID.randomUUID().toString());
    }

    public static CommandeId de(String valeur) {
        return new CommandeId(valeur);
    }

    @Override
    public String toString() {
        return valeur;
    }
}
