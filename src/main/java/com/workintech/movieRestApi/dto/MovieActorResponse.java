package com.workintech.movieRestApi.dto;

import com.workintech.movieRestApi.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieActorResponse {
    private Movie movie;
    private int actorId;
    private String actorFirstName;
    private String actorLastName;
    private LocalDate actorBirthDate;
}
