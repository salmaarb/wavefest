package fr.wavefest.billetterie.domaine.commande;

import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;

public class CommandeNonModifiableException extends ExceptionMetier {
    public CommandeNonModifiableException(CommandeId id, StatutCommande statut) {
        super("La commande " + id + " est " + statut + " : opération impossible");
    }
}
