package lapr.project.gpsd.model;

import java.util.Objects;

/**
 *
 * @author Gonçalo Pinto (1180987)
 */
public class Rating {

    private ExecutionOrder execOrd;
    private int rate;

    public Rating(int rate) {
        this.rate = rate;
    }

    public ExecutionOrder getServProv() {
        return execOrd;
    }

    public double getRate() {
        return this.rate;
    }

    public void setExecutionOrder(ExecutionOrder executionOrder) {
        this.execOrd = executionOrder;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rating: " + rate + "\nExecution Order:" + execOrd ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return rate == rating.rate &&
                execOrd.equals(rating.execOrd);
    }
}
