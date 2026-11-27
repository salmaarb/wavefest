package fr.wavefest.billetterie.application.ports;

import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.Montant;

/** Port défini par la Billetterie. L'adapter PSP (l'ACL) l'implémente. */
public interface PortPaiement {
    ResultatPaiement payer(CommandeId commande, Montant montant);
}
