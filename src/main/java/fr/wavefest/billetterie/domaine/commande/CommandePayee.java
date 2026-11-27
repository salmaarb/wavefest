package fr.wavefest.billetterie.domaine.commande;

import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.DomainEvent;
import fr.wavefest.billetterie.domaine.commun.Email;
import fr.wavefest.billetterie.domaine.commun.FestivalierId;
import fr.wavefest.billetterie.domaine.commun.Montant;
import fr.wavefest.billetterie.domaine.commun.Titulaire;

import java.time.Instant;
import java.util.List;

/** Événement du domaine (interne à la Billetterie). */
public record CommandePayee(
        CommandeId commandeId,
        FestivalierId acheteur,
        Email contact,
        Titulaire titulaire,
        Montant total,
        List<BilletAttribue> billets,
        Instant survenuLe
) implements DomainEvent {
}
