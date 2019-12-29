package io.bkrzyminski.moviecatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class
 *
 * @author bkrzyminski
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    private String movieId;
    private int rating;
}
