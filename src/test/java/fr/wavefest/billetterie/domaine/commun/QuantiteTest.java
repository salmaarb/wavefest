package fr.wavefest.billetterie.domaine.commun;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuantiteTest {

    //@Disabled("TP3 : à activer une fois Quantite implémentée")
    @Test
    void une_quantite_est_strictement_positive() {
        // TODO TP3 : vérifier que Quantite.de(0) et Quantite.de(-2) sont refusés
        assertThatThrownBy(() -> Quantite.de(0))
                .isInstanceOf(ExceptionMetier.class)
                .hasMessage("La quantité doit être strictement positive");

        assertThatThrownBy(() -> Quantite.de(-2))
                .isInstanceOf(ExceptionMetier.class)
                .hasMessage("La quantité doit être strictement positive");
    }
}
