package fr.wavefest.billetterie.infrastructure.psp;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Simulation de l'API du prestataire de paiement (PSP). NE PAS MODIFIER :
 * c'est un système externe, son modèle (centimes, statuts en anglais, metadata)
 * nous est imposé. C'est précisément pour cela qu'on écrit une ACL.
 */
public class ApiPspSimulee {

    public record Charge(String id, long amount, String currency, String status, Map<String, String> metadata) {
    }

    public record Refund(String id, String reference, long amount, String status) {
    }

    private final Random aleatoire;
    private final double tauxEchecPaiement;
    private final double tauxEchecRemboursement;

    /** Paiements toujours acceptés, remboursements en échec 1 fois sur 5 (comme demandé au TP4). */
    public ApiPspSimulee(Random aleatoire) {
        this(aleatoire, 0.0, 0.2);
    }

    public ApiPspSimulee(Random aleatoire, double tauxEchecPaiement, double tauxEchecRemboursement) {
        this.aleatoire = aleatoire;
        this.tauxEchecPaiement = tauxEchecPaiement;
        this.tauxEchecRemboursement = tauxEchecRemboursement;
    }

    public Charge createCharge(long amountInCents, String currency, Map<String, String> metadata) {
        String status = aleatoire.nextDouble() < tauxEchecPaiement ? "card_declined" : "succeeded";
        return new Charge("ch_" + UUID.randomUUID().toString().substring(0, 8), amountInCents, currency, status, Map.copyOf(metadata));
    }

    public Refund createRefund(String reference, long amountInCents) {
        String status = aleatoire.nextDouble() < tauxEchecRemboursement ? "failed" : "succeeded";
        return new Refund("re_" + UUID.randomUUID().toString().substring(0, 8), reference, amountInCents, status);
    }
}
