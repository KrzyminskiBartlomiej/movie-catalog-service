package io.bkrzyminski.moviecatalogservice.resources;

import io.bkrzyminski.moviecatalogservice.models.CatalogItem;
import io.bkrzyminski.moviecatalogservice.models.Movie;
import io.bkrzyminski.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Main resource for movie catalog service
 *
 * @author bkrzyminski
 */
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    public static final String RATING_SERVICE_URI = "http://localhost:8083/ratingsdata/users/";
    public static final String MOVIE_SERVICE_URI = "http://localhost:8082/movies/";
    public static final String TEST_DESCRIPTION = "Just a normal description";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Creates new catalogs by communicating with outer services
     *
     * @param userId id of the user
     * @return catalogs created with userId
     */
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        UserRating ratings = restTemplate.getForObject(RATING_SERVICE_URI + userId, UserRating.class);

        return Objects.requireNonNull(ratings).getRatings().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject(MOVIE_SERVICE_URI + rating.getMovieId(), Movie.class);
                    return new CatalogItem(Objects.requireNonNull(movie).getName(), TEST_DESCRIPTION, rating.getRating());
                })
                .collect(Collectors.toList());
    }

}
