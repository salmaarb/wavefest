package fr.wavefest.billetterie.application;

import fr.wavefest.billetterie.application.ports.Billets;
import fr.wavefest.billetterie.domaine.billet.Billet;
import fr.wavefest.billetterie.domaine.billet.OffreDeRevente;
import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;
import fr.wavefest.billetterie.domaine.commun.Montant;

/** R6 : proposer un billet sur la bourse de revente officielle. */
public class MettreEnReventeUseCase {

    private final Billets billets;

    public MettreEnReventeUseCase(Billets billets) {
        this.billets = billets;
    }

    public OffreDeRevente executer(BilletId id, Montant prixDemande) {
        Billet billet = billets.parId(id).orElseThrow(() -> new ExceptionMetier("Billet introuvable : " + id));
        OffreDeRevente offre = billet.mettreEnRevente(prixDemande);
        billets.enregistrer(billet);
        return offre;
    }
}
