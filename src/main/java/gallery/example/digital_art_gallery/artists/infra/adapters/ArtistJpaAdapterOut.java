package gallery.example.digital_art_gallery.artists.infra.adapters;

import gallery.example.digital_art_gallery.artists.infra.entities.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistJpaAdapterOut extends JpaRepository<ArtistEntity, Long> {
}
