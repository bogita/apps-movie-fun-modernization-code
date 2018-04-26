package org.superbiz.moviefun.movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/movies")
public class MoviesController {

    private final MoviesRepository moviesRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MoviesController(MoviesRepository moviesRepository){
        this.moviesRepository=moviesRepository;
    }

    @PostMapping
    public ResponseEntity addMovie(@RequestBody Movie movie){
        moviesRepository.addMovie(movie);
        return new ResponseEntity(CREATED);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity deleteMovieId(@PathVariable Long movieId){
        moviesRepository.deleteMovieId(movieId);
        return new ResponseEntity(ACCEPTED);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> count(@RequestParam(name = "field", required = false) String field,
                                         @RequestParam(name = "key", required = false) String key){
        if(field != null && key != null){
            logger.info("Retrieving count for key="+key+" and field="+field);
            return new ResponseEntity<>(moviesRepository.count(field, key), OK);
        }else{
            logger.info("Retrieving count all");
            return new ResponseEntity<>(moviesRepository.countAll(), OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Movie>> find(@RequestParam(name = "field", required = false) String field,
                                               @RequestParam(name = "key", required = false) String key,
                                               @RequestParam(name = "start", required = false) Integer start,
                                               @RequestParam(name = "pageSize", required = false) Integer pageSize){
        logger.info("Finding movies");
        if (field != null && key != null) {
            return new ResponseEntity<>(moviesRepository.findRange(field, key, start, pageSize), OK);
        } else if (start != null && pageSize != null) {
            return new ResponseEntity<>(moviesRepository.findAll(start, pageSize), OK);
        } else {
            return new ResponseEntity<>(moviesRepository.getMovies(), OK);
        }
    }

}
