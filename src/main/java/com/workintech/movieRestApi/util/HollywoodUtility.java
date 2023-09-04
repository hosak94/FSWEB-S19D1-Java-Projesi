package com.workintech.movieRestApi.util;

import com.workintech.movieRestApi.dto.ActorResponse;
import com.workintech.movieRestApi.dto.MovieActorResponse;
import com.workintech.movieRestApi.dto.MovieResponse;
import com.workintech.movieRestApi.entity.Actor;
import com.workintech.movieRestApi.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class HollywoodUtility {
    public static ActorResponse convertActorResponse(Actor actor) {
        return new ActorResponse(
                actor.getId(),
                actor.getFirstName(),
                actor.getLastName(),
                actor.getBirthDate()
        );
    }
    public static MovieResponse convertMovieResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getName(),
                movie.getDirectorName(),
                movie.getRating(),
                movie.getReleaseDate()
        );
    }
    public static MovieActorResponse convertMovieActorResponse(Movie movie, Actor actor) {
        return new MovieActorResponse(movie, actor.getId(), actor.getFirstName(),
                actor.getLastName(),actor.getBirthDate());
    }

    public static List<ActorResponse> convertActorResponses(Movie savedMovie) {
        List<ActorResponse> actorResponses = new ArrayList<>();
        for(Actor actor : savedMovie.getActors()) {
            actorResponses.add(new ActorResponse(actor.getId(),actor.getFirstName(),actor.getLastName(),actor.getBirthDate()));
        }
        return actorResponses;
    }
}
