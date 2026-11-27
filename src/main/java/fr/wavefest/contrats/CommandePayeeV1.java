package fr.wavefest.contrats;

import java.util.List;

/**
 * Événement d'intégration publié par la Billetterie (Published Language).
 * Règles : types simples, aucun type du domaine, champs ajoutés = compatibles,
 * champ supprimé ou renommé = nouvelle version (V2) publiée en parallèle.
 *
 * @param montantTotalCentimes total en centimes d'euro
 * @param survenuLe            instant ISO-8601
 */
public record CommandePayeeV1(
        String eventId,
        String commandeId,
        String emailAcheteur,
        String nomTitulaire,
        long montantTotalCentimes,
        List<BilletV1> billets,
        String survenuLe
) {
    /** @param jour VENDREDI, SAMEDI, DIMANCHE ou null si le pass n'est pas un pass 1 jour */
    public record BilletV1(String billetId, String typePass, String jour) {
    }
}
