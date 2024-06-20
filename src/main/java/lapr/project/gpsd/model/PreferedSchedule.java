package lapr.project.gpsd.model;

import java.util.Objects;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;

public class PreferedSchedule{
    private int order;
    private Date date;
    private Time time;

    /**
     * Construtor
     * @param order
     * @param date
     * @param time
     */
    public PreferedSchedule(int order, Date date, Time time){
        this.order = order;
        this.date = date;
        this.time = time;
    }
    
    public Date getDate(){
        return date;
    }

    public Time getTime(){
        return time;
    }

    @Override
    public String toString(){
        return "Date = " + date +
               "\nTime = " + time;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PreferedSchedule otherPrefSchedule = (PreferedSchedule) o;
        return order == otherPrefSchedule.order &&
               time.equals(otherPrefSchedule.time)&&
                date.equals(otherPrefSchedule.date);
    }
}