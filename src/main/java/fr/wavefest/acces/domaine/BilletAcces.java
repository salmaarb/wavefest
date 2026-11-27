package fr.wavefest.acces.domaine;

import java.util.Objects;

/**
 * Le « billet » du point de vue du Contrôle d'accès : un QR code à scanner.
 * Même mot que dans la Billetterie, autre modèle : c'est normal, c'est un autre Bounded Context.
 */
public record BilletAcces(String billetId, CodeQr code, String titulaire, String jour) {

    public BilletAcces {
        Objects.requireNonNull(billetId);
        Objects.requireNonNull(code);
        Objects.requireNonNull(titulaire);
    }
}
