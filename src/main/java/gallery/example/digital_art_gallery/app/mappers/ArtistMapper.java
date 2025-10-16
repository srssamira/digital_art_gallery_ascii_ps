package gallery.example.digital_art_gallery.app.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import gallery.example.digital_art_gallery.app.dtos.artist.ArtistCreateDTO;
import gallery.example.digital_art_gallery.app.dtos.artist.ArtistResponseDTO;
import gallery.example.digital_art_gallery.infra.entities.ArtistEntity;

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
        artistResponseDTO.setArtworks(artistEntity.getArtworks());
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
