/**
 * @author <Oleksandr Maister>
 * Matrikelnummer: a1277671
 */

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FahrzeugClient {

    public static void main(String[] args)  {

        FahrzeugManagement fahrzeugManagement = new FahrzeugManagement(args[0]);
        //System.out.println(args[0]);
        switch (args[1]){
            case "show" :
                if(args.length > 3){
                    throw new IllegalArgumentException("Error: Parameter ungueltig.");
                } else if(args.length == 2){
                    fahrzeugManagement.getDatenAllerFahrzeuge();
                }else if(args.length == 3){
                    fahrzeugManagement.getDatenEinesFahrzeug(Integer.parseInt(args[2]));
                }
                break;

            case "add" :
                if (args.length != 9 && args[2].equals("pkw")){
                    try{
                        throw new IllegalArgumentException("Error: Parameter ungueltig.");
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                }
                if (args.length != 8 && args[2].equals("lkw")) {
                    try {
                        throw new IllegalArgumentException("Error: Parameter ungueltig.");
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                }

                if (args[2].equals("pkw")) {
                    try {
                        Pkw pkw = new Pkw(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]), Integer.parseInt(args[8]));
                        fahrzeugManagement.addFahrzeug(pkw);
                    }catch (ArrayIndexOutOfBoundsException e){
                        //System.out.println();
                    }

                } else if (args[2].equals("lkw")) {
                    Lkw lkw = new Lkw(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]));
                    fahrzeugManagement.addFahrzeug(lkw);
                } else {
                    try{
                        throw new IllegalArgumentException("Error: Parameter ungueltig.");
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                }
                break;

/*
                if(args[2].equals("lkw")){
                    Lkw lkw = new Lkw(Integer.parseInt(args[3]), args[4], args[5], Integer.parseInt(args[6]), Double.parseDouble(args[7]));
                    fahrzeugManagement.addFahrzeug(lkw);
                }
                if(args[2].equals("pkw")){
                    Pkw pkw = new Pkw(new Integer(args[3]), args[4], args[5], new Integer(args[6]), Double.parseDouble(args[7]), new Integer(args[8]));
                    fahrzeugManagement.addFahrzeug(pkw);
                }
                break;*/

            case "del" :
                if(args.length != 3){
                    throw new IllegalArgumentException("Error: Parameter ungueltig.");
                }
                fahrzeugManagement.deleteFahrzeug(Integer.parseInt(args[2]));
                break;

            case "count" :
                if(args.length > 3){
                    throw new IllegalArgumentException("Error: Parameter ungueltig.");
                }
                if(args.length == 2){
                    System.out.println(fahrzeugManagement.getGesamtAnzahlAllerFahrzeuge());
                }
                if(args.length == 3 && args[2].equals("lkw")){
                    System.out.println(fahrzeugManagement.getGesamtAnzahlAllerLKW());
                }
                if(args.length == 3 && args[2].equals("pkw")){
                    System.out.println(fahrzeugManagement.getGesamtAnzahlAllerPKW());
                }
                break;

            case "meanprice" :
                if(args.length != 2){
                    throw new IllegalArgumentException("Error: Parameter ungueltig.");
                }
                double mean = fahrzeugManagement.getDurchschnittspreis();
                System.out.format("%.2f%n", mean);
                break;

            case "oldest" :
                if(args.length != 2){
                    throw new IllegalArgumentException("Error: Parameter ungueltig.");
                }
                for (int i : fahrzeugManagement.getIdAeltestenFahrzeug()){
                    System.out.println("Id: " + i);
                }
                break;
        }
    }
}
