package io.bkrzyminski.moviecatalogservice.models;

import lombok.Data;

import java.util.List;

/**
 * Model class
 *
 * @author bkrzyminski
 */
@Data
public class UserRating {
    private List<Rating> ratings;
}
