package ma.emsi.gestion;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnalyseEtudiants {

    // etape 1 : afficher les etudiants admis
    public static void afficherAdmis(
            List<Etudiant> etudiants,
            RegleAdmission regle) {etudiants.stream().filter(regle::valider)
            .forEach(e -> System.out.println(e.getNom() + " : " + e.getNote()));
    }

    // etape 2 : creer les resultats des etudiants admis
    public static List<ResultatEtudiant> creerResultats(
            List<Etudiant> etudiants,
            RegleAdmission regle) {

        Function<Etudiant, ResultatEtudiant> convertir =
                e -> new ResultatEtudiant(e.getId(),e.getNom(),e.getNote(),regle.valider(e));
        return etudiants.stream().filter(regle::valider).map(convertir).toList();
    }

    // afficher les noms des etudiants admis
    public static List<String> nomsAdmis(
            List<Etudiant> etudiants,
            RegleAdmission regle) {
        return etudiants.stream().filter(regle::valider).map(Etudiant::getNom).toList();
    }

    // etape 3 : trier les etudiants par note croissante
    public static List<Etudiant> trierCroissant(
            List<Etudiant> etudiants) {
        return etudiants.stream().sorted(Comparator.comparingDouble(Etudiant::getNote)).toList();
    }

    // trier les etudiants par note decroissante
    public static List<Etudiant> trierDecroissant(
            List<Etudiant> etudiants) {
        return etudiants.stream().sorted(Comparator.comparingDouble(Etudiant::getNote).reversed()).toList();
    }

    // afficher les trois meilleurs etudiants
    public static List<Etudiant> topTrois(
            List<Etudiant> etudiants) {

        return etudiants.stream().sorted(Comparator.comparingDouble(Etudiant::getNote).reversed()).limit(3).toList();
    }

    // recuperer les noms des trois meilleurs
    public static List<String> nomsTopTrois(
            List<Etudiant> etudiants) {
        return topTrois(etudiants).stream().map(Etudiant::getNom).toList();
    }

    // etape 4 : compter les etudiants admis
    public static long compterAdmis(
            List<Etudiant> etudiants) {
        return etudiants.stream().filter(Etudiant::estAdmis).count();
    }

    // verifier s'il existe une note superieure ou egale a 16
    public static boolean existeNoteSuperieure16(
            List<Etudiant> etudiants) {
        return etudiants.stream().anyMatch(e -> e.getNote() >= 16);
    }

    // verifier si toutes les notes sont valides
    public static boolean toutesNotesValides(
            List<Etudiant> etudiants) {
        return etudiants.stream().allMatch(e ->e.getNote() >= 0 && e.getNote() <= 20);
    }

    // chercher le meilleur etudiant
    public static Optional<Etudiant> meilleurEtudiant(
            List<Etudiant> etudiants) {
        return etudiants.stream().max(Comparator.comparingDouble(Etudiant::getNote));
    }

    // calculer la moyenne generale
    public static double calculerMoyenne(
            List<Etudiant> etudiants) {
        return etudiants.stream().mapToDouble(Etudiant::getNote).average().orElse(0.0);
    }

    // etape 5 : separer les admis et les ajournes
    public static Map<Boolean, List<Etudiant>> partitionner(
            List<Etudiant> etudiants) {
        return etudiants.stream().collect(Collectors.partitioningBy(Etudiant::estAdmis));
    }

    // determiner la categorie de chaque etudiant
    public static String categorie(Etudiant e) {

        if (e.getNote() >= 16) {
            return "Tres bien";
        } else if (e.getNote() >= 10) {
            return "Admis";
        } else {
            return "Ajourne";
        }
    }

    // regrouper les etudiants selon leur categorie
    public static Map<String, List<Etudiant>> regrouper(
            List<Etudiant> etudiants) {
        return etudiants.stream().collect(Collectors.groupingBy(AnalyseEtudiants::categorie));
    }

    // defi A : calculer la somme avec reduce
    public static double sommeNotesReduce(
            List<Etudiant> etudiants) {
        return etudiants.stream().map(Etudiant::getNote).reduce(0.0, Double::sum);
    }

    // comparer avec la methode sum
    public static double sommeNotes(
            List<Etudiant> etudiants) {
        return etudiants.stream().mapToDouble(Etudiant::getNote).sum();
    }

    // defi B : fusionner deux groupes avec flatMap
    public static List<Etudiant> fusionnerGroupes(
            List<Etudiant> etudiants) {

        List<Etudiant> groupe1 =etudiants.subList(0, Math.min(3, etudiants.size()));
        List<Etudiant> groupe2 =etudiants.subList(groupe1.size(),etudiants.size());
        return java.util.stream.Stream.of(groupe1, groupe2).flatMap(List::stream).toList();
    }
}
