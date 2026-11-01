package ma.emsi.gestion;

@FunctionalInterface
public interface RegleAdmission {
    boolean valider(Etudiant e);
}
