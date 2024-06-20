package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;

public class Availability implements Comparable<Availability>{
    private Date initDate;
    private Time initTime;
    private Date endDate;
    private Time endTime;
    
    private final Time END_AVAILABILITY_PERIOD = new Time(0, 0);
    private final Time START_AVAILABILITY_PERIOD = new Time(6, 0);

    /**
     * Constructor
     * @param initDate Inicial date
     * @param initTime Iniciat time
     * @param endDate End date
     * @param endTime End time
     */
    public Availability(Date initDate, Time initTime, Date endDate, Time endTime){
        setInitDate(initDate);
        setInitTime(initTime);
        setEndDate(endDate);
        setEndTime(endTime);
    }

    public Date getInitDate() {
        return initDate;
    }

    public Time getInitTime() {
        return initTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Time getEndTime() {
        return endTime;
    }
    
    public final void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public final void setInitTime(Time initTime) {
        if (initTime.isBetween(END_AVAILABILITY_PERIOD, START_AVAILABILITY_PERIOD)) {
            throw new IllegalArgumentException("Invalid Initial Time!");
        }
        this.initTime = initTime;
    }

    public final void setEndTime(Time endTime) {
        if (endTime.isBetween(END_AVAILABILITY_PERIOD, START_AVAILABILITY_PERIOD)) {
            throw new IllegalArgumentException("Invalid End Time!");
        }
        this.endTime = endTime;
    }

    public final void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    @Override
    public String toString() {
        return "Initial date: " + initDate.toString() + "\nEnd date: " + endDate.toString() + "\nInitial time: " + initTime + "\nEnd time: " + endTime;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Availability otherAvailability = (Availability) otherObject;
        
        return initDate == otherAvailability.initDate &&
               initTime == otherAvailability.initTime &&
               endDate == otherAvailability.endDate &&
               endTime == otherAvailability.endTime;
    }

    @Override
    public int compareTo(Availability otherAvailability) {
        return initDate.compareTo(otherAvailability.initDate);
    }
    
}