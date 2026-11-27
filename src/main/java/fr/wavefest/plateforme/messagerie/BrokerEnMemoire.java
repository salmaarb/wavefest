package fr.wavefest.plateforme.messagerie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Broker synchrone en mémoire, avec deux outils pour tester les cas d'échec :
 *  - tomberEnPanne() / redemarrer() : publier() lève BrokerIndisponibleException ;
 *  - redistribuer(message) : simule une livraison en double (au moins une fois).
 */
public class BrokerEnMemoire implements Broker {

    private final Map<String, List<Consumer<Message>>> abonnes = new ConcurrentHashMap<>();
    private final List<Message> historique = new ArrayList<>();
    private boolean enPanne;

    @Override
    public void publier(Message message) {
        if (enPanne) {
            throw new BrokerIndisponibleException();
        }
        historique.add(message);
        distribuer(message);
    }

    @Override
    public void abonner(String topic, Consumer<Message> consommateur) {
        abonnes.computeIfAbsent(topic, t -> new ArrayList<>()).add(consommateur);
    }

    public void redistribuer(Message message) {
        distribuer(message);
    }

    public void tomberEnPanne() {
        enPanne = true;
    }

    public void redemarrer() {
        enPanne = false;
    }

    public List<Message> historique() {
        return List.copyOf(historique);
    }

    private void distribuer(Message message) {
        abonnes.getOrDefault(message.topic(), List.of()).forEach(c -> c.accept(message));
    }
}
