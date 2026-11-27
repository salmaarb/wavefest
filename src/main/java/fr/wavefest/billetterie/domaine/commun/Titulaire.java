package fr.wavefest.billetterie.domaine.commun;

/** Value Object : la personne dont le nom figure sur un billet nominatif. */
public record Titulaire(String prenom, String nom) {

    public Titulaire {
        if (prenom == null || prenom.isBlank() || nom == null || nom.isBlank()) {
            throw new ExceptionMetier("Un billet nominatif exige un prénom et un nom");
        }
        prenom = prenom.trim();
        nom = nom.trim();
    }

    public static Titulaire de(String prenom, String nom) {
        return new Titulaire(prenom, nom);
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}
