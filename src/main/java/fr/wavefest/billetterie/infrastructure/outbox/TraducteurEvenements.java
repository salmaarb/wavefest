package fr.wavefest.billetterie.infrastructure.outbox;

import fr.wavefest.billetterie.domaine.commande.CommandePayee;
import fr.wavefest.contrats.CommandePayeeV1;

/** Domain event (riche, interne) vers integration event (stable, types simples, versionné). */
public final class TraducteurEvenements {

    private TraducteurEvenements() {
    }

    public static CommandePayeeV1 versV1(String eventId, CommandePayee e) {
        // TODO TP4 : construire le CommandePayeeV1 (types simples uniquement : String, long...)
        throw new UnsupportedOperationException("TODO TP4");
    }
}
