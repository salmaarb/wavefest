package fr.wavefest.billetterie.domaine.billet;

import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.Montant;

/** Value Object : un billet proposé sur la bourse de revente officielle (R6). */
public record OffreDeRevente(BilletId billet, Montant prix, Montant commission, Montant netVendeur) {

    public static final int COMMISSION_PCT = 5;

    /** R6 : la commission de WaveFest est de 5 % du prix de revente ; le vendeur touche le reste. */
    public static OffreDeRevente pour(BilletId billet, Montant prix) {
        // TODO TP3 R6 : calculer la commission (5 %) et le net vendeur
        throw new UnsupportedOperationException("TODO TP3 R6");
    }
}
