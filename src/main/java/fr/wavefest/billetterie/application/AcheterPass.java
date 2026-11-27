package fr.wavefest.billetterie.application;

import fr.wavefest.billetterie.domaine.commun.Email;
import fr.wavefest.billetterie.domaine.commun.FestivalierId;
import fr.wavefest.billetterie.domaine.commun.Quantite;
import fr.wavefest.billetterie.domaine.commun.Titulaire;
import fr.wavefest.billetterie.domaine.offre.Formule;

/** Commande (au sens CQRS) : l'intention du festivalier. */
public record AcheterPass(FestivalierId acheteur, Email contact, Titulaire titulaire, Formule formule, Quantite quantite) {
}
