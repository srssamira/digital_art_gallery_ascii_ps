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

    @Override
    public java.util.List<ArtistResponseDTO> getAllArtists() {
        java.util.List<ArtistEntity> artistEntities = artistJpaRepository.findAll();
        return artistEntities.stream()
                .map(ArtistMapper::toResponse)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public ArtistResponseDTO getArtistById(Long artistId) {
        ArtistEntity artistEntity = artistJpaRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artista com ID " + artistId + " não encontrado."));
        return ArtistMapper.toResponse(artistEntity);
    }

    @Override
    public ArtistResponseDTO updateArtist(Long artistId, ArtistCreateDTO updatedArtist) {
        ArtistEntity existingArtist = artistJpaRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artista com ID " + artistId + " não encontrado."));

        existingArtist.setName(updatedArtist.getName());
        existingArtist.setBio(updatedArtist.getBio());
        existingArtist.setInstagram(updatedArtist.getInstagram());
        existingArtist.setTwitter(updatedArtist.getTwitter());
        existingArtist.setWebsite(updatedArtist.getWebsite());
        existingArtist.setEmail(updatedArtist.getEmail());

        ArtistEntity savedArtist = artistJpaRepository.save(existingArtist);
        return ArtistMapper.toResponse(savedArtist);
    }

}
