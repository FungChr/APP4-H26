package electronique;

import java.util.ArrayList;

public class CircuitParallele extends Circuit{
    public CircuitParallele(ArrayList<Composant> composants) {
        super(composants);

    }

    @Override
    public double calculerResistance() {
        double resistance = 0;
        for (Composant c : composants){
            resistance += Math.pow(c.calculerResistance(),-1);
        }
        return Math.pow(resistance,-1);
    }
}
