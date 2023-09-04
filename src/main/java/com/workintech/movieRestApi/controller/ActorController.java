package com.workintech.movieRestApi.controller;

import com.workintech.movieRestApi.dto.ActorResponse;
import com.workintech.movieRestApi.dto.MovieActorRequest;
import com.workintech.movieRestApi.dto.MovieActorResponse;
import com.workintech.movieRestApi.entity.Actor;
import com.workintech.movieRestApi.entity.Movie;
import com.workintech.movieRestApi.service.ActorService;
import com.workintech.movieRestApi.service.MovieService;
import com.workintech.movieRestApi.util.HollywoodUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workintech/actors")
public class ActorController {
    private ActorService actorService;
    private MovieService movieService;
    @Autowired
    public ActorController(ActorService actorService, MovieService movieService) {
        this.actorService = actorService;
        this.movieService = movieService;
    }

    @GetMapping("/")
    public List<ActorResponse> gelAllActors() {
        List<Actor> actors = actorService.findAll();
        List<ActorResponse> actorResponses = new ArrayList<>();

        for(Actor actor : actors) {
            ActorResponse actorResponse = HollywoodUtility.convertActorResponse(actor);
            actorResponses.add(actorResponse);
        }
        return actorResponses;
    }

    @GetMapping("/{id}")
    public ActorResponse getActorById(@PathVariable int id) {
        Actor actor = actorService.findById(id);
        if(actor != null) {
            return HollywoodUtility.convertActorResponse(actor);
        }
        return null;
    }

    @PostMapping("/")
    public MovieActorResponse save(@RequestBody MovieActorRequest movieActorRequest){
        Movie movie = movieActorRequest.getMovie();
        Actor actor = movieActorRequest.getActor();
        movie.addActor(actor);
        Movie savedMovie = movieService.save(movie);
        return HollywoodUtility.convertMovieActorResponse(savedMovie,actor);
    }
    @PutMapping("/{id}")
    public ActorResponse updateActor(@RequestBody Actor actor,@PathVariable("id") int actorId) {
        Actor existingActor = actorService.findById(actorId);
        actor.setId(actorId);
        actor.setMovies(existingActor.getMovies());
        Actor updatedActor = actorService.save(actor);
        return HollywoodUtility.convertActorResponse(updatedActor);
    }
    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable int id) {
        Actor actorToDelete = actorService.delete(id);
        if(actorToDelete != null) {
            return HollywoodUtility.convertActorResponse(actorToDelete);
        }
        return null;
    }



}
