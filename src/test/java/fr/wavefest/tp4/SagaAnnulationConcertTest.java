package fr.wavefest.tp4;

import fr.wavefest.billetterie.application.annulation.DossierRemboursement;
import fr.wavefest.billetterie.application.annulation.SagaAnnulationConcert;
import fr.wavefest.billetterie.application.annulation.StatutRemboursement;
import fr.wavefest.billetterie.application.ports.PortRemboursement;
import fr.wavefest.billetterie.domaine.billet.Billet;
import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.Montant;
import fr.wavefest.billetterie.domaine.offre.Formule;
import fr.wavefest.billetterie.domaine.offre.JourFestival;
import fr.wavefest.billetterie.infrastructure.persistance.BilletsEnMemoire;
import fr.wavefest.billetterie.infrastructure.persistance.DossiersRemboursementEnMemoire;
import fr.wavefest.contrats.ConcertAnnuleV1;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static fr.wavefest.support.Fixtures.ALICE;
import static org.assertj.core.api.Assertions.assertThat;

class SagaAnnulationConcertTest {

    private final BilletsEnMemoire billets = new BilletsEnMemoire();
    private final DossiersRemboursementEnMemoire dossiers = new DossiersRemboursementEnMemoire();
    private final ConcertAnnuleV1 annulationSamedi = new ConcertAnnuleV1("ann-1", "Tête d'affiche", "SAMEDI", "2027-07-10T12:00:00Z");

    /** Faux PSP scripté : renvoie les réponses dans l'ordre, puis toujours « succès ». */
    private static PortRemboursement pspScripte(Boolean... reponses) {
        Deque<Boolean> file = new ArrayDeque<>(List.of(reponses));
        return (billet, montant) -> file.isEmpty() || file.poll();
    }

    private Billet passSamedi(long prix) {
        Billet b = Billet.emettre(BilletId.nouveau(), CommandeId.nouveau(), Formule.unJour(JourFestival.SAMEDI), ALICE, Montant.euros(prix));
        billets.enregistrer(b);
        return b;
    }

    @Disabled("TP4 saga")
    @Test
    void seuls_les_pass_1_jour_du_jour_annule_sont_rembourses_a_50_pourcents() {
        // TODO TP4 : un pass samedi (69 €) remboursé 34,50 € ; un pass dimanche et un pass 3 jours non remboursés
    }

    @Disabled("TP4 saga")
    @Test
    void un_echec_du_psp_est_retente() {
        // TODO TP4 : PSP KO puis OK : le billet est remboursé en 2 tentatives
    }

    @Disabled("TP4 saga")
    @Test
    void apres_3_echecs_le_dossier_part_en_traitement_manuel() {
        // TODO TP4 : 3 échecs : dossier A_TRAITER_MANUELLEMENT, billet non remboursé, pas d'exception
    }

    @Disabled("TP4 saga")
    @Test
    void la_meme_annulation_recue_deux_fois_ne_rembourse_qu_une_fois() {
        // TODO TP4 : traiter 2 fois le même ConcertAnnuleV1 : un seul dossier, aucune exception
    }
}
