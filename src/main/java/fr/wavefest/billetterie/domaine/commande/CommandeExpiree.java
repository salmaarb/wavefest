package fr.wavefest.billetterie.domaine.commande;

import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.DomainEvent;

import java.time.Instant;
import java.util.List;

public record CommandeExpiree(CommandeId commandeId, List<LigneCommande> lignes, Instant survenuLe) implements DomainEvent {
}
