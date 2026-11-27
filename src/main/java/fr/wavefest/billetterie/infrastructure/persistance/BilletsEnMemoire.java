package fr.wavefest.billetterie.infrastructure.persistance;

import fr.wavefest.billetterie.application.ports.Billets;
import fr.wavefest.billetterie.domaine.billet.Billet;
import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.offre.JourFestival;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class BilletsEnMemoire implements Billets {

    private final Map<BilletId, Billet> stockage = new ConcurrentHashMap<>();

    @Override
    public Optional<Billet> parId(BilletId id) {
        return Optional.ofNullable(stockage.get(id));
    }

    @Override
    public void enregistrer(Billet billet) {
        stockage.put(billet.id(), billet);
    }

    @Override
    public List<Billet> deLaCommande(CommandeId commande) {
        return stockage.values().stream().filter(b -> b.commande().equals(commande)).toList();
    }

    @Override
    public List<Billet> pass1JourPour(JourFestival jour) {
        return stockage.values().stream().filter(b -> b.estUnPass1JourPour(jour)).toList();
    }

    public int nombre() {
        return stockage.size();
    }
}
