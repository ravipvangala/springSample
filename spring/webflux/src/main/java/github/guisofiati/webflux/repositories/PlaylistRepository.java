package github.guisofiati.webflux.repositories;

import github.guisofiati.webflux.documents.Playlist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PlaylistRepository extends ReactiveMongoRepository<Playlist, String> {

}
