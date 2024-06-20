package lapr.project.gpsd.model;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import lapr.project.gpsd.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExternalServiceByCsv {

    /**
     * Logger for error message displaying
     */
    private static final Logger LOGGER = Logger.getLogger( ExternalServiceByCsv.class.getName() );
    /**
     *  Name of the file containing the zip codes
     */
    static String fileName = "zipCodes.csv";

    /**
     * Constructor
     */
    public ExternalServiceByCsv() {
        //empty constructor
    }

    /**
     * Method that returns a list with the postal codes within a certain radius of a center postal code
     * File structure:
     * index 7- postal code (part 1, i.e. 4000)
     * index 8- postal code (part 2, i.e. 000)
     * index 10- latitude
     * index 11- longitude
     *
     * @param center The postal code at the center
     * @param radius Search radius
     * @return
     */
    public List<ZipCode> obtainCoverage(ZipCode center, double radius) {
        float latitudeCenter = center.getLatitude();
        float longitudeCenter = center.getLongitude();
        float latitudeFile, longitudeFile;
        ZipCode zipAdd;
        List<ZipCode> coverage = new ArrayList<>();
        coverage.add(center);
        File file = new File(fileName);
        try {
            Scanner inputStream = new Scanner(file);
            try {
                while (inputStream.hasNext()) {
                    String data = inputStream.nextLine();
                    String[] line = data.split(";");
                    latitudeFile = Float.parseFloat(line[10]);
                    longitudeFile = Float.parseFloat(line[11]);
                    if (Utils.distance(latitudeCenter, longitudeCenter, latitudeFile, longitudeFile) <= radius) {
                        String temp = String.format("%03d", Integer.parseInt(line[8]));
                        String zipCode = line[7] + "-" + temp;
                        zipAdd = new ZipCode(zipCode, latitudeFile, longitudeFile);
                        if (!coverage.contains(zipAdd)) {
                            coverage.add(new ZipCode(zipCode, latitudeFile, longitudeFile));
                        }
                    }
                }
            }finally {
                inputStream.close();
                return coverage;
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE,"File '" + file.getName() + "' at '" + file.getPath() + "' not found. Check the directory and try again.",e);
            e.printStackTrace();
        }
        return coverage;
    }

}

