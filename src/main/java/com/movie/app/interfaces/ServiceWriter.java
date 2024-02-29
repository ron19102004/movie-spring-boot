package com.movie.app.interfaces;

public interface ServiceWriter<Entity, TypeId, CreateDto, UpdateDto> extends
        ServiceCreate<Entity, CreateDto>,
        ServiceUpdate<Entity, UpdateDto, TypeId>,
        ServiceDelete<TypeId> {
}
