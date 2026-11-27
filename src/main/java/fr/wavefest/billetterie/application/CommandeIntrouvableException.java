package fr.wavefest.billetterie.application;

import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;

public class CommandeIntrouvableException extends ExceptionMetier {
    public CommandeIntrouvableException(CommandeId id) {
        super("Commande introuvable : " + id);
    }
}
