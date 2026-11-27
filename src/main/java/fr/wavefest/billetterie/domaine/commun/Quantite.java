package fr.wavefest.billetterie.domaine.commun;

/** Value Object : un nombre de billets, toujours strictement positif. */
public record Quantite(int valeur) {

    public Quantite {
       if (valeur <= 0) throw new QuantiteInvalideException(valeur);
    }

    public static Quantite de(int valeur) {
        return new Quantite(valeur);
    }
}
