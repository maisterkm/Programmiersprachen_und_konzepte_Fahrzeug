/**
 * @author <Oleksandr Maister>
 * Matrikelnummer: a1277671
 */

public class Pkw extends Fahrzeug {
    private int serviceJahr;

    private static final long serialVersionUID = 1L;

    public Pkw(int id, String marke, String modell, int baujahr, double grundpreis, int serviceJahr) {
        super(id, marke, modell, baujahr, grundpreis);
        this.serviceJahr = serviceJahr;
    }

    public void setServiceJahr(int serviceJahr) throws Exception {
        if(laufendesJahr < serviceJahr || serviceJahr > laufendesJahr){
            throw new Exception("Error: Servicejahr ungueltig.");
        }
        this.serviceJahr = serviceJahr;
    }

    double getRabatt(){
        double prozentRabatt = (getAlter() * grundRabattProJahr) + (laufendesJahr - serviceJahr) * 0.02;
        if((getGrundpreis()*0.15) < prozentRabatt*getGrundpreis()){
            return (0.15*getGrundpreis());
        }else{ return (prozentRabatt*getGrundpreis()); }
    }

    @Override
    public String toString() {
        return  "Typ:         PKW" +
                "\nId:          " + getId() +
                "\nMarke:       " + getMarke() +
                "\nModell:      " + getModell() +
                "\nBaujahr:     " + getBaujahr() +
                "\nGrundpreis:  " + df.format(getGrundpreis()) +
                "\nServicejahr: " + serviceJahr +
                "\nPreis:       " + df.format(getPreis()) + "\n";
    }
}
