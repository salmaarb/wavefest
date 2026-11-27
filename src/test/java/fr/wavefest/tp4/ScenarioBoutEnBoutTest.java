package fr.wavefest.tp4;

import fr.wavefest.acces.application.EmettreBilletsQrQuandCommandePayee;
import fr.wavefest.acces.infrastructure.BilletsAccesEnMemoire;
import fr.wavefest.acces.infrastructure.MessagesTraitesEnMemoire;
import fr.wavefest.billetterie.application.AcheterPass;
import fr.wavefest.billetterie.application.AcheterPassUseCase;
import fr.wavefest.billetterie.application.PayerCommandeUseCase;
import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.FestivalierId;
import fr.wavefest.billetterie.domaine.commun.Quantite;
import fr.wavefest.billetterie.domaine.offre.Formule;
import fr.wavefest.billetterie.domaine.offre.JourFestival;
import fr.wavefest.billetterie.infrastructure.outbox.Outbox;
import fr.wavefest.billetterie.infrastructure.outbox.PublicateurOutbox;
import fr.wavefest.billetterie.infrastructure.outbox.RelaisOutbox;
import fr.wavefest.billetterie.infrastructure.persistance.BilletsEnMemoire;
import fr.wavefest.billetterie.infrastructure.persistance.CommandesEnMemoire;
import fr.wavefest.billetterie.infrastructure.persistance.GrillesTarifairesEnMemoire;
import fr.wavefest.billetterie.infrastructure.psp.ApiPspSimulee;
import fr.wavefest.billetterie.infrastructure.psp.PspPaiementAdapter;
import fr.wavefest.contrats.Topics;
import fr.wavefest.notification.EnvoyerConfirmationQuandCommandePayee;
import fr.wavefest.notification.FauxServiceEmail;
import fr.wavefest.plateforme.messagerie.BrokerEnMemoire;
import fr.wavefest.support.Fixtures;
import fr.wavefest.support.HorlogeFixe;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static fr.wavefest.support.Fixtures.ALICE;
import static fr.wavefest.support.Fixtures.EMAIL_ALICE;
import static org.assertj.core.api.Assertions.assertThat;

/** La démo de la soutenance, sous forme de test : achat, paiement, billets QR, e-mail. */
class ScenarioBoutEnBoutTest {

    @Disabled("TP4 : la démo de bout en bout")
    @Test
    void achat_paiement_billets_qr_et_email_meme_avec_un_doublon_de_message() {
        // TODO TP4 : câbler Billetterie + outbox + relais + broker + Accès + Notification, puis vérifier QR et e-mail
    }
}
