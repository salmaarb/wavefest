package fr.wavefest.billetterie.domaine.commun;

import java.util.Objects;
import java.util.regex.Pattern;

/** Value Object : une adresse e-mail valide (validation volontairement simple). */
public record Email(String valeur) {

    private static final Pattern FORMAT = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    public Email {
        Objects.requireNonNull(valeur, "valeur");
        valeur = valeur.trim().toLowerCase();
        if (!FORMAT.matcher(valeur).matches()) {
            throw new ExceptionMetier("Adresse e-mail invalide : " + valeur);
        }
    }

    public static Email de(String valeur) {
        return new Email(valeur);
    }

    @Override
    public String toString() {
        return valeur;
    }
}
