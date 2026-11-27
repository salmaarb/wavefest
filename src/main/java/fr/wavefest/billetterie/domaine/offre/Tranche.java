package fr.wavefest.billetterie.domaine.offre;

import fr.wavefest.billetterie.domaine.commun.Montant;
import fr.wavefest.billetterie.domaine.commun.Quantite;

/** Une partie d'une réservation, vendue au prix d'une vague donnée. */
public record Tranche(CodeVague vague, Quantite quantite, Montant prixUnitaire) {
}
