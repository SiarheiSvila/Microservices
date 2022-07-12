package com.epam.integrationtests.songs;

public class SongObjectMother {
    public static SongClientModel songClientModel() {
        return SongClientModel.builder()
                .name("name")
                .artist("artist")
                .album("album")
                .length(200)
                .resourceId(1)
                .year("2000")
                .build();
    }
}
