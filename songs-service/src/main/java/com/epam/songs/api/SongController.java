package com.epam.songs.api;

import com.epam.songs.api.clientmodels.SongClientModel;
import com.epam.songs.services.SongsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/songs")
@AllArgsConstructor
@Slf4j
public class SongController {
    private final SongsService service;

    @GetMapping("/{id}")
    public SongClientModel getSong(@PathVariable long id) {
        return service.getSong(id);
    }

    @GetMapping
    public SongClientModel findByResourceId(@RequestParam long resourceId) {
        log.info("Received get dy resourceId: {}", resourceId);
        return service.findByResourceId(resourceId);
    }

    @PostMapping
    public long createSong(@RequestBody SongClientModel clientModel) {
        return service.createSong(clientModel);
    }

    @DeleteMapping("/{id}")
    public long deleteSong(@PathVariable long id) {
        return service.deleteSong(id);
    }
}
