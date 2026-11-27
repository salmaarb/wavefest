package fr.wavefest.billetterie.application.ports;

import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.Montant;

/** TP4 : port de remboursement (le PSP simulé échoue 1 fois sur 5). */
public interface PortRemboursement {

    /** @return vrai si le remboursement est accepté par le PSP. */
    boolean rembourser(BilletId billet, Montant montant);
}
