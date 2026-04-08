package app;

import electronique.Composant;

import java.io.File;
import java.util.Scanner;

public class CircuitApp {
    private final static char fSep = File.separatorChar;
    private static final String cheminDossier = System.getProperty("user.dir") + fSep + "APP4_gr1" + fSep + "APP4" + fSep + "src" + fSep + "donnees" + fSep + "fichiers_json";
    private static final File dossier = new File(cheminDossier);
    File[] fichiersJson = dossier.listFiles();

    public CircuitApp() {
        String entree = "";
        Composant composant = null;
        CircuitBuilder circuitBuilder = new CircuitBuilder();
        int indice = 0;

        do {
            entree = lireString("•\t[R] Tester un autre fichier : Relance le cycle de sélection" + "\n" + "•\t[Q] Quitter : Ferme proprement l'application avec un message de confirmation.");
            validerEntreeRQ(entree);

             if (entree.equalsIgnoreCase("R")) {
                System.out.println("Sélectionnez un fichier :");
                for (int i = 0; i<fichiersJson.length; i++){
                    System.out.println("\t" + (i+1) + " - " + fichiersJson[i].getName());
                }

                do {
                    entree = lireString("Entrez le chiffre correspondant au fichier désiré.");
                }while(!validerChoixFichier(entree));

                int choix = Integer.parseInt(entree);
                composant = circuitBuilder.construireCircuit(fichiersJson[choix-1].getName());
                System.out.println("Résistance équivalente calculée : " + composant.calculerResistance() + " Ω\n");
            }
        } while (!entree.equalsIgnoreCase("Q"));
    }

    private String lireString(String question){
        System.out.println(question);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private boolean validerEntreeRQ(String entree){
        if (!(entree.equalsIgnoreCase("R")||entree.equalsIgnoreCase("Q"))){
            System.out.println("Entrée invalide, entrez «R» pour relancer le cycle de sélection ou «Q» pour quitter l'application.");
            return false;
        }
        return true;
    }

    private boolean validerChoixFichier(String entree){
        //Condiition 1
        try {
            int choix = Integer.parseInt(entree);
        }catch (NumberFormatException e){
            System.out.println("Entrée invalide, entrez un chiffre");
            return false;
        }

        int choix = Integer.parseInt(entree);

        //Condition 2
        if(!(choix > 0 && choix <= fichiersJson.length)){
            System.out.println("Entrée invalide, entrez un des chiffres situés à la gauche des choix");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new CircuitApp();
    }
}
