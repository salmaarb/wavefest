package fr.wavefest.billetterie.domaine.billet;

import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;

public class ChangementDeNomHorsDelaiException extends ExceptionMetier {
    public ChangementDeNomHorsDelaiException(BilletId id) {
        super("Le billet " + id + " ne peut plus changer de titulaire : délai de J-7 dépassé");
    }
}
