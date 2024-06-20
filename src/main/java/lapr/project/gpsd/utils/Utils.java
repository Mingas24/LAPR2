/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import lapr.project.gpsd.model.ZipCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Utils {
    /**
     * Logger for error message displaying
     */
    private static final Logger LOGGER = Logger.getLogger( Utils.class.getName() );
    static public String readLineFromConsole(String strPrompt) {
        try {
            System.out.println("\n" + strPrompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public int readIntegerFromConsole(String strPrompt) {
        do {
            try {
                String strInt = readLineFromConsole(strPrompt);

                int iValor = Integer.parseInt(strInt);

                return iValor;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public double readDoubleFromConsole(String strPrompt) {
        do {
            try {
                String strDouble = readLineFromConsole(strPrompt);

                double dValor = Double.parseDouble(strDouble);

                return dValor;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Date readDateFromConsole(String strPrompt) {
        do {
            try {
                String strDate = readLineFromConsole(strPrompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public boolean confirma(String sMessage) {
        String strConfirma;
        do {
            strConfirma = Utils.readLineFromConsole("\n" + sMessage + "\n");
        } while (!strConfirma.equalsIgnoreCase("s") && !strConfirma.equalsIgnoreCase("n"));

        return strConfirma.equalsIgnoreCase("s");
    }

    static public Object showsAndSelects(List list, String sHeader) {
        apresentaLista(list, sHeader);
        return selecionaObject(list);
    }

    static public int apresentaESelecionaIndex(List list, String sHeader) {
        apresentaLista(list, sHeader);
        return selecionaIndex(list);
    }

    static public void apresentaLista(List list, String sHeader) {
        System.out.println(sHeader);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println("");
        System.out.println("0 - Cancelar");
    }

    static public Object selecionaObject(List list) {
        String opcao;
        int nOpcao;
        do {
            opcao = Utils.readLineFromConsole("Introduza opção: ");
            nOpcao = new Integer(opcao);
        } while (nOpcao < 0 || nOpcao > list.size());

        if (nOpcao == 0) {
            return null;
        } else {
            return list.get(nOpcao - 1);
        }
    }

    static public int selecionaIndex(List list) {
        String opcao;
        int nOpcao;
        do {
            opcao = Utils.readLineFromConsole("Introduza opção: ");
            nOpcao = new Integer(opcao);
        } while (nOpcao < 0 || nOpcao > list.size());

        return nOpcao - 1;
    }

    /**
     * Calculates the shortest distance over the earth’s surface in metres
     * Obtained from https://www.movable-type.co.uk/scripts/latlong.html
     *
     * @param lat1 first latitude
     * @param lon1 first longitude
     * @param lat2 second latitude
     * @param lon2 second longitude
     * @return distance
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371e3;
        double theta1 = Math.toRadians(lat1);
        double theta2 = Math.toRadians(lat2);
        double deltaTheta = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);
        double a = Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2) + Math.cos(theta1) * Math.cos(theta2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c; // distância em metros
        return d;
    }
    
    /**
     * Calculates the shortest distance over the earth’s surface in metres
     * Obtained from https://www.movable-type.co.uk/scripts/latlong.html
     *
     * @param zipCodeA
     * @param zipCodeB
     * @return distance
     */
    public static double distance(String zipCodeA, String zipCodeB) {
        Float[] temp;
        temp = getLatAndLon(zipCodeA);
        double lat1 = temp[0]; 
        double lon1 = temp[1];
        temp = getLatAndLon(zipCodeA);
        double lat2 = temp[0]; 
        double lon2 = temp[1];        
        final double R = 6371e3;
        double theta1 = Math.toRadians(lat1);
        double theta2 = Math.toRadians(lat2);
        double deltaTheta = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);
        double a = Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2) + Math.cos(theta1) * Math.cos(theta2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c; // distância em metros
        return d;
    }
    
    public static Float[] getLatAndLon(String zipCode) {
        String[] temp = zipCode.split("-");
        Float[] returner = new Float[2];
        boolean endFlag=true;
        if (temp.length == 2) {
            int zip1integer = Integer.parseInt(temp[0].trim());
            int zip2integer = Integer.parseInt(temp[1].trim());
            int zipFile1, zipFile2;
            String fileName = "zipCodes.csv";
            File file = new File(fileName);

            try {
                Scanner inputStream = new Scanner(file);
                while (inputStream.hasNext() && endFlag) {
                    String data = inputStream.nextLine();
                    String[] line = data.split(";");
                    zipFile1 = Integer.parseInt(line[7]);
                    zipFile2 = Integer.parseInt(line[8]);
                    if (zipFile1 == zip1integer) {
                        if (zipFile2 == zip2integer) {
                            returner[0] = Float.parseFloat(line[10]);
                            returner[1] = Float.parseFloat(line[11]);
                            endFlag=false;
                        }
                    }
                }
                inputStream.close();
            } catch (FileNotFoundException e) {
                LOGGER.log(Level.SEVERE, "File '" + file.getName() + "' at '" + file.getPath() + "' not found. Check the directory and try again.", e);
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Wrong zip code format. Please use the '0000-000' format.");
        }
        return returner;
    }
}


            /*
            Cell zipCodeCell1, zipCodeCell2, latitudeCell, longitudeCell;
            try{
                Workbook wb= Workbook.getWorkbook(file);
                Sheet s=wb.getSheet(0);
                int col=s.getColumns();
                int row=s.getRows();
                for(int i=1; i<row; i++){
                    zipCodeCell1=s.getCell(7, i);
                    zipCodeCell2=s.getCell(8,i);
                    zipFile1=Integer.parseInt(zipCodeCell1.getContents());
                    zipFile2 = Integer.parseInt(zipCodeCell2.getContents());
                    if (zipFile1 == zip1integer) {
                        System.out.println("ENCONTRA PRIMEIRO ZIP");
                        if (zipFile2 == zip2integer) {
                            System.out.println("ENCONTRA CODIGO");
                            latitudeCell=s.getCell(10, i);
                            longitudeCell=s.getCell(11,i);
                            returner[0] = Float.parseFloat(latitudeCell.getContents()) ;
                            returner[1] = Float.parseFloat(longitudeCell.getContents());
                            return returner;
                        }
                    }

                }
            }catch(FileNotFoundException e){
                System.out.println("FILE NOT FOUND");
                System.out.println(e.getMessage());
            }catch(Exception e){
                System.out.println("RANDOM EXCEPTION");
                e.printStackTrace();
            }
            return returner;
        }
        return null;
    }

             */

