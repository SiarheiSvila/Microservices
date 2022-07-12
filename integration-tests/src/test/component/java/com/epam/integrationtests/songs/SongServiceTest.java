package com.epam.integrationtests.songs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class SongServiceTest {

    @Value("${song-service.url}")
    private String url;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testSongServiceOperations() {
        SongClientModel song = SongObjectMother.songClientModel();
        log.info("Step 1: create a song");
        Long songId = restTemplate.postForObject(url, song, Long.class);
        assertThat(songId).isPositive();
        log.info("Step 2: retrieve a song");
        SongClientModel actualSong = restTemplate.getForObject(url + "/" + songId, SongClientModel.class);
        song.setId(actualSong.getId());
        assertThat(actualSong).isEqualTo(song);
        log.info("Step 3: delete a song");
        restTemplate.delete(url + "/" + songId);
        log.info("Step 4: retrieve a song to check delete");
        assertThat(restTemplate.getForObject(url + "/" + songId, SongClientModel.class)).isNull();
    }
}
