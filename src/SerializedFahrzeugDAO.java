/**
 * @author <Oleksandr Maister>
 * Matrikelnummer: a1277671
 */

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class SerializedFahrzeugDAO implements FahrzeugDAO {
    String dateiname;
    private ArrayList<Fahrzeug> fahrzeugList;

    public SerializedFahrzeugDAO(String dateiname) {
        this.dateiname = dateiname;
        deserialezeFahrzeug();
    }

    void serializeFahrzeuge() {
        try {
            File file = new File(dateiname);
            if(file.getParentFile() != null) { file.getParentFile().mkdir(); }
            FileOutputStream fos = new FileOutputStream(dateiname);
            ObjectOutputStream writer = new ObjectOutputStream(fos);
            writer.writeObject(fahrzeugList);
            writer.close();
            fos.close();
        } catch (Exception e) {
            System.err.println("Fehler bei Serialisierung: + e.getMessage");
            System.exit(1);
        }

    }

    void deserialezeFahrzeug(){
        File file = new File(dateiname);
        if(file.exists()){
            try{
                FileInputStream fis = new FileInputStream(dateiname);
                ObjectInputStream reader = new ObjectInputStream(fis);
                fahrzeugList = (ArrayList<Fahrzeug>) reader.readObject();
                reader.close();
                fis.close();
            }catch (Exception e) {
                System.err.println("Fehler bei deserialisierung: " + e.getMessage());
                System.exit(1);
            }
        }else{
            fahrzeugList = new ArrayList<Fahrzeug>();
        }
    }

    @Override
    public List<Fahrzeug> getFahrzeugList(){
        return fahrzeugList;
    }

    @Override
    public Fahrzeug getFahrzeugbyId(int id){
        for (Fahrzeug f : fahrzeugList){
            if(id == f.getId()){ return f; }
        }
        return null;
    }

    @Override
    public void speichereFahrzeug(Fahrzeug fahrzeug) {
        for (Fahrzeug f : fahrzeugList) {
            if(f.getId() == fahrzeug.getId()){
                throw new IllegalArgumentException();
            }
        }

        fahrzeugList.add(fahrzeug);
        serializeFahrzeuge();
    }

    @Override
    public void loescheFahrzeug(int id){
        Fahrzeug fahrzeug = null;

        for(Fahrzeug f : fahrzeugList){
            if(f.getId() == id){
                fahrzeug = f;
            }
        }
        if(fahrzeug == null){
            throw new IllegalArgumentException();
        }

        fahrzeugList.remove(fahrzeug);
        serializeFahrzeuge();
    }

}
