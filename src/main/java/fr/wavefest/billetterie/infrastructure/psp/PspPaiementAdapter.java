package fr.wavefest.billetterie.infrastructure.psp;

import fr.wavefest.billetterie.application.ports.PortPaiement;
import fr.wavefest.billetterie.application.ports.ResultatPaiement;
import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.Montant;

import java.util.Map;

/**
 * Anticorruption Layer : traduit le modèle du PSP vers notre langage.
 * Aucun « charge », « succeeded » ou centime ne doit sortir de cette classe.
 */
public class PspPaiementAdapter implements PortPaiement {

    private final ApiPspSimulee psp;

    public PspPaiementAdapter(ApiPspSimulee psp) {
        this.psp = psp;
    }

    @Override
    public ResultatPaiement payer(CommandeId commande, Montant montant) {
        // TODO TP3 ACL : appeler psp.createCharge (centimes, devise en minuscules, metadata ref) et traduire le statut
        throw new UnsupportedOperationException("TODO TP3 ACL");
    }
}
