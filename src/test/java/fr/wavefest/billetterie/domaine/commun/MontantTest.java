package fr.wavefest.billetterie.domaine.commun;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/** EXEMPLE COMPLET : des tests de Value Object, nommés comme des règles. */
class MontantTest {

    @Test
    void un_montant_ne_peut_pas_etre_negatif() {
        assertThatThrownBy(() -> Montant.euros("-1")).isInstanceOf(MontantNegatifException.class);
    }

    @Test
    void deux_montants_de_meme_valeur_sont_egaux() {
        assertThat(Montant.euros("10")).isEqualTo(Montant.euros("10.00"));
    }

    @Test
    void ajouter_renvoie_un_nouveau_montant() {
        Montant dix = Montant.euros(10);
        Montant total = dix.ajouter(Montant.euros("2.50"));
        assertThat(total).isEqualTo(Montant.euros("12.50"));
        assertThat(dix).isEqualTo(Montant.euros(10));
    }

    @Test
    void cinq_pourcents_de_149_euros_font_7_euros_45() {
        assertThat(Montant.euros(149).pourcentage(5)).isEqualTo(Montant.euros("7.45"));
    }

    @Test
    void le_psp_parle_en_centimes() {
        assertThat(Montant.euros("159.90").enCentimes()).isEqualTo(15990L);
    }
}
