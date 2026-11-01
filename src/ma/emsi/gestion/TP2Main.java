
package ma.emsi.gestion;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TP2Main {

    public static boolean noteAdmise(double note) {
        return note >= 10;
    }

    public static boolean verifier(Etudiant e, RegleAdmission regle) {
        return regle.valider(e);
    }

    public static void main(String[] args) {
        Etudiant youssef = new Etudiant(2, "Youssef", 9);
        System.out.println("Note initiale : " + youssef.getNote());
        youssef.setNote(11);
        System.out.println("Note modifiee : " + youssef.getNote());
        System.out.println("noteAdmise(9) : " + noteAdmise(9));
        youssef.setNote(9);

        Etudiant amina = new Etudiant(1, "Amina", 15);
        Etudiant sara = new Etudiant(3, "Sara", 12.5);

        RegleAdmission regleAnonyme = new RegleAdmission() {
            @Override
            public boolean valider(Etudiant e) {
                return e.getNote() >= 10;
            }
        };
        RegleAdmission seuil10 = e -> e.getNote() >= 10;
        RegleAdmission seuil12 = e -> e.getNote() >= 12;
        System.out.println("Amina seuil 10 (lambda) : " + verifier(amina, seuil10));

        System.out.println("Amina : " + verifier(amina, regleAnonyme) + " / " + verifier(amina, seuil12));
        System.out.println("Youssef : " + verifier(youssef, seuil10));

        Predicate<Etudiant> admis = e -> e.getNote() >= 10;
        Function<Etudiant, String> statut =e -> e.estAdmis() ? "Admis" : "Ajourne";
        Consumer<Etudiant> afficher =e -> System.out.println(e.getNom() + " : " + e.getNote());
        Supplier<Etudiant> creerOmar = () -> new Etudiant(4, "Omar", 14);

        System.out.println("Amina : " + admis.test(amina) + " / " + statut.apply(amina));
        System.out.println("Youssef : " + statut.apply(youssef));

        afficher.accept(amina);
        afficher.accept(youssef);
        afficher.accept(sara);

        Etudiant omar = creerOmar.get();
        System.out.println(omar.getNom() + " : " + omar.getNote());

        Function<Etudiant, String> obtenirNom = Etudiant::getNom;
        Consumer<String> afficherTexte = System.out::println;

        afficherTexte.accept(obtenirNom.apply(amina));

        ResultatEtudiant resultat = new ResultatEtudiant(1, "Amina", 15, true);
        System.out.println(resultat);
        System.out.println(resultat.nom());

        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(amina);
        etudiants.add(youssef);
        etudiants.add(sara);

        Predicate<Etudiant> filtre = e -> verifier(e, seuil10);
        Function<Etudiant, ResultatEtudiant> convertir =e -> new ResultatEtudiant(e.getId(), e.getNom(), e.getNote(), filtre.test(e));

        Consumer<ResultatEtudiant> afficherResultat = System.out::println;

        System.out.println("Etudiants admis :");
        for (Etudiant e : etudiants) {
            if (filtre.test(e)) {afficherResultat.accept(convertir.apply(e));}
        }
    }
}
