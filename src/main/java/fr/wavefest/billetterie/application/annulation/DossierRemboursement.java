package fr.wavefest.billetterie.application.annulation;

import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.Montant;

import java.util.Objects;

/**
 * TP4 : état de la saga pour UN billet. C'est cet état persistant qui permet
 * de reprendre le processus après une panne et de ne jamais rembourser deux fois.
 */
public class DossierRemboursement {

    public static final int MAX_TENTATIVES = 3;

    private final String annulationId;
    private final BilletId billet;
    private final Montant montant;
    private StatutRemboursement statut = StatutRemboursement.EN_COURS;
    private int tentatives;

    public DossierRemboursement(String annulationId, BilletId billet, Montant montant) {
        this.annulationId = Objects.requireNonNull(annulationId);
        this.billet = Objects.requireNonNull(billet);
        this.montant = Objects.requireNonNull(montant);
    }

    public void enregistrerSucces() {
        tentatives++;
        statut = StatutRemboursement.REMBOURSE;
    }

    /** Après MAX_TENTATIVES échecs, le dossier part en traitement manuel (escalade au support). */
    public void enregistrerEchec() {
        // TODO TP4 : compter la tentative ; au-delà de MAX_TENTATIVES, passer A_TRAITER_MANUELLEMENT
    }

    /** Relance manuelle par le support : on repart pour un cycle de tentatives. */
    public void relancer() {
        if (statut == StatutRemboursement.A_TRAITER_MANUELLEMENT) {
            statut = StatutRemboursement.EN_COURS;
            tentatives = 0;
        }
    }

    public boolean estEnCours() {
        return statut == StatutRemboursement.EN_COURS;
    }

    public String annulationId() {
        return annulationId;
    }

    public BilletId billet() {
        return billet;
    }

    public Montant montant() {
        return montant;
    }

    public StatutRemboursement statut() {
        return statut;
    }

    public int tentatives() {
        return tentatives;
    }
}
