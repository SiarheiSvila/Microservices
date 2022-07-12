package com.epam.integrationtests.songs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongClientModel {
    private Long id;
    private String name;
    private String artist;
    private String album;
    private long length;
    private long resourceId;
    private String year;
}
