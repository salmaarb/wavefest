package fr.wavefest.tp4;

import fr.wavefest.acces.application.EmettreBilletsQrQuandCommandePayee;
import fr.wavefest.acces.infrastructure.BilletsAccesEnMemoire;
import fr.wavefest.acces.infrastructure.MessagesTraitesEnMemoire;
import fr.wavefest.contrats.CommandePayeeV1;
import fr.wavefest.contrats.Topics;
import fr.wavefest.plateforme.messagerie.Message;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EmettreBilletsQrTest {

    private final BilletsAccesEnMemoire billets = new BilletsAccesEnMemoire();
    private final EmettreBilletsQrQuandCommandePayee consommateur =
            new EmettreBilletsQrQuandCommandePayee(new MessagesTraitesEnMemoire(), billets);

    private final Message message = new Message("evt-1", Topics.COMMANDE_PAYEE_V1, new CommandePayeeV1(
            "evt-1", "cmd-1", "alice@example.org", "Alice Martin", 29800,
            List.of(new CommandePayeeV1.BilletV1("b1", "VIP", null), new CommandePayeeV1.BilletV1("b2", "VIP", null)),
            "2027-01-10T10:05:00Z"));

    @Disabled("TP4 idempotence")
    @Test
    void un_qr_par_billet() {
        // TODO TP4 : un message avec 2 billets produit 2 BilletAcces
    }

    @Disabled("TP4 idempotence")
    @Test
    void un_message_recu_deux_fois_n_emet_pas_de_doublon() {
        // TODO TP4 : le même message reçu 2 fois produit toujours 2 BilletAcces
    }
}
