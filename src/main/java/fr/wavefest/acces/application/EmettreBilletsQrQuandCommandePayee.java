package fr.wavefest.acces.application;

import fr.wavefest.acces.domaine.BilletAcces;
import fr.wavefest.acces.domaine.CodeQr;
import fr.wavefest.contrats.CommandePayeeV1;
import fr.wavefest.plateforme.messagerie.Message;

import java.util.function.Consumer;

/**
 * TP4 : consommateur du topic billetterie.commande-payee.v1.
 * Il DOIT être idempotent : le relais garantit « au moins une fois », pas « exactement une fois ».
 */
public class EmettreBilletsQrQuandCommandePayee implements Consumer<Message> {

    private final MessagesTraites dejaTraites;
    private final BilletsAcces billets;

    public EmettreBilletsQrQuandCommandePayee(MessagesTraites dejaTraites, BilletsAcces billets) {
        this.dejaTraites = dejaTraites;
        this.billets = billets;
    }

    @Override
    public void accept(Message message) {
        // TODO TP4 : ignorer un message déjà traité, puis émettre un BilletAcces (QR) par billet du CommandePayeeV1
    }
}
