package fr.wavefest.billetterie.domaine.commun;

import java.math.BigDecimal;

public class MontantNegatifException extends ExceptionMetier {
    public MontantNegatifException(BigDecimal valeur) {
        super("Un montant ne peut pas être négatif : " + valeur);
    }
}
