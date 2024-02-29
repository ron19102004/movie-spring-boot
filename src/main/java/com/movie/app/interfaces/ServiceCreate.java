package com.movie.app.interfaces;

public interface ServiceCreate<Entity,Dto> {
    Entity create(Dto dto);
}
