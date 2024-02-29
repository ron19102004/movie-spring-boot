package com.movie.app.interfaces;

public interface ServiceUpdate<Entity,Dto,TypeId> {
    Entity update(TypeId id,Dto dto);
}
