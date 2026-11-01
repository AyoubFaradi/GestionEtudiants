package ma.emsi.gestion;

public abstract class Personne {

    private String nom;

    public Personne(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    public abstract String role();
}
