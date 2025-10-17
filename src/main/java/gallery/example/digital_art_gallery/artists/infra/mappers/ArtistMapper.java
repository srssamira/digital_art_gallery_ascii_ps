package gallery.example.digital_art_gallery.artists.infra.mappers;

import gallery.example.digital_art_gallery.artists.app.dtos.ArtistCreateDTO;
import gallery.example.digital_art_gallery.artists.app.dtos.ArtistResponseDTO;
import gallery.example.digital_art_gallery.artworks.app.dtos.ArtWorkResponseDTO;
import gallery.example.digital_art_gallery.artists.infra.entities.ArtistEntity;
import gallery.example.digital_art_gallery.artworks.infra.mappers.ArtWorkMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ArtistMapper {

    public static ArtistResponseDTO toResponse(ArtistEntity artistEntity) {
        ArtistResponseDTO artistResponseDTO = new ArtistResponseDTO();
        artistResponseDTO.setId(artistEntity.getId());
        artistResponseDTO.setName(artistEntity.getName());
        artistResponseDTO.setBio(artistEntity.getBio());
        artistResponseDTO.setEmail(artistEntity.getEmail());
        artistResponseDTO.setInstagram(artistEntity.getInstagram());
        artistResponseDTO.setTwitter(artistEntity.getTwitter());
        artistResponseDTO.setWebsite(artistEntity.getWebsite());

        if (artistEntity.getArtworks() != null) {
            List<ArtWorkResponseDTO> artworkResponses = artistEntity.getArtworks().stream()
                    .map(ArtWorkMapper::toResponse)
                    .collect(Collectors.toList());
            artistResponseDTO.setArtworks(artworkResponses);
        }

        return artistResponseDTO;
    }

    public static ArtistEntity toEntity(ArtistCreateDTO newArtist) {

        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName(newArtist.getName());
        artistEntity.setBio(newArtist.getBio());
        artistEntity.setEmail(newArtist.getEmail());
        artistEntity.setInstagram(newArtist.getInstagram());
        artistEntity.setTwitter(newArtist.getTwitter());
        artistEntity.setWebsite(newArtist.getWebsite());
        return artistEntity;
    }
}