package ma.emsi.gestion;
import java.util.Objects;

public class Etudiant extends Personne
        implements Evaluatable, Comparable<Etudiant> {
    private final int id;
    private double note;

    public Etudiant(int id, String nom, double note) {
        super(nom);

        if (id <= 0) {
            throw new IllegalArgumentException("L'identifiant doit etre positif");
        }
        this.id = id;
        setNote(note);
    }
    public int getId() {
        return id;
    }
    public double getNote() {
        return note;
    }
    public void setNote(double note) {
        if (!Double.isFinite(note) || note < 0 || note > 20) {
            throw new IllegalArgumentException("La note doit etre comprise entre 0 et 20");}
        this.note = note;
    }
    @Override
    public boolean estAdmis() {return note >= 10;}
    @Override
    public String role() {return "Etudiant";}
    @Override
    public int compareTo(Etudiant autre) {return Integer.compare(this.id, autre.id);}
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}

        if (!(obj instanceof Etudiant)) {return false;}
        Etudiant autre = (Etudiant) obj;
        return this.id == autre.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Etudiant{id=" + id + ", nom='" + getNom() + '\'' + ", note=" + note + '}';}
}
