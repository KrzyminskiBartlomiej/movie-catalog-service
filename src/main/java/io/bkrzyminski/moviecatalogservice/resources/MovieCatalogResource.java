package io.bkrzyminski.moviecatalogservice.resources;

import io.bkrzyminski.moviecatalogservice.models.CatalogItem;
import io.bkrzyminski.moviecatalogservice.models.Movie;
import io.bkrzyminski.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        RestTemplate restTemplate = new RestTemplate();

        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 87)
        );

        return ratings.stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), "Desc", rating.getRating());
                })
                .collect(Collectors.toList());
    }
}
