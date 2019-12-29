package io.bkrzyminski.moviecatalogservice.resources;

import io.bkrzyminski.moviecatalogservice.models.CatalogItem;
import io.bkrzyminski.moviecatalogservice.models.Movie;
import io.bkrzyminski.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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

    @Autowired
    private RestTemplate restTemplate;

    @Qualifier("getWebclientBuilder")
    @Autowired
    private WebClient.Builder builder;

    /**
     * Creates new catalogs by communicating with outer services
     *
     * @param userId id of the user
     * @return catalogs created with userId
     */
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);

        return Objects.requireNonNull(ratings).getRatings().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(Objects.requireNonNull(movie).getName(), "Desc", rating.getRating());
                })
                .collect(Collectors.toList());
    }
}
