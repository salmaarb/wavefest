package fr.wavefest.acces.infrastructure;

import fr.wavefest.acces.application.MessagesTraites;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MessagesTraitesEnMemoire implements MessagesTraites {

    private final Set<String> ids = ConcurrentHashMap.newKeySet();

    @Override
    public boolean marquerSiNouveau(String messageId) {
        return ids.add(messageId);
    }
}
