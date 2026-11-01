
package ma.emsi.gestion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TP3Main {

    public static void main(String[] args) {

        // creer la liste des six etudiants
        List<Etudiant> etudiants = List.of(
                new Etudiant(1, "Amina", 15.0),
                new Etudiant(2, "Youssef", 9.0),
                new Etudiant(3, "Sara", 12.5),
                new Etudiant(4, "Omar", 14.0),
                new Etudiant(5, "Lina", 18.0),
                new Etudiant(6, "Karim", 7.5)
        );

        // definir la regle d'admission
        RegleAdmission seuil10 = e -> e.getNote() >= 10;

        // etape 1 : afficher les etudiants admis
        System.out.println("\n===== ETAPE 1 =====");
        System.out.println("Etudiants admis :");

        AnalyseEtudiants.afficherAdmis(etudiants, seuil10);

        // etape 2 : filtrer et transformer les donnees
        System.out.println("\n===== ETAPE 2 =====");

        List<ResultatEtudiant> resultats = AnalyseEtudiants.creerResultats(etudiants, seuil10);

        System.out.println("Les resultats :");
        resultats.forEach(System.out::println);

        List<String> noms = AnalyseEtudiants.nomsAdmis(etudiants, seuil10);
        System.out.println("Noms des admis : " + noms);


        // etape 3 : trier et afficher les etudiants
        System.out.println("\n===== ETAPE 3 =====");
        System.out.println("Tri par note croissante :");
        AnalyseEtudiants.trierCroissant(etudiants).forEach(e -> System.out.println(e.getNom() + " : " + e.getNote()));
        System.out.println("\nTri par note decroissante :");
        AnalyseEtudiants.trierDecroissant(etudiants).forEach(e -> System.out.println(e.getNom() + " : " + e.getNote()));
        System.out.println("\nTop 3 des meilleurs etudiants :");

        List<Etudiant> top3 = AnalyseEtudiants.topTrois(etudiants);

        for (int i = 0; i < top3.size(); i++) {
            Etudiant e = top3.get(i);
            System.out.println(
                    (i + 1) + ". " + e.getNom() + " : " + e.getNote()
            );
        }
        System.out.println("Noms du Top 3 : " + AnalyseEtudiants.nomsTopTrois(etudiants));

        // etape 4 : calculer les statistiques
        System.out.println("\n===== ETAPE 4 =====");

        long nombreAdmis = AnalyseEtudiants.compterAdmis(etudiants);

        boolean note16 = AnalyseEtudiants.existeNoteSuperieure16(etudiants);

        boolean notesValides = AnalyseEtudiants.toutesNotesValides(etudiants);

        Optional<Etudiant> meilleur = AnalyseEtudiants.meilleurEtudiant(etudiants);

        double moyenne = AnalyseEtudiants.calculerMoyenne(etudiants);

        System.out.println("Nombre d'admis : " + nombreAdmis);
        System.out.println("Note >= 16 : " + note16);
        System.out.println("Notes valides : " + notesValides);
        meilleur.ifPresent(e -> System.out.println("Meilleur etudiant : " + e.getNom() + " (" + e.getNote() + ")")
        );

        System.out.printf(java.util.Locale.US,"Moyenne generale : %.2f%n",moyenne);

        // etape 5 : regrouper les etudiants
        System.out.println("\n===== ETAPE 5 =====");

        Map<Boolean, List<Etudiant>> groupes = AnalyseEtudiants.partitionner(etudiants);

        System.out.println("Admis : " + groupes.get(true).stream().map(Etudiant::getNom).toList());

        System.out.println("Ajournes : " + groupes.get(false).stream().map(Etudiant::getNom).toList());

        Map<String, List<Etudiant>> categories = AnalyseEtudiants.regrouper(etudiants);

        System.out.println("\nGroupement par categorie :");

        for (String categorie : List.of("Tres bien", "Admis", "Ajourne")) {

            List<Etudiant> liste = categories.getOrDefault(categorie, List.of());

            System.out.println(categorie + " : " + liste.stream().map(Etudiant::getNom).toList()
            );
        }

        // afficher le bilan final
        System.out.println("Total etudiants : " + etudiants.size());
        System.out.println("Nombre d'admis : " + nombreAdmis);

        System.out.printf(java.util.Locale.US,"Moyenne generale : %.2f%n",moyenne);
        meilleur.ifPresent(e ->System.out.println("Meilleur etudiant : " + e.getNom() + " (" + e.getNote() + ")"));

        System.out.println("Top 3 : " + AnalyseEtudiants.nomsTopTrois(etudiants));


        // defi A : calculer la somme des notes
        System.out.println("\n===== DEFI A =====");

        double sommeReduce = AnalyseEtudiants.sommeNotesReduce(etudiants);
        double somme = AnalyseEtudiants.sommeNotes(etudiants);

        System.out.println("Somme avec reduce : " + sommeReduce);
        System.out.println("Somme avec sum : " + somme);

        // defi B : fusionner les deux groupes
        System.out.println("\n===== DEFI B =====");

        List<Etudiant> fusion = AnalyseEtudiants.fusionnerGroupes(etudiants);

        System.out.println("Liste fusionnee :");

        fusion.forEach(e ->System.out.println(e.getNom() + " : " + e.getNote()));
        System.out.println("Nombre total : " + fusion.size());
    }
}
