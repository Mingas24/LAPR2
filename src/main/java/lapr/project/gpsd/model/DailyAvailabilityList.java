package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;

public class DailyAvailabilityList {
    private List<Availability> dailyAvailabilityList;
    
    /**
     * Empty contructor.
     */
    public DailyAvailabilityList() {
        dailyAvailabilityList = new ArrayList<>();
    }
    
    /**
     * Constructor that receives the Daily Availability List.
     * @param dailyAvailaList - Daily Availability List.
     */
    public DailyAvailabilityList(List<Availability> dailyAvailaList) {
        this.dailyAvailabilityList = new ArrayList<>(dailyAvailaList);
    }
    
    /**
     * Gets the availibility saved in the list with the index
     * @param index
     * @return availability
     */
    public Availability getAvailability(int index) {
        if(index<this.dailyAvailabilityList.size())
            return dailyAvailabilityList.get(index);
        throw new IllegalArgumentException("Invalide index.");
    }
    
    /**
     * 
     * @param index
     * @param availability
     * @return 
     */
    public Availability setAvailability(int index, Availability availability) {
        if(!dailyAvailabilityList.contains(availability)){
            return dailyAvailabilityList.set(index, availability);
        }

        throw new IllegalArgumentException("Availability already inserted.");
    }
    
    /**
     *
     * @param initDate Inicial date
     * @param initTime Iniciat time
     * @param endDate End date
     * @param endTime End time
     * @return 
     */
    public Availability newAvailability(Date initDate, Time initTime, Date endDate, Time endTime){
        return new Availability(initDate, initTime, endDate, endTime);
    }
    
    /**
     * metodo que recebo por parametro um periodo de disponibilidades diaria e regista o
     * para isso, chama um metodo que valida a disponibilidade
     * e outro que a adiciona aos periodos ja existentes
     * 
     * @param availability disponibilidade diaria
     * @return 
     */
    public boolean registerAvailability(Availability availability) {
        if (validatesAvailability(availability)) {
            return addAvailability(availability);
        }
        return false;
    }
    
    /**
     * validates availability
     * 
     * @param availability availability
     * @return
     */
    public boolean validatesAvailability(Availability availability) {
        return !dailyAvailabilityList.contains(availability);
    }
    
    /**
     * metodo que recebe por parametro um periodo de disponibilidade diaria
     * e o adiciona aos periodos pendentes ja existentes
     *
     * @param availability availability
     * @return 
     */
    public boolean addAvailability(Availability availability) {
        return dailyAvailabilityList.add(availability);
    }
    
    public boolean removeAvailability(Availability availability) {
        return dailyAvailabilityList.remove(availability);
    }
    
    public int getIndex(Availability availability) {
        return dailyAvailabilityList.indexOf(availability);
    }
    
    public void clear() {
        dailyAvailabilityList.clear();
    }
    
    public boolean isEmpty() {
        return dailyAvailabilityList.isEmpty();
    }
    
    @Override
    public String toString() {
        List<Availability> copia = new ArrayList<>(dailyAvailabilityList);
        Collections.sort(copia);

        StringBuilder s = new StringBuilder();
        for (Availability disponibilidade : copia) {
            s.append(disponibilidade);
            s.append("\n");
        }
        
        return s.toString().trim();
    }
    
    @Override
    public boolean equals(Object outroObjeto) {
        if (this == outroObjeto) {
            return true;
        }
        if (outroObjeto == null || getClass() != outroObjeto.getClass()) {
            return false;
        }
        DailyAvailabilityList outraListaDisponibilidadesDiaria = (DailyAvailabilityList) outroObjeto;

        List<Availability> copiaThis = new ArrayList<>(dailyAvailabilityList);
        List<Availability> copiaOutra = new ArrayList<>(outraListaDisponibilidadesDiaria.dailyAvailabilityList);

        return copiaThis.equals(copiaOutra);
    }

    /**
     *
     * @return
     */
    public List<Availability> getListDispD() {
        return dailyAvailabilityList;
    }
    
    public Time[] getAvailabilityInDate(Date date){
        Time[] r = new Time[2];
        r[0] = new Time(0, 0);
        r[1] = new Time(0, 0);
        for(Availability a : this.dailyAvailabilityList){
            if(date.isBetween(a.getInitDate(), a.getEndDate())){
                r[0] = a.getInitTime();
                r[1] = a.getEndTime();
                return r;
            }
        }   
        return r;  
    }
}