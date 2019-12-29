package io.bkrzyminski.moviecatalogservice.models;

import java.util.List;

/**
 * Model class
 *
 * @author bkrzyminski
 */
public class UserRating {

    public UserRating() {
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    private List<Rating> ratings;

}
