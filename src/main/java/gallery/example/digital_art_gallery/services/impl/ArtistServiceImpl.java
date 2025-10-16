package gallery.example.digital_art_gallery.services.impl;

import gallery.example.digital_art_gallery.app.dtos.artist.ArtistCreateDTO;
import gallery.example.digital_art_gallery.app.dtos.artist.ArtistResponseDTO;
import gallery.example.digital_art_gallery.app.mappers.ArtistMapper;
import gallery.example.digital_art_gallery.infra.entities.ArtistEntity;
import gallery.example.digital_art_gallery.infra.repositories.ArtistJpaRepository;
import gallery.example.digital_art_gallery.services.ArtistService;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistJpaRepository artistJpaRepository;

    public ArtistServiceImpl(ArtistJpaRepository artistJpaRepository) {
        this.artistJpaRepository = artistJpaRepository;
    }

    @Override
    public ArtistResponseDTO createArtist(ArtistCreateDTO newArtist) {
        ArtistEntity artistEntity = artistJpaRepository.save(ArtistMapper.toEntity(newArtist));
        return ArtistMapper.toResponse(artistEntity);
    }
}
