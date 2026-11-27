package fr.wavefest.billetterie.application;

import fr.wavefest.billetterie.application.ports.PortPaiement;
import fr.wavefest.billetterie.application.ports.ResultatPaiement;
import fr.wavefest.billetterie.domaine.commande.CommandeExpireeException;
import fr.wavefest.billetterie.domaine.commande.CommandePayee;
import fr.wavefest.billetterie.domaine.commande.StatutCommande;
import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.FestivalierId;
import fr.wavefest.billetterie.domaine.commun.Quantite;
import fr.wavefest.billetterie.domaine.offre.Formule;
import fr.wavefest.billetterie.infrastructure.evenements.PublicateurEvenementsEnMemoire;
import fr.wavefest.billetterie.infrastructure.persistance.BilletsEnMemoire;
import fr.wavefest.billetterie.infrastructure.persistance.CommandesEnMemoire;
import fr.wavefest.billetterie.infrastructure.persistance.GrillesTarifairesEnMemoire;
import fr.wavefest.support.Fixtures;
import fr.wavefest.support.HorlogeFixe;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static fr.wavefest.support.Fixtures.ALICE;
import static fr.wavefest.support.Fixtures.EMAIL_ALICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/** Tests d'application : de vrais objets du domaine, des adapters en mémoire, un faux PSP. */
class PayerCommandeUseCaseTest {

    private final HorlogeFixe horloge = new HorlogeFixe(Fixtures.MAINTENANT);
    private final CommandesEnMemoire commandes = new CommandesEnMemoire();
    private final BilletsEnMemoire billets = new BilletsEnMemoire();
    private final GrillesTarifairesEnMemoire grilles = new GrillesTarifairesEnMemoire(Fixtures.grilleStandard());
    private final PublicateurEvenementsEnMemoire publicateur = new PublicateurEvenementsEnMemoire();
    private final AtomicInteger appelsPsp = new AtomicInteger();
    private final PortPaiement pspQuiAccepte = (commande, montant) -> {
        appelsPsp.incrementAndGet();
        return new ResultatPaiement.PaiementConfirme(commande, "ref-test");
    };

    private final AcheterPassUseCase acheter = new AcheterPassUseCase(commandes, grilles, horloge);
    private final PayerCommandeUseCase payer = new PayerCommandeUseCase(commandes, billets, pspQuiAccepte, publicateur, horloge);

    private CommandeId aliceAchete(Formule formule, int quantite) {
        return acheter.executer(new AcheterPass(FestivalierId.nouveau(), EMAIL_ALICE, ALICE, formule, Quantite.de(quantite)));
    }

    @Disabled("TP3 : à activer une fois PayerCommandeUseCase implémenté")
    @Test
    void payer_emet_un_billet_par_place_et_publie_CommandePayee() {
        // TODO TP3 : Given une commande de 3 pass, When on la paie, Then 3 billets émis, commande PAYEE, CommandePayee publié
    }

    @Disabled("TP3 R4")
    @Test
    void R4_une_commande_expiree_n_est_jamais_envoyee_au_psp() {
        // TODO TP3 R4 : 16 minutes après l'achat, payer lève CommandeExpireeException et le PSP n'est pas appelé
    }
}
