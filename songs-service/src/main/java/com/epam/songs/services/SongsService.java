package com.epam.songs.services;

import com.epam.songs.api.clientmodels.SongClientModel;
import com.epam.songs.entities.SongEntityModel;
import com.epam.songs.mappers.SongMapper;
import com.epam.songs.repositories.SongJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<SongClientModel> getAll() {
        return repository.findAll().stream()
                .map(mapper::toClientModel)
                .collect(Collectors.toList());
    }

    public SongClientModel findByResourceId(long resourceId) {
        Optional<SongClientModel> optional = repository.findByResourceId(resourceId)
                        .map(mapper::toClientModel);
        return optional.orElse(null);
    }

    public long deleteSong(long id) {
        repository.deleteById(id);
        return id;
    }
}
