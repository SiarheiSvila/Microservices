package com.epam.integrationtests;

import lombok.SneakyThrows;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ResourceObjectMother {
    private static final String SONG_MP3 = "song.mp3";
    private static final String SONG_FILE_PATH = "D:/EPAM Learn/Microservices/Songs Project/integration-tests/src/test/end2end/resources/song.mp3";

    @SneakyThrows
    public static HttpEntity<MultiValueMap<String, Object>> getFileRequestEntity() {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("resource", new FileSystemResource(SONG_FILE_PATH));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        return new HttpEntity<>(body, httpHeaders);
    }
}
