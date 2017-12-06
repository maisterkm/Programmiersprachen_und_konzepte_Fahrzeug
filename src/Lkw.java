/**
 * @author <Oleksandr Maister>
 * Matrikelnummer: a1277671
 */

import java.util.Date;

public class Lkw extends Fahrzeug {

    private static final long serialVersionUID = 1L;

    public Lkw(int id, String marke, String modell, int Baujahr, double grundpreis) {
        super(id, marke, modell, Baujahr, grundpreis);
    }

    double getRabatt(){
        double prozentRabatt = getAlter() * grundRabattProJahr;
        if(getGrundpreis()*0.2 < getGrundpreis()*prozentRabatt){
            return 0.2*getGrundpreis();
        }else { return prozentRabatt*getGrundpreis(); }

    }




    @Override
    public String toString() {
        return "Typ:         LKW" +
                "\nId:          " + getId() +
                "\nMarke:       " + getMarke() +
                "\nModell:      " + getModell() +
                "\nBaujahr:     " + getBaujahr() +
                "\nGrundpreis:  " + df.format(getGrundpreis()) +
                "\nPreis:       " + df.format(getPreis()) + "\n";

    }
}
