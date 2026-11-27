package fr.wavefest.billetterie.domaine.offre;

import fr.wavefest.billetterie.domaine.commun.ExceptionMetier;
import fr.wavefest.billetterie.domaine.commun.Quantite;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Agrégat : les vagues de vente du festival et leurs quotas.
 * R2 : le prix dépend de la vague active.
 * R3 : quand le quota d'une vague est épuisé, la vente bascule sur la vague suivante.
 */
public class GrilleTarifaire {

    private final List<Vague> vagues;

    public GrilleTarifaire(List<Vague> vagues) {
        if (vagues.isEmpty()) {
            throw new ExceptionMetier("Une grille tarifaire contient au moins une vague");
        }
        this.vagues = new ArrayList<>(vagues);
        this.vagues.sort(Comparator.comparing(Vague::code));
    }

    /** La première vague ouverte à cet instant, s'il y en a une. */
    public Optional<Vague> vagueActive(Instant maintenant) {
        // TODO TP3 R2 : renvoyer la première vague ouverte à cet instant
        throw new UnsupportedOperationException("TODO TP3 R2");
    }

    /**
     * Réserve des places et renvoie les tranches tarifaires correspondantes.
     * Si la vague active n'a plus assez de places, le reste bascule sur la vague suivante (R3).
     * Tout ou rien : si le festival n'a pas assez de places, rien n'est réservé.
     */
    public List<Tranche> reserver(TypePass type, Quantite quantite, Instant maintenant) {
        // TODO TP3 R2/R3 : découper la quantité en tranches, vague par vague, puis consommer les quotas
        throw new UnsupportedOperationException("TODO TP3 R2/R3");
    }

    /** Remet des places dans le quota de leur vague (commande expirée, annulation...). */
    public void liberer(List<Tranche> tranches) {
        tranches.forEach(t -> vague(t.vague()).liberer(t.quantite().valeur()));
    }

    public void liberer(CodeVague code, Quantite quantite) {
        vague(code).liberer(quantite.valeur());
    }

    public Vague vague(CodeVague code) {
        return vagues.stream()
                .filter(v -> v.code() == code)
                .findFirst()
                .orElseThrow(() -> new ExceptionMetier("Vague inconnue : " + code));
    }

    public List<Vague> vagues() {
        return List.copyOf(vagues);
    }
}
