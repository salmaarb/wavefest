package fr.wavefest.billetterie.application.ports;

import fr.wavefest.billetterie.domaine.billet.Billet;
import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.CommandeId;
import fr.wavefest.billetterie.domaine.offre.JourFestival;

import java.util.List;
import java.util.Optional;

public interface Billets {

    Optional<Billet> parId(BilletId id);

    void enregistrer(Billet billet);

    List<Billet> deLaCommande(CommandeId commande);

    /** TP4 : les pass 1 jour du jour donné (saga d'annulation de concert). */
    List<Billet> pass1JourPour(JourFestival jour);
}
