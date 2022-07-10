package com.epam.songs.api;

import com.epam.songs.api.clientmodels.SongClientModel;
import com.epam.songs.services.SongsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/songs")
@AllArgsConstructor
public class SongController {
    private final SongsService service;

    @GetMapping("/{id}")
    public SongClientModel getSong(@PathVariable long id) {
        return service.getSong(id);
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
