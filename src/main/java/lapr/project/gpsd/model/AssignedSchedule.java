/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;

/**
 *
 * @author Jee ^^
 */
public class AssignedSchedule implements Comparable<AssignedSchedule>{
    private Date day;
    private Time startTime;
    private Time endTime;

    private final Time START_SCHEDULE_PERIOD = new Time(6, 0);
    
    /**
     * Constructor
     * @param day
     * @param startTime
     */
    public AssignedSchedule(Date day, Time startTime, Time endTime){
        setDay(day);
        setStartTime(startTime);
        setEndTime(endTime);
    }

    /**
     * Obtains the assigned schedule assigned day
     * @return assigned day
     */
    public Date getDay() {
        return day;
    }

    /**
     * Obtains the assigned schedule assigned start time
     * @return assigned start time
     */
    public Time getStartTime() {
        return startTime;
    }

    
    /**
     * sets the object assigned day to another day
     * @param day new assigned day
     */
    public final void setDay(Date day) {
        this.day = day;
    }
    
   /**
     * sets the object assigned start time to another tie
     * @param startTime new assigned start time
     */
    public final void setStartTime(Time startTime) {
        if (startTime.isBetween(START_SCHEDULE_PERIOD, START_SCHEDULE_PERIOD)) {
            throw new IllegalArgumentException("Invalid time!");
        }
        this.startTime = startTime;
    }

    /**
     * Returns a string with all the info of the object
     * @return string with all the info of the object
     */
    @Override
    public String toString() {
        return "Day: " + day.toString() + "\nStart time: " + startTime;
    }

    /**
     * checks if 2 objects are equal
     * @param otherObject other object to compare to
     * @return true if they are equal
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        AssignedSchedule otherSchedule = (AssignedSchedule) otherObject;
        
        return day.equals(otherSchedule.day) &&
               startTime.equals(otherSchedule.startTime);
    }

    /**
     * Compares 2 objects checking which one shoud be the first when they are ordered
     * @param otherAssignedSchedule other object
     * @return
     */
    @Override
    public int compareTo(AssignedSchedule otherAssignedSchedule) {
        return day.diference(otherAssignedSchedule.day);
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
