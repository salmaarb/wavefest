package fr.wavefest.billetterie.domaine.offre;

import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;
import fr.wavefest.billetterie.domaine.commun.Montant;

import java.time.Instant;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Entité interne à l'agrégat GrilleTarifaire.
 * Une vague se clôt à sa date de fin OU quand son quota est épuisé.
 */
public class Vague {

    private final CodeVague code;
    private final Instant finDeVente;
    private final int quota;
    private final Map<TypePass, Montant> prix;
    private int vendus;

    public Vague(CodeVague code, Instant finDeVente, int quota, Map<TypePass, Montant> prix) {
        this.code = Objects.requireNonNull(code);
        this.finDeVente = Objects.requireNonNull(finDeVente);
        if (quota <= 0) {
            throw new ExceptionMetier("Le quota d'une vague doit être positif");
        }
        this.quota = quota;
        this.prix = new EnumMap<>(prix);
        if (this.prix.size() != TypePass.values().length) {
            throw new ExceptionMetier("Une vague doit fixer un prix pour chaque type de pass");
        }
    }

    public boolean estOuverteA(Instant maintenant) {
        // TODO TP3 R2/R3 : ouverte si la date de fin n'est pas passée ET s'il reste des places
        throw new UnsupportedOperationException("TODO TP3 R2/R3");
    }

    public int placesRestantes() {
        return quota - vendus;
    }

    public Montant prix(TypePass type) {
        return prix.get(type);
    }

    void vendre(int nombre) {
        if (nombre > placesRestantes()) {
            throw new PlusDePlacesException(nombre, placesRestantes());
        }
        vendus += nombre;
    }

    void liberer(int nombre) {
        vendus = Math.max(0, vendus - nombre);
    }

    public CodeVague code() {
        return code;
    }

    public Instant finDeVente() {
        return finDeVente;
    }

    public int quota() {
        return quota;
    }
}
