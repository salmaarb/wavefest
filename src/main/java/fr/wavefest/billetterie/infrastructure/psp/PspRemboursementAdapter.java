package fr.wavefest.billetterie.infrastructure.psp;

import fr.wavefest.billetterie.application.ports.PortRemboursement;
import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.Montant;

/** ACL côté remboursement (TP4). */
public class PspRemboursementAdapter implements PortRemboursement {

    private final ApiPspSimulee psp;

    public PspRemboursementAdapter(ApiPspSimulee psp) {
        this.psp = psp;
    }

    @Override
    public boolean rembourser(BilletId billet, Montant montant) {
        return "succeeded".equals(psp.createRefund(billet.valeur(), montant.enCentimes()).status());
    }
}
