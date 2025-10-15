package gallery.example.digital_art_gallery.infra.repositories;

import gallery.example.digital_art_gallery.infra.entities.ArtWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtWorkJpaRepository extends JpaRepository<ArtWorkEntity, Long> {
}
