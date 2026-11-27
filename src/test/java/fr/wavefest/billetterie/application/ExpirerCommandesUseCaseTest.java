package fr.wavefest.billetterie.application;

import fr.wavefest.billetterie.domaine.commande.StatutCommande;
import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.FestivalierId;
import fr.wavefest.billetterie.domaine.commun.Quantite;
import fr.wavefest.billetterie.domaine.offre.CodeVague;
import fr.wavefest.billetterie.domaine.offre.Formule;
import fr.wavefest.billetterie.infrastructure.evenements.PublicateurEvenementsEnMemoire;
import fr.wavefest.billetterie.infrastructure.persistance.CommandesEnMemoire;
import fr.wavefest.billetterie.infrastructure.persistance.GrillesTarifairesEnMemoire;
import fr.wavefest.support.Fixtures;
import fr.wavefest.support.HorlogeFixe;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static fr.wavefest.support.Fixtures.ALICE;
import static fr.wavefest.support.Fixtures.EMAIL_ALICE;
import static org.assertj.core.api.Assertions.assertThat;

class ExpirerCommandesUseCaseTest {

    private final HorlogeFixe horloge = new HorlogeFixe(Fixtures.MAINTENANT);
    private final CommandesEnMemoire commandes = new CommandesEnMemoire();
    private final GrillesTarifairesEnMemoire grilles = new GrillesTarifairesEnMemoire(Fixtures.grilleStandard());
    private final AcheterPassUseCase acheter = new AcheterPassUseCase(commandes, grilles, horloge);
    private final ExpirerCommandesUseCase expirer =
            new ExpirerCommandesUseCase(commandes, grilles, new PublicateurEvenementsEnMemoire(), horloge);

    @Disabled("TP3 R4")
    @Test
    void R4_les_places_d_une_commande_expiree_sont_rendues_au_quota() {
        // TODO TP3 R4 : 4 places EARLY réservées puis 15 min écoulées : la commande expire et EARLY retrouve 10 places
    }

    @Disabled("TP3 R4")
    @Test
    void R4_une_commande_recente_n_expire_pas() {
        // TODO TP3 R4 : après 10 minutes, rien n'expire
    }
}
