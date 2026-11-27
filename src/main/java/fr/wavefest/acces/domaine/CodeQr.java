package fr.wavefest.acces.domaine;

import java.util.Objects;

/** Value Object du contexte Contrôle d'accès. */
public record CodeQr(String valeur) {

    public CodeQr {
        Objects.requireNonNull(valeur);
    }

    /** Génération simplifiée (en vrai : signature cryptographique). */
    public static CodeQr pour(String billetId) {
        return new CodeQr("WF-" + Integer.toHexString(billetId.hashCode()).toUpperCase());
    }
}
