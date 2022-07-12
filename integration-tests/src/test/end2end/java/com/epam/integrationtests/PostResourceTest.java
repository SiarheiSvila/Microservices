package com.epam.integrationtests;

import com.epam.integrationtests.songs.SongClientModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class PostResourceTest {
    @Value("${resource-service.url}")
    private String resourceServiceUrl;

    @Value("${song-service.url}")
    private String songServiceUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void postResourceAndCheckOtherServices() throws InterruptedException {
        log.info("Step 1: create a resource");
        HttpEntity<MultiValueMap<String, Object>> requestEntity = ResourceObjectMother.getFileRequestEntity();
        long resourceId = restTemplate.postForObject(resourceServiceUrl, requestEntity, Long.class);

        log.info("Step 2: check that song with same resourceId was created");
        Thread.sleep(1000);
        ResponseEntity<SongClientModel> song = restTemplate.getForEntity(
                songServiceUrl + "?resourceId=" + resourceId, SongClientModel.class);
        assertThat(song.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(song.getBody()).isNotNull();
        assertThat(song.getBody().getResourceId()).isEqualTo(resourceId);

        log.info("Step 3: delete a resource");
        restTemplate.delete(resourceServiceUrl + "/" + resourceId);
        log.info("Step 4: delete a song");
        restTemplate.delete(songServiceUrl + "/" + song.getBody().getId());
    }
}
