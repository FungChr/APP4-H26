package electronique;

import java.util.ArrayList;

public class CircuitSerie extends Circuit{
    public CircuitSerie(ArrayList<Composant> composants) {
        super(composants);
    }

    @Override
    public double calculerResistance() {
        double resistance = 0;
        for (Composant c :this.composants){
            resistance += c.calculerResistance();
        }
        return resistance;
    }
}
