package fr.wavefest.billetterie.domaine.commun;

/** Value Object : un nombre de billets, toujours strictement positif. */
public record Quantite(int valeur) {

    public Quantite {
        // TODO TP3 : refuser une quantité nulle ou négative (ExceptionMetier)
    }

    public static Quantite de(int valeur) {
        return new Quantite(valeur);
    }
}
