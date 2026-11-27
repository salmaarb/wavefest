package fr.wavefest.billetterie.application.annulation;

import fr.wavefest.billetterie.application.ports.Billets;
import fr.wavefest.billetterie.application.ports.PortRemboursement;
import fr.wavefest.billetterie.domaine.billet.Billet;
import fr.wavefest.contrats.ConcertAnnuleV1;
import fr.wavefest.billetterie.domaine.offre.JourFestival;

import java.util.List;

/**
 * TP4 - Saga « annulation d'une tête d'affiche » (règle 9 du brief) :
 * les pass 1 jour du jour concerné sont remboursés à 50 %.
 *
 * Exigences :
 *  - idempotente : recevoir deux fois le même ConcertAnnuleV1 ne rembourse pas deux fois ;
 *  - le PSP échoue parfois : jusqu'à MAX_TENTATIVES essais par billet ;
 *  - au-delà, le dossier passe A_TRAITER_MANUELLEMENT (pas d'exception, le processus continue
 *    pour les autres billets).
 */
public class SagaAnnulationConcert {

    public static final int TAUX_REMBOURSEMENT_PCT = 50;

    private final Billets billets;
    private final DossiersRemboursement dossiers;
    private final PortRemboursement remboursement;

    public SagaAnnulationConcert(Billets billets, DossiersRemboursement dossiers, PortRemboursement remboursement) {
        this.billets = billets;
        this.dossiers = dossiers;
        this.remboursement = remboursement;
    }

    public void quandConcertAnnule(ConcertAnnuleV1 evenement) {
        // TODO TP4 : idempotence sur evenement.eventId(), un dossier par pass 1 jour concerné, puis traiter chaque dossier
    }

    /** Relance les dossiers en échec (déclenché par le support). */
    public void relancerDossiersEnEchec() {
        for (DossierRemboursement dossier : dossiers.aTraiterManuellement()) {
            dossier.relancer();
            traiter(dossier);
        }
    }

    private void traiter(DossierRemboursement dossier) {
        // TODO TP4 : tant que le dossier est en cours, appeler le PSP ; succès = rembourser le billet ; échec = enregistrerEchec()
    }
}
