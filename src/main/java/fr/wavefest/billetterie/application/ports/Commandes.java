package fr.wavefest.billetterie.application.ports;

import fr.wavefest.billetterie.domaine.commande.Commande;
import fr.wavefest.billetterie.domaine.commun.CommandeId;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/** Port (repository) : la collection des commandes, en langage métier. */
public interface Commandes {

    Optional<Commande> parId(CommandeId id);

    void enregistrer(Commande commande);

    /** Commandes encore en cours ouvertes avant cet instant (candidates à l'expiration, R4). */
    List<Commande> enCoursOuvertesAvant(Instant limite);
}
