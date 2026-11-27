package fr.wavefest.notification;

import fr.wavefest.contrats.CommandePayeeV1;
import fr.wavefest.plateforme.messagerie.Message;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/** EXEMPLE COMPLET de consommateur idempotent : un doublon de message n'envoie pas deux e-mails. */
public class EnvoyerConfirmationQuandCommandePayee implements Consumer<Message> {

    private final ServiceEmail email;
    private final Set<String> dejaTraites = ConcurrentHashMap.newKeySet();

    public EnvoyerConfirmationQuandCommandePayee(ServiceEmail email) {
        this.email = email;
    }

    @Override
    public void accept(Message message) {
        if (!dejaTraites.add(message.id())) {
            return;
        }
        CommandePayeeV1 evenement = (CommandePayeeV1) message.contenu();
        email.envoyer(evenement.emailAcheteur(),
                "WaveFest : vos " + evenement.billets().size() + " billet(s)",
                "Bonjour " + evenement.nomTitulaire() + ", votre commande " + evenement.commandeId() + " est confirmée.");
    }
}
