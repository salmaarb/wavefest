package fr.wavefest.tp4;

import fr.wavefest.billetterie.infrastructure.outbox.MessageOutbox;
import fr.wavefest.billetterie.infrastructure.outbox.Outbox;
import fr.wavefest.billetterie.infrastructure.outbox.RelaisOutbox;
import fr.wavefest.plateforme.messagerie.BrokerEnMemoire;
import fr.wavefest.support.Fixtures;
import fr.wavefest.support.HorlogeFixe;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RelaisOutboxTest {

    private final HorlogeFixe horloge = new HorlogeFixe(Fixtures.MAINTENANT);
    private final Outbox outbox = new Outbox();
    private final BrokerEnMemoire broker = new BrokerEnMemoire();
    private final RelaisOutbox relais = new RelaisOutbox(outbox, broker, horloge);

    @Disabled("TP4 outbox")
    @Test
    void le_relais_publie_les_messages_en_attente_une_seule_fois() {
        // TODO TP4 : 2 messages en attente : 1er passage = 2 publiés, 2e passage = 0
    }

    @Disabled("TP4 outbox")
    @Test
    void broker_en_panne_le_message_reste_en_attente_et_part_plus_tard() {
        // TODO TP4 : broker en panne : 0 publié, message toujours en attente ; après redémarrage : publié
    }
}
