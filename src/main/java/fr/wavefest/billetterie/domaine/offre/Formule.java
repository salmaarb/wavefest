package fr.wavefest.billetterie.domaine.offre;

import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;

import java.util.Objects;
import java.util.Optional;

/**
 * Value Object : ce que le festivalier achète.
 * Un pass 1 jour porte obligatoirement son jour ; les autres pass n'en ont pas.
 */
public record Formule(TypePass type, JourFestival jour) {

    public Formule {
        Objects.requireNonNull(type, "type");
        if (type == TypePass.PASS_1_JOUR && jour == null) {
            throw new ExceptionMetier("Un pass 1 jour doit préciser son jour");
        }
        if (type != TypePass.PASS_1_JOUR && jour != null) {
            throw new ExceptionMetier("Seul un pass 1 jour précise un jour");
        }
    }

    public static Formule unJour(JourFestival jour) {
        return new Formule(TypePass.PASS_1_JOUR, jour);
    }

    public static Formule troisJours() {
        return new Formule(TypePass.PASS_3_JOURS, null);
    }

    public static Formule vip() {
        return new Formule(TypePass.VIP, null);
    }

    public Optional<JourFestival> jourConcerne() {
        return Optional.ofNullable(jour);
    }

    @Override
    public String toString() {
        return jour == null ? type.name() : type.name() + "(" + jour + ")";
    }
}
