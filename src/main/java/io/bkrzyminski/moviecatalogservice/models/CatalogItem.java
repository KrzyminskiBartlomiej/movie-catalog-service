package io.bkrzyminski.moviecatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Model class
 *
 * @author bkrzyminski
 */
@Data
@AllArgsConstructor
public class CatalogItem {
    private String name;
    private String description;
    private int rating;
}
