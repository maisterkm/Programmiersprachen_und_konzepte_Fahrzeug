/**
 * @author <Oleksandr Maister>
 * Matrikelnummer: a1277671
 */

import java.util.ArrayList;
import java.util.List;

public class FahrzeugManagement {
    FahrzeugDAO fahrzeugDAO;

    public FahrzeugManagement(String dateiname) {
        fahrzeugDAO = new SerializedFahrzeugDAO(dateiname);
    }

    // Alle Daten aller Fahrzeuge bereitstellen
    public void getDatenAllerFahrzeuge(){
        for (Fahrzeug f : fahrzeugDAO.getFahrzeugList()) {
            System.out.println(f.toString());
        }
    }

    // Alle Daten eines Fahrzeugs bereitstellen
    public void getDatenEinesFahrzeug(int id){
        System.out.println(fahrzeugDAO.getFahrzeugbyId(id).toString());
    }

    // Neues Fahrzeug hinzufügen
    public void addFahrzeug(Fahrzeug f){
        try{
            fahrzeugDAO.speichereFahrzeug(f);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // Bestehendes Fahrzeug löschen
    public void deleteFahrzeug(int id){
        try{
            fahrzeugDAO.loescheFahrzeug(id);
        }catch(Exception e){
            System.out.println("Error: Fahrzeug nicht vorhanden. (id=" + id + ")" );
        }
    }

    // Gesamtzahl aller Fahrzeuge ermitteln
    public int getGesamtAnzahlAllerFahrzeuge(){
        return fahrzeugDAO.getFahrzeugList().size();
    }

    // Gesamtzahl aller PKWs ermitteln
    public int getGesamtAnzahlAllerPKW(){

        int gesamtzahlPKW = 0;
        for (Fahrzeug f : fahrzeugDAO.getFahrzeugList() ){
            if(f instanceof Pkw){
                gesamtzahlPKW++;
            }
        }
        return gesamtzahlPKW;
    }

    // Gesamtzahl aller LKWs ermitteln
    public int getGesamtAnzahlAllerLKW(){

        int gesamtzahlLKW = 0;
        for (Fahrzeug f : fahrzeugDAO.getFahrzeugList() ){
            if(f instanceof Lkw){
                gesamtzahlLKW++;
            }
        }
        return gesamtzahlLKW;
    }

    // Durchschnittspreis aller Fahrzeuge ermitteln
    public double  getDurchschnittspreis(){

        double totalPreis = 0.0;

        for (Fahrzeug f : fahrzeugDAO.getFahrzeugList()) {
            totalPreis += f.getPreis();
        }
        return totalPreis/fahrzeugDAO.getFahrzeugList().size();
    }

    // Id(s) des(r) ältesten Fahrzeugs(e) ermitteln
    public List<Integer> getIdAeltestenFahrzeug(){

        if (fahrzeugDAO.getFahrzeugList().isEmpty()) {
            System.err.println("Error: Fahrzeuge nicht vorhanden");
            System.exit(1);
        }

        List<Integer> ArrayOfOldestFahrzeuge = new ArrayList<Integer>();
        Fahrzeug aeltesteFahrzeug = fahrzeugDAO.getFahrzeugList().get(0);

        for(Fahrzeug f : fahrzeugDAO.getFahrzeugList()){
            if(f.getAlter() > aeltesteFahrzeug.getAlter()){
                aeltesteFahrzeug = f;
            }
        }
        ArrayOfOldestFahrzeuge.add(aeltesteFahrzeug.getId());
        for(Fahrzeug f : fahrzeugDAO.getFahrzeugList()) {
            if( (aeltesteFahrzeug.getAlter() == f.getAlter()) && (aeltesteFahrzeug.getId() != f.getId()) ){
                ArrayOfOldestFahrzeuge.add(f.getId());
            }
        }

        return ArrayOfOldestFahrzeuge;
    }

}
