package fr.wavefest.billetterie.domaine.billet;

import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.Montant;
import fr.wavefest.billetterie.domaine.offre.Formule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static fr.wavefest.support.Fixtures.ALICE;
import static fr.wavefest.support.Fixtures.BOB;
import static fr.wavefest.support.Fixtures.DATES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BilletTest {

    private Billet unPass3JoursAchete149Euros() {
        return Billet.emettre(BilletId.nouveau(), CommandeId.nouveau(), Formule.troisJours(), ALICE, Montant.euros(149));
    }

    // ---------- R5 : changement de nom gratuit jusqu'à J-7 ----------

    @Disabled("TP3 R5")
    @Test
    void R5_changement_de_nom_accepte_avant_J_moins_7() {
        // TODO TP3 R5 : à J-8, le titulaire devient BOB
    }

    @Disabled("TP3 R5")
    @Test
    void R5_changement_de_nom_refuse_a_partir_de_J_moins_7() {
        // TODO TP3 R5 : à J-7 pile, ChangementDeNomHorsDelaiException et le titulaire reste ALICE
    }

    // ---------- R6 : revente plafonnée, commission 5 % ----------

    @Disabled("TP3 R6")
    @Test
    void R6_revente_refusee_au_dessus_du_prix_d_achat() {
        // TODO TP3 R6 : revendre à 150 € un billet payé 149 € lève PrixDeReventeTropEleveException
    }

    @Disabled("TP3 R6")
    @Test
    void R6_revente_au_prix_d_achat_commission_de_5_pourcents() {
        // TODO TP3 R6 : à 149 €, commission 7,45 €, net vendeur 141,55 €, billet EN_REVENTE
    }

    @Disabled("TP3 R6")
    @Test
    void R6_apres_revente_le_billet_change_de_titulaire() {
        // TODO TP3 R6 : conclureRevente(BOB) : titulaire BOB, prix de référence = prix de revente, événement BilletRevendu
    }
}
