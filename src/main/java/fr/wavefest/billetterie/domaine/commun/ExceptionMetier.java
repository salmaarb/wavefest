package fr.wavefest.billetterie.domaine.commun;

/** Violation d'une règle métier. Le message est écrit en langage ubiquitaire. */
public class ExceptionMetier extends RuntimeException {
    public ExceptionMetier(String message) {
        super(message);
    }
}
