package ma.emsi.gestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Gestion des etudiants — EMSI");
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(new Etudiant(1, "Amina", 15));
        etudiants.add(new Etudiant(2, "Youssef", 9));
        etudiants.add(new Etudiant(3, "Sara", 12.5));
        Personne personne = etudiants.get(0);
        System.out.println("Role : " + personne.role());

        Evaluatable evaluation = etudiants.get(0);
        System.out.println("Amina admise : " + evaluation.estAdmis());
        double somme = 0;
        System.out.println("\nListe des etudiants :");

        for (Etudiant e : etudiants) {
            System.out.println(e.getNom() + " - Note : " + e.getNote());
            somme += e.getNote();}

        if (etudiants.isEmpty()) {
            System.out.println("Aucun etudiant.");
        } else {
            System.out.printf("Moyenne : %.2f%n",somme / etudiants.size());
        }

        System.out.println("\nEtudiants admis :");
        for (Etudiant e : etudiants) {
            if (e.estAdmis()) {
                System.out.println(e.getNom());
            }
        }
        Etudiant resultat = rechercherParNom(etudiants, " amina ");

        System.out.println("\n Recherche : " + (resultat != null ? resultat.getNom(): "Etudiant introuvable"));

        try {
            Etudiant test = new Etudiant(4, "Test", -2);
        } catch (IllegalArgumentException e) {
            System.out.println("\nErreur : " + e.getMessage());
        }
        testerHashSetEtMap(etudiants);
        testerTris(etudiants);
        testerSaisie();
    }
    public static Etudiant rechercherParNom(
            List<Etudiant> liste, String nom) {
        if (nom == null) {return null;}
        for (Etudiant e : liste) {
            if (e.getNom().trim().equalsIgnoreCase(nom.trim())) {
                return e;
            }
        }

        return null;
    }
    public static void testerHashSetEtMap(
            List<Etudiant> etudiants) {
        java.util.Set<Etudiant> ensemble =
                new java.util.HashSet<>();

        ensemble.add(new Etudiant(1, "Amina", 15));
        ensemble.add(new Etudiant(1, "Amina bis", 18));

        System.out.println("\nTaille du HashSet : " + ensemble.size());

        java.util.Map<Integer, Etudiant> dictionnaire =
                new java.util.HashMap<>();
        for (Etudiant e : etudiants) {
            dictionnaire.put(e.getId(), e);
        }

        System.out.println("Cle 2 : " + dictionnaire.get(2).getNom());
    }
    public static void testerTris(List<Etudiant> etudiants) {
        List<Etudiant> parId =
                new ArrayList<>(etudiants);
        java.util.Collections.sort(parId);
        System.out.println("\nTri par identifiant :");
        for (Etudiant e : parId) {
            System.out.println(e.getNom());
        }
        List<Etudiant> parNote =
                new ArrayList<>(etudiants);
        java.util.Collections.sort(
                parNote,
                new java.util.Comparator<Etudiant>() {
                    @Override
                    public int compare(Etudiant a, Etudiant b) {
                        return Double.compare(a.getNote(), b.getNote());
                    }
                }
        );
        System.out.println("\nTri par note croissante :");
        for (Etudiant e : parNote) {
            System.out.println(e.getNom());
        }
    }
    public static void testerSaisie() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEntrez un identifiant: ");
        String texte = scanner.nextLine();
        try {
            int id = Integer.parseInt(texte);
            if (id <= 0) {
                System.out.println("L'identifiant doit etre positif");
            } else {
                System.out.println("Identifiant saisi :" + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Erreur : entrez un nombre entier valide");
        }
    }
}
