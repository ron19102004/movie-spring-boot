package com.movie.app.interfaces;

import java.util.List;
import java.util.Optional;

public interface ServiceReader<Entity, TypeID> {
    Optional<Entity> findById(TypeID typeID);

    List<Entity> find();
}
