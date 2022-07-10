package com.epam.songs.repositories;

import com.epam.songs.entities.SongEntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongJpaRepository extends JpaRepository<SongEntityModel, Long> {
}
