package com.epam.songs.services;

import com.epam.songs.api.clientmodels.SongClientModel;
import com.epam.songs.entities.SongEntityModel;
import com.epam.songs.mappers.SongMapper;
import com.epam.songs.repositories.SongJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class SongsService {
    private final SongJpaRepository repository;
    private final SongMapper mapper;

    public long createSong(SongClientModel songClientModel) {
        SongEntityModel songEntityModel = mapper.toEntity(songClientModel);
        SongEntityModel saveResult = repository.save(songEntityModel);
        return saveResult.getId();
    }

    public SongClientModel getSong(long id) {
        SongEntityModel entityModel = repository.getReferenceById(id);
        return mapper.toClientModel(entityModel);
    }

    public long deleteSong(long id) {
        repository.deleteById(id);
        return id;
    }
}
