package fr.wavefest.billetterie.infrastructure.persistance;

import fr.wavefest.billetterie.application.ports.GrillesTarifaires;
import fr.wavefest.billetterie.domaine.offre.GrilleTarifaire;

public class GrillesTarifairesEnMemoire implements GrillesTarifaires {

    private GrilleTarifaire courante;

    public GrillesTarifairesEnMemoire(GrilleTarifaire initiale) {
        this.courante = initiale;
    }

    @Override
    public GrilleTarifaire courante() {
        return courante;
    }

    @Override
    public void enregistrer(GrilleTarifaire grille) {
        this.courante = grille;
    }
}
