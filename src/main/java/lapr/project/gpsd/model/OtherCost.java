package lapr.project.gpsd.model;

public class OtherCost{
    private String designation;
    private double value;

    /**
     * Constructor for other costs objects.
     * @param designation
     * @param value
     */
    public OtherCost(String designation, double value) {
        this.designation = designation;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Designation - " + designation +
                "\nValue - " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OtherCost that = (OtherCost) o;
        return Double.compare(that.value, value) == 0 &&
                designation.equals(that.designation);
    }
}
