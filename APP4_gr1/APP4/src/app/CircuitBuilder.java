package app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import electronique.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CircuitBuilder {

    private final static char fSep = File.separatorChar;
    private static final String pathIn = System.getProperty("user.dir") + fSep + "APP4_gr1" + fSep + "APP4" + fSep + "src" + fSep + "donnees" + fSep + "fichiers_json" + fSep;

    public CircuitBuilder() {
    }

    public Composant construireCircuit(String cheminFichier){

        ObjectMapper mapper = new ObjectMapper();
        Composant circuit = null;

        cheminFichier = pathIn + cheminFichier;
        try {
            JsonNode donneesCircuit = mapper.readTree(new File(cheminFichier));

                JsonNode circuitLecture = donneesCircuit.get("circuit");

                return lireComposant(circuitLecture);

        } catch (IOException e) {
            System.out.println("Erreur de lecture : " + e.getMessage());
        }

        return null;
    }

    private Composant lireComposant(JsonNode node){
        String type = node.get("type").asText();

        if("resistance".equals(type)){
            return new Resistance(node.get("valeur").asDouble());
        }

        else if ("serie".equals(type)){
            ArrayList<Composant> composants = new ArrayList<>();

            for (JsonNode compoosantNode : node.get("composants")){
                composants.add(lireComposant(compoosantNode));
            }
            return new CircuitSerie(composants);
        }

        else if ("parallele".equals(type)){
            ArrayList<Composant> composants = new ArrayList<>();

            for (JsonNode compoosantNode : node.get("composants")){
                composants.add(lireComposant(compoosantNode));
            }
            return new CircuitParallele(composants);
        }

        throw new IllegalArgumentException("Type de composant incconu : " + type);
    }
}
