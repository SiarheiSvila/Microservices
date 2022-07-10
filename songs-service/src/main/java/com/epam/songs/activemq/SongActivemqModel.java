package com.epam.songs.activemq;

import lombok.Data;

@Data
public class SongActivemqModel {
    private String name;
    private String artist;
    private String album;
    private long length;
    private long resourceId;
    private String year;
}
