package github.guisofiati.webflux.controllers;

import github.guisofiati.webflux.documents.Playlist;
import github.guisofiati.webflux.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@RequestMapping(value = "/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public Flux<Playlist> getAllPlaylists() {
        return playlistService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Playlist> getPlaylistById(@PathVariable String id) {
        return playlistService.findById(id);
    }

    @PostMapping
    public Mono<Playlist> save(@RequestBody Playlist playlist) {
        return playlistService.save(playlist);
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> getPlaylistsByEvents() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Playlist> events = playlistService.findAll();
        System.out.println("gone");
        return Flux.zip(interval, events);
    }
}
