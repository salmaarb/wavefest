package fr.wavefest.billetterie.infrastructure.persistance;

import fr.wavefest.billetterie.application.ports.Commandes;
import fr.wavefest.billetterie.domaine.commande.Commande;
import fr.wavefest.billetterie.domaine.commande.StatutCommande;
import fr.wavefest.billetterie.domaine.commun.CommandeId;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/** Adapter : implémentation en mémoire du port Commandes (suffisante pour le TP). */
public class CommandesEnMemoire implements Commandes {

    private final Map<CommandeId, Commande> stockage = new ConcurrentHashMap<>();

    @Override
    public Optional<Commande> parId(CommandeId id) {
        return Optional.ofNullable(stockage.get(id));
    }

    @Override
    public void enregistrer(Commande commande) {
        stockage.put(commande.id(), commande);
    }

    @Override
    public List<Commande> enCoursOuvertesAvant(Instant limite) {
        return stockage.values().stream()
                .filter(c -> c.statut() == StatutCommande.EN_COURS)
                .filter(c -> !c.ouverteLe().isAfter(limite))
                .toList();
    }
}
