package fr.wavefest.billetterie.application.ports;

import fr.wavefest.billetterie.domaine.offre.GrilleTarifaire;

public interface GrillesTarifaires {

    GrilleTarifaire courante();

    void enregistrer(GrilleTarifaire grille);
}
