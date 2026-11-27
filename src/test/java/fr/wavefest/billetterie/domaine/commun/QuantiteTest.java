package fr.wavefest.billetterie.domaine.commun;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuantiteTest {

    @Test
    void une_quantite_est_strictement_positive() {
        assertThatThrownBy(() -> Quantite.de(0))
                .isInstanceOf(ExceptionMetier.class)
                .hasMessage("La quantité doit être strictement positive");

        assertThatThrownBy(() -> Quantite.de(-2))
                .isInstanceOf(ExceptionMetier.class)
                .hasMessage("La quantité doit être strictement positive");
    }
}
