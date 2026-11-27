package fr.wavefest.billetterie.domaine.commun;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

/**
 * Value Object : une somme d'argent dans une devise.
 * Immuable, validé à la construction, égalité par valeur (record).
 * EXEMPLE COMPLET fourni : inspirez-vous-en pour Quantite, Email, etc.
 */
public record Montant(BigDecimal valeur, Currency devise) {

    public static final Currency EUR = Currency.getInstance("EUR");
    public static final Montant ZERO_EURO = euros("0");

    public Montant {
        Objects.requireNonNull(valeur, "valeur");
        Objects.requireNonNull(devise, "devise");
        if (valeur.signum() < 0) {
            throw new MontantNegatifException(valeur);
        }
        valeur = valeur.setScale(2, RoundingMode.HALF_EVEN);
    }

    public static Montant euros(String valeur) {
        return new Montant(new BigDecimal(valeur), EUR);
    }

    public static Montant euros(long valeur) {
        return new Montant(BigDecimal.valueOf(valeur), EUR);
    }

    public Montant ajouter(Montant autre) {
        exigerMemeDevise(autre);
        return new Montant(valeur.add(autre.valeur), devise);
    }

    public Montant soustraire(Montant autre) {
        exigerMemeDevise(autre);
        return new Montant(valeur.subtract(autre.valeur), devise);
    }

    public Montant multiplierPar(int facteur) {
        return new Montant(valeur.multiply(BigDecimal.valueOf(facteur)), devise);
    }

    /** Ex. : commission de 5 % : montant.pourcentage(5). */
    public Montant pourcentage(int pct) {
        BigDecimal resultat = valeur.multiply(BigDecimal.valueOf(pct))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
        return new Montant(resultat, devise);
    }

    public boolean estSuperieurA(Montant autre) {
        exigerMemeDevise(autre);
        return valeur.compareTo(autre.valeur) > 0;
    }

    /** Pour les systèmes externes (PSP) qui parlent en centimes. */
    public long enCentimes() {
        return valeur.movePointRight(2).longValueExact();
    }

    private void exigerMemeDevise(Montant autre) {
        if (!devise.equals(autre.devise)) {
            throw new ExceptionMetier("Devises différentes : " + devise + " et " + autre.devise);
        }
    }

    @Override
    public String toString() {
        return valeur.toPlainString() + " " + devise.getCurrencyCode();
    }
}
