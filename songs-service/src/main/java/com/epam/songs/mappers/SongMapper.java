package com.epam.songs.mappers;

import com.epam.songs.activemq.SongActivemqModel;
import com.epam.songs.api.clientmodels.SongClientModel;
import com.epam.songs.entities.SongEntityModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongEntityModel toEntity(SongClientModel clientModel);
    SongClientModel toClientModel(SongEntityModel entityModel);
    SongClientModel toClientModel(SongActivemqModel entityModel);
}
