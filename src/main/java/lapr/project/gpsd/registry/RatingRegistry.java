package lapr.project.gpsd.registry;

import java.util.List;
import lapr.project.gpsd.model.Rating;

/**
 *
 * @author Gonçalo Pinto (1180987)
 */
public class RatingRegistry {

    private List<Rating> ratings;

    public RatingRegistry(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public RatingRegistry() {
    }

    public List<Rating> getRatings() {
        return this.ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Rating newRating(int rate) {
        return new Rating(rate);
    }

    public boolean validateRating(Rating rat) {
        return !this.ratings.contains(rat);
    }

    public boolean addRating(Rating rat) {
        return this.ratings.add(rat);
    }
    
    public boolean registerRating(Rating rat) {
        if (validateRating(rat))
            return addRating(rat);
        else
            return false;
    }
}
