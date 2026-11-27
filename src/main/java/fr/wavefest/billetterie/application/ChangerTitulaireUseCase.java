package fr.wavefest.billetterie.application;

import fr.wavefest.billetterie.application.ports.Billets;
import fr.wavefest.billetterie.application.ports.Horloge;
import fr.wavefest.billetterie.domaine.billet.Billet;
import fr.wavefest.billetterie.domaine.billet.DatesFestival;
import fr.wavefest.billetterie.domaine.commun.BilletId;
import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;
import fr.wavefest.billetterie.domaine.commun.Titulaire;

/** R5 : changer le nom porté par un billet. */
public class ChangerTitulaireUseCase {

    private final Billets billets;
    private final DatesFestival dates;
    private final Horloge horloge;

    public ChangerTitulaireUseCase(Billets billets, DatesFestival dates, Horloge horloge) {
        this.billets = billets;
        this.dates = dates;
        this.horloge = horloge;
    }

    public void executer(BilletId id, Titulaire nouveau) {
        Billet billet = billets.parId(id).orElseThrow(() -> new ExceptionMetier("Billet introuvable : " + id));
        billet.changerTitulaire(nouveau, horloge.maintenant(), dates);
        billets.enregistrer(billet);
    }
}
