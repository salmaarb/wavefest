package fr.wavefest.support;

import fr.wavefest.billetterie.domaine.billet.DatesFestival;
import fr.wavefest.billetterie.domaine.commande.Commande;
import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.commun.Email;
import fr.wavefest.billetterie.domaine.commun.FestivalierId;
import fr.wavefest.billetterie.domaine.commun.Montant;
import fr.wavefest.billetterie.domaine.commun.Quantite;
import fr.wavefest.billetterie.domaine.commun.Titulaire;
import fr.wavefest.billetterie.domaine.offre.CodeVague;
import fr.wavefest.billetterie.domaine.offre.GrilleTarifaire;
import fr.wavefest.billetterie.domaine.offre.Tranche;
import fr.wavefest.billetterie.domaine.offre.TypePass;
import fr.wavefest.billetterie.domaine.offre.Vague;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/** Données de test partagées. Le festival ouvre le vendredi 9 juillet 2027 à 16h (UTC). */
public final class Fixtures {

    public static final Instant MAINTENANT = Instant.parse("2027-01-10T10:00:00Z");
    public static final Instant FIN_EARLY = Instant.parse("2027-01-31T23:00:00Z");
    public static final Instant FIN_REGULAR = Instant.parse("2027-05-31T22:00:00Z");
    public static final Instant FIN_LAST_MINUTE = Instant.parse("2027-07-11T22:00:00Z");
    public static final DatesFestival DATES = new DatesFestival(Instant.parse("2027-07-09T16:00:00Z"));

    public static final Titulaire ALICE = Titulaire.de("Alice", "Martin");
    public static final Titulaire BOB = Titulaire.de("Bob", "Durand");
    public static final Email EMAIL_ALICE = Email.de("alice@example.org");

    private Fixtures() {
    }

    /** EARLY : 10 places (59 / 149 / 299 €), REGULAR : 20 places (69 / 169 / 349 €), LAST_MINUTE : 1000 places. */
    public static GrilleTarifaire grilleStandard() {
        return new GrilleTarifaire(List.of(
                new Vague(CodeVague.EARLY, FIN_EARLY, 10, prix(59, 149, 299)),
                new Vague(CodeVague.REGULAR, FIN_REGULAR, 20, prix(69, 169, 349)),
                new Vague(CodeVague.LAST_MINUTE, FIN_LAST_MINUTE, 1000, prix(79, 189, 399))));
    }

    public static Map<TypePass, Montant> prix(long unJour, long troisJours, long vip) {
        return Map.of(TypePass.PASS_1_JOUR, Montant.euros(unJour),
                TypePass.PASS_3_JOURS, Montant.euros(troisJours),
                TypePass.VIP, Montant.euros(vip));
    }

    public static Commande uneCommandeOuverteA(Instant instant) {
        return Commande.ouvrir(CommandeId.nouveau(), FestivalierId.nouveau(), EMAIL_ALICE, ALICE, instant);
    }

    public static List<Tranche> tranche(int quantite, long prixUnitaire) {
        return List.of(new Tranche(CodeVague.EARLY, Quantite.de(quantite), Montant.euros(prixUnitaire)));
    }
}
