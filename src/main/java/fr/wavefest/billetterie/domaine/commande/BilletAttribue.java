package fr.wavefest.billetterie.domaine.commande;

import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.Montant;
import fr.wavefest.billetterie.domaine.offre.Formule;

/** Un billet attribué au moment du paiement : 1 unité d'une ligne = 1 billet. */
public record BilletAttribue(BilletId billetId, Formule formule, Montant prixPaye) {
}
