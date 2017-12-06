/**
 * @author <Oleksandr Maister>
 * Matrikelnummer: a1277671
 */

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;


public abstract class Fahrzeug implements Serializable {
    private static final long serialVersionUID = 1L;
    final double grundRabattProJahr = 0.05;
    static public int laufendesJahr = getCurrentYear();
    public static DecimalFormat df = getDecimalFormat();

    private int id;
    private String marke;
    private String modell;
    private int baujahr;
    private double grundpreis;

    abstract double getRabatt();
    abstract public String toString();

    public Fahrzeug(int id, String marke, String modell, int baujahr, double grundpreis) {
        this.id = id;
        this.marke = marke;
        this.modell = modell;
        setBaujahr(baujahr);
        this.grundpreis = grundpreis;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(int baujahr) throws IllegalArgumentException {
        if(baujahr <= laufendesJahr){
            this.baujahr = baujahr;
        }else{
            try{
                throw new IllegalArgumentException("Error: Baujahr ungueltig.");
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public double getGrundpreis() {
        return grundpreis;
    }

    public void setGrundpreis(double grundpreis) throws IllegalArgumentException {
        if(grundpreis > 0){
            this.grundpreis = grundpreis;
        }else{
            throw new IllegalArgumentException("Error: Grundpreis ungueltig.");
        }
    }


    int getAlter(){ return laufendesJahr - baujahr; };

    private static int getCurrentYear() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    double getPreis(){
        return getGrundpreis() - getRabatt();
    }

    public static DecimalFormat getDecimalFormat() {
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
        dfs.setDecimalSeparator('.');
        return new DecimalFormat("0.00", dfs);
    }
}
