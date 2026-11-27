package fr.wavefest.billetterie.domaine.billet;

import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.DomainEvent;
import fr.wavefest.billetterie.domaine.commun.Titulaire;

import java.time.Instant;

public record BilletRevendu(BilletId billetId, Titulaire ancienTitulaire, Titulaire nouveauTitulaire,
                            OffreDeRevente offre, Instant survenuLe) implements DomainEvent {
}
