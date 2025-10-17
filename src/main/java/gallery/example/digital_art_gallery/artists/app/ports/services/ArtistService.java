package gallery.example.digital_art_gallery.artists.app.ports.services;

import gallery.example.digital_art_gallery.artists.app.dtos.datemasking.ArtistCreateDTO;
import gallery.example.digital_art_gallery.artists.app.dtos.datemasking.ArtistResponseDTO;
import gallery.example.digital_art_gallery.artists.domain.exceptions.ArtistNotFoundException;
import gallery.example.digital_art_gallery.artists.infra.mappers.ArtistMapper;
import gallery.example.digital_art_gallery.artists.infra.entities.ArtistEntity;
import gallery.example.digital_art_gallery.artists.infra.adapters.ArtistJpaAdapterOut;
import gallery.example.digital_art_gallery.artists.domain.ports.ArtistManagementPortIn;
import org.springframework.stereotype.Service;

@Service
public class ArtistService implements ArtistManagementPortIn {

    private final ArtistJpaAdapterOut artistRepository;

    public ArtistService(ArtistJpaAdapterOut artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public ArtistResponseDTO createArtist(ArtistCreateDTO newArtist) {
        ArtistEntity artistEntity = artistRepository.save(ArtistMapper.toEntity(newArtist));
        return ArtistMapper.toResponse(artistEntity);
    }

    @Override
    public java.util.List<ArtistResponseDTO> getAllArtists() {
        java.util.List<ArtistEntity> artistEntities = artistRepository.findAll();
        return artistEntities.stream()
                .map(ArtistMapper::toResponse)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public ArtistResponseDTO getArtistById(Long artistId) {
        ArtistEntity artistEntity = artistRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(artistId));
        return ArtistMapper.toResponse(artistEntity);
    }

    @Override
    public ArtistResponseDTO updateArtist(Long artistId, ArtistCreateDTO updatedArtist) {
        ArtistEntity existingArtist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(artistId));

        existingArtist.setName(updatedArtist.getName());
        existingArtist.setBio(updatedArtist.getBio());
        existingArtist.setInstagram(updatedArtist.getInstagram());
        existingArtist.setTwitter(updatedArtist.getTwitter());
        existingArtist.setWebsite(updatedArtist.getWebsite());
        existingArtist.setEmail(updatedArtist.getEmail());

        ArtistEntity savedArtist = artistRepository.save(existingArtist);
        return ArtistMapper.toResponse(savedArtist);
    }

    @Override
    public void deleteArtist(Long artistId) {
        ArtistEntity existingArtist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(artistId));
        artistRepository.delete(existingArtist);
    }

}
