package com.workintech.movieRestApi.controller;

import com.workintech.movieRestApi.dto.ActorResponse;
import com.workintech.movieRestApi.dto.MovieActorRequest;
import com.workintech.movieRestApi.dto.MovieActorResponse;
import com.workintech.movieRestApi.entity.Actor;
import com.workintech.movieRestApi.entity.Movie;
import com.workintech.movieRestApi.dto.MovieResponse;
import com.workintech.movieRestApi.service.MovieService;
import com.workintech.movieRestApi.util.HollywoodUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workintech/movies")
public class MovieController {
    private MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieService.findAll();
        List<MovieResponse> movieResponses = new ArrayList<>();

        for (Movie movie : movies) {
            MovieResponse movieResponse = HollywoodUtility.convertMovieResponse(movie);
            movieResponses.add(movieResponse);
        }
        return movieResponses;
    }

    @GetMapping("/{id}")
    public MovieResponse getMovieById(@PathVariable int id) {
        Movie movie = movieService.findById(id);
        if (movie != null) {
            return HollywoodUtility.convertMovieResponse(movie);
        }
        return null;
    }

    @PostMapping("/")
    public MovieActorResponse save(@RequestBody MovieActorRequest movieActorRequest) {
        Movie movie = movieActorRequest.getMovie();
        Actor actor = movieActorRequest.getActor();
        movie.addActor(actor);
        Movie savedMovie = movieService.save(movie);
        return HollywoodUtility.convertMovieActorResponse(savedMovie,actor);
    }

    @PostMapping("/addActor/{movieId}")
    public List<ActorResponse> addActor(@RequestBody List<Actor> actors, @PathVariable int movieId) {
      Movie movie = movieService.findById(movieId);
      movie.addAllActor(actors);
        Movie savedMovie = movieService.save(movie);
        return HollywoodUtility.convertActorResponses(savedMovie);
    }
    @PutMapping("/{movieId}")
    public MovieResponse update(@RequestBody Movie movie,@PathVariable int movieId) {
        Movie existingMovie = movieService.findById(movieId);
            movie.setId(movieId);
            movie.setActors(existingMovie.getActors());
            Movie updatedMovie = movieService.save(movie);
            return HollywoodUtility.convertMovieResponse(updatedMovie);
    }


    @DeleteMapping("/{id}")
    public MovieResponse delete(@PathVariable int id) {
        Movie movieToDelete = movieService.delete(id);
        if(movieToDelete != null) {
            return HollywoodUtility.convertMovieResponse(movieToDelete);
        }
        return null;
    }







}
