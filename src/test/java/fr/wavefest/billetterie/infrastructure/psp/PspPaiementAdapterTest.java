package fr.wavefest.billetterie.infrastructure.psp;

import fr.wavefest.billetterie.application.ports.ResultatPaiement;
import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.Montant;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class PspPaiementAdapterTest {

    @Disabled("TP3 ACL")
    @Test
    void un_paiement_succeeded_devient_PaiementConfirme() {
        // TODO TP3 ACL : avec un PSP qui accepte tout, le résultat est un PaiementConfirme
    }

    @Disabled("TP3 ACL")
    @Test
    void un_paiement_refuse_par_la_banque_devient_PaiementRefuse() {
        // TODO TP3 ACL : avec un PSP qui refuse tout (taux d'échec 1.0), le résultat est un PaiementRefuse
    }
}
