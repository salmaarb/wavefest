package fr.wavefest.billetterie.application.annulation;

import java.util.List;

/** Port : stockage de l'état de la saga. */
public interface DossiersRemboursement {

    void enregistrer(DossierRemboursement dossier);

    boolean existentPour(String annulationId);

    List<DossierRemboursement> deLAnnulation(String annulationId);

    List<DossierRemboursement> aTraiterManuellement();
}
