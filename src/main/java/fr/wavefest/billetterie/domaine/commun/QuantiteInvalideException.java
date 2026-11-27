package fr.wavefest.billetterie.domaine.commun;

public class QuantiteInvalideException extends ExceptionMetier {
    public QuantiteInvalideException(int valeur) {
        super("La quantité doit être strictement positive");
    }
}