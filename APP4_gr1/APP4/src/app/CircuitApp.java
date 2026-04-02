package app;

import java.util.Scanner;

public class CircuitApp {
    public CircuitApp() {
        String entree = "";
        CircuitBuilder circuitBuilder = new CircuitBuilder();
        do {
            entree = lireString("•\t[R] Tester un autre fichier : Relance le cycle de sélection" + "\n" + "•\t[Q] Quitter : Ferme proprement l'application avec un message de confirmation.");
            if (!validerEntree(entree)){
                System.out.println("Entrée invalide, entrez «R» pour relancer le cycle de sélection ou «Q» pour quitter l'application.");
            } else {
                System.out.println("Résistance équivalente calculée : " + circuitBuilder.construireCircuit("complexe_industriel_zone_nord.json") + "Ω");
            }
        } while (entree.equalsIgnoreCase("Q"));
    }

    private String lireString(String question){
        System.out.println(question);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private boolean validerEntree(String entree){
        return (entree.equalsIgnoreCase("R")||entree.equalsIgnoreCase("Q"));
    }

    public static void main(String[] args) {
        new CircuitApp();
    }
}
