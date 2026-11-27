package fr.wavefest.billetterie.domaine.offre;

import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;

public class PlusDePlacesException extends ExceptionMetier {
    public PlusDePlacesException(int demandees, int disponibles) {
        super("Plus assez de places : " + demandees + " demandées, " + disponibles + " disponibles");
    }
}
