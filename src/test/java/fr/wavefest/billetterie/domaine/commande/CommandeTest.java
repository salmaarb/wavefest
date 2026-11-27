package fr.wavefest.billetterie.domaine.commande;

import fr.wavefest.billetterie.domaine.offre.Formule;
import fr.wavefest.billetterie.domaine.offre.JourFestival;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static fr.wavefest.support.Fixtures.MAINTENANT;
import static fr.wavefest.support.Fixtures.tranche;
import static fr.wavefest.support.Fixtures.uneCommandeOuverteA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandeTest {

    // ---------- R1 : 6 billets maximum par commande ----------

    @Disabled("TP3 R1 : exemple complet, à activer une fois R1 implémentée")
    @Test
    void R1_refuse_plus_de_6_billets_par_commande() {
        // Given
        Commande commande = uneCommandeOuverteA(MAINTENANT);
        commande.ajouterBillets(Formule.troisJours(), tranche(4, 149));
        // When / Then
        assertThatThrownBy(() -> commande.ajouterBillets(Formule.vip(), tranche(3, 299)))
                .isInstanceOf(LimiteDeBilletsDepasseeException.class);
        assertThat(commande.nombreDeBillets()).isEqualTo(4);
    }

    @Disabled("TP3 R1")
    @Test
    void R1_accepte_exactement_6_billets() {
        // TODO TP3 R1 : Given une commande vide, When on ajoute 6 billets, Then elle contient 6 billets
    }

    @Test
    void le_total_est_la_somme_des_lignes() {
        Commande commande = uneCommandeOuverteA(MAINTENANT);
        commande.ajouterBillets(Formule.troisJours(), tranche(2, 149));
        commande.ajouterBillets(Formule.unJour(JourFestival.VENDREDI), tranche(1, 59));
        assertThat(commande.total().toString()).isEqualTo("357.00 EUR");
    }

    // ---------- R4 : 15 minutes pour payer ----------

    @Disabled("TP3 R4")
    @Test
    void R4_une_commande_non_payee_expire_apres_15_minutes() {
        // TODO TP3 R4 : pas expirée à 14 min 59 s, expirée à 15 min
    }

    @Disabled("TP3 R4")
    @Test
    void R4_on_ne_peut_pas_payer_une_commande_expiree() {
        // TODO TP3 R4 : confirmerPaiement 16 minutes après l'ouverture lève CommandeExpireeException
    }

    @Disabled("TP3 R4")
    @Test
    void R4_l_expiration_publie_CommandeExpiree() {
        // TODO TP3 R4 : expirer() passe la commande EXPIREE et enregistre CommandeExpiree
    }

    // ---------- Paiement ----------

    @Test
    void le_paiement_attribue_un_billet_par_unite_et_publie_CommandePayee() {
        Commande commande = uneCommandeOuverteA(MAINTENANT);
        commande.ajouterBillets(Formule.troisJours(), tranche(3, 149));
        commande.confirmerPaiement(MAINTENANT.plus(Duration.ofMinutes(5)));
        assertThat(commande.statut()).isEqualTo(StatutCommande.PAYEE);
        assertThat(commande.billetsAttribues()).hasSize(3);
        assertThat(commande.evenements()).hasOnlyElementsOfType(CommandePayee.class);
    }

    @Test
    void une_commande_payee_n_est_plus_modifiable() {
        Commande commande = uneCommandeOuverteA(MAINTENANT);
        commande.ajouterBillets(Formule.vip(), tranche(1, 299));
        commande.confirmerPaiement(MAINTENANT.plus(Duration.ofMinutes(1)));
        assertThatThrownBy(() -> commande.ajouterBillets(Formule.vip(), tranche(1, 299)))
                .isInstanceOf(CommandeNonModifiableException.class);
    }
}
