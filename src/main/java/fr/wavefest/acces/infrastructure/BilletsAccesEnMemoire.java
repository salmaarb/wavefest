package fr.wavefest.acces.infrastructure;

import fr.wavefest.acces.application.BilletsAcces;
import fr.wavefest.acces.domaine.BilletAcces;

import java.util.ArrayList;
import java.util.List;

public class BilletsAccesEnMemoire implements BilletsAcces {

    private final List<BilletAcces> billets = new ArrayList<>();

    @Override
    public synchronized void enregistrer(BilletAcces billet) {
        billets.add(billet);
    }

    @Override
    public synchronized List<BilletAcces> tous() {
        return List.copyOf(billets);
    }
}
