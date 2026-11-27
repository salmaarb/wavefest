package fr.wavefest.billetterie.infrastructure.persistance;

import fr.wavefest.billetterie.application.annulation.DossierRemboursement;
import fr.wavefest.billetterie.application.annulation.DossiersRemboursement;
import fr.wavefest.billetterie.application.annulation.StatutRemboursement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DossiersRemboursementEnMemoire implements DossiersRemboursement {

    private final Map<String, DossierRemboursement> stockage = new LinkedHashMap<>();

    @Override
    public synchronized void enregistrer(DossierRemboursement dossier) {
        stockage.put(dossier.annulationId() + "/" + dossier.billet(), dossier);
    }

    @Override
    public synchronized boolean existentPour(String annulationId) {
        return stockage.values().stream().anyMatch(d -> d.annulationId().equals(annulationId));
    }

    @Override
    public synchronized List<DossierRemboursement> deLAnnulation(String annulationId) {
        return stockage.values().stream().filter(d -> d.annulationId().equals(annulationId)).toList();
    }

    @Override
    public synchronized List<DossierRemboursement> aTraiterManuellement() {
        return stockage.values().stream().filter(d -> d.statut() == StatutRemboursement.A_TRAITER_MANUELLEMENT).toList();
    }
}
