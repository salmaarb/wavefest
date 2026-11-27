package fr.wavefest.billetterie.domaine.commande;

import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;

public class CommandeExpireeException extends ExceptionMetier {
    public CommandeExpireeException(CommandeId id) {
        super("La commande " + id + " a expiré : ses places ont été libérées");
    }
}
