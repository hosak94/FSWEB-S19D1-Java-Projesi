package com.workintech.movieRestApi.service;

import com.workintech.movieRestApi.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> findAll();

    Actor findById(int id);

    Actor save(Actor actor);

    Actor delete(int id);
}
