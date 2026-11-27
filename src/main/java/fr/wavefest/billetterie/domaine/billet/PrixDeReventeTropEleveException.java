package fr.wavefest.billetterie.domaine.billet;

import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;
import fr.wavefest.billetterie.domaine.commun.Montant;

public class PrixDeReventeTropEleveException extends ExceptionMetier {
    public PrixDeReventeTropEleveException(BilletId id, Montant demande, Montant plafond) {
        super("Revente du billet " + id + " refusée : " + demande + " dépasse le prix d'achat " + plafond);
    }
}
