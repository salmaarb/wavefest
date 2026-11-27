package fr.wavefest.acces.application;

import fr.wavefest.acces.domaine.BilletAcces;

import java.util.List;

public interface BilletsAcces {

    void enregistrer(BilletAcces billet);

    List<BilletAcces> tous();
}
