package com.epam.songs.api.clientmodels;

import lombok.Data;

@Data
public class SongClientModel {
    private Long id;
    private String name;
    private String artist;
    private String album;
    private long length;
    private long resourceId;
    private String year;
}
