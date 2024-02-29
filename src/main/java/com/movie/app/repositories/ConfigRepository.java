package com.movie.app.repositories;

import com.movie.app.entities.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigRepository extends JpaRepository<Config,Long> {
    List<Config> findAllByIsActive(Boolean isActive);
}
