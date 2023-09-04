package com.workintech.movieRestApi.dto;

import com.workintech.movieRestApi.entity.Actor;
import com.workintech.movieRestApi.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieActorRequest {
    private Movie movie;
    private Actor actor;
}
