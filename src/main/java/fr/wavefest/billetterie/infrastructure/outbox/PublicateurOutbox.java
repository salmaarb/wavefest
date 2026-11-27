package fr.wavefest.billetterie.infrastructure.outbox;

import fr.wavefest.billetterie.application.ports.Horloge;
import fr.wavefest.billetterie.application.ports.PublicateurEvenements;
import fr.wavefest.billetterie.domaine.commande.CommandePayee;
import fr.wavefest.billetterie.domaine.commun.DomainEvent;
import fr.wavefest.contrats.CommandePayeeV1;
import fr.wavefest.contrats.Topics;

import java.util.List;
import java.util.UUID;

/**
 * TP4 : traduit les événements du domaine utiles aux autres contextes en événements
 * d'intégration (Published Language) et les écrit dans l'outbox.
 * Les événements purement internes (CommandeExpiree...) ne sortent pas du contexte.
 */
public class PublicateurOutbox implements PublicateurEvenements {

    private final Outbox outbox;
    private final Horloge horloge;

    public PublicateurOutbox(Outbox outbox, Horloge horloge) {
        this.outbox = outbox;
        this.horloge = horloge;
    }

    @Override
    public void publier(List<DomainEvent> evenements) {
        // TODO TP4 : pour chaque CommandePayee, écrire un MessageOutbox (topic Topics.COMMANDE_PAYEE_V1, contenu CommandePayeeV1)
    }
}
