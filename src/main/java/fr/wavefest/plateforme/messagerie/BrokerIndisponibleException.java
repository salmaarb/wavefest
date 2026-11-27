package fr.wavefest.plateforme.messagerie;

public class BrokerIndisponibleException extends RuntimeException {
    public BrokerIndisponibleException() {
        super("Broker indisponible");
    }
}
