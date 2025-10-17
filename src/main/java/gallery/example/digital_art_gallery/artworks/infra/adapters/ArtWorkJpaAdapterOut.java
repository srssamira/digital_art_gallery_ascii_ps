package gallery.example.digital_art_gallery.artworks.infra.adapters;

import gallery.example.digital_art_gallery.artworks.infra.entities.ArtWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtWorkJpaAdapterOut extends JpaRepository<ArtWorkEntity, Long> {
}
