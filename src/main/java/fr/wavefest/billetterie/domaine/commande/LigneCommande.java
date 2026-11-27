package fr.wavefest.billetterie.domaine.commande;

import fr.wavefest.billetterie.domaine.commun.Montant;
import fr.wavefest.billetterie.domaine.commun.Quantite;
import fr.wavefest.billetterie.domaine.offre.CodeVague;
import fr.wavefest.billetterie.domaine.offre.Formule;

/** Value Object interne à l'agrégat Commande. */
public record LigneCommande(Formule formule, Quantite quantite, Montant prixUnitaire, CodeVague vague) {

    public Montant sousTotal() {
        return prixUnitaire.multiplierPar(quantite.valeur());
    }
}
