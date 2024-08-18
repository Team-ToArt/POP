package com.pop.backend.domain.artist.persistence.repository;

import com.pop.backend.domain.artist.persistence.Artist;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long>, CustomArtistRepository {

  Optional<Artist> findByToken(String token);

}