package fr.wavefest.billetterie.domaine.commande;

import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;

public class LimiteDeBilletsDepasseeException extends ExceptionMetier {
    public LimiteDeBilletsDepasseeException(CommandeId id, int maximum) {
        super("La commande " + id + " ne peut pas dépasser " + maximum + " billets");
    }
}
