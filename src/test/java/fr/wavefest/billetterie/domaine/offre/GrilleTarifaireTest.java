package fr.wavefest.billetterie.domaine.offre;

import fr.wavefest.billetterie.domaine.commun.Montant;
import fr.wavefest.billetterie.domaine.commun.Quantite;
import fr.wavefest.support.Fixtures;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static fr.wavefest.support.Fixtures.FIN_EARLY;
import static fr.wavefest.support.Fixtures.MAINTENANT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GrilleTarifaireTest {

    @Disabled("TP3 R2")
    @Test
    void R2_le_prix_est_celui_de_la_vague_active() {
        // TODO TP3 R2 : en janvier, un pass 3 jours coûte 149 € (EARLY)
    }

    @Disabled("TP3 R2")
    @Test
    void R2_apres_sa_date_de_fin_la_vague_suivante_s_applique() {
        // TODO TP3 R2 : le lendemain de FIN_EARLY, la vague active est REGULAR
    }

    @Disabled("TP3 R3")
    @Test
    void R3_quota_epuise_la_vente_bascule_sur_la_vague_suivante() {
        // TODO TP3 R3 : EARLY a 10 places ; on en vend 8 puis on en demande 4 : 2 en EARLY + 2 en REGULAR
    }

    @Disabled("TP3 R3")
    @Test
    void R3_sans_assez_de_places_rien_n_est_reserve() {
        // TODO TP3 R3 : demander plus que le total disponible lève PlusDePlacesException et ne consomme rien
    }
}
