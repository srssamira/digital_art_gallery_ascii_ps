package gallery.example.digital_art_gallery.infra.repositories;

import gallery.example.digital_art_gallery.infra.entities.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistJpaRepository extends JpaRepository<ArtistEntity, Long> {
}
