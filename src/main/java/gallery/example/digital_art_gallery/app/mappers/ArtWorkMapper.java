package gallery.example.digital_art_gallery.app.mappers;

import gallery.example.digital_art_gallery.app.dtos.artwork.ArtWorkCreateDTO;
import gallery.example.digital_art_gallery.app.dtos.artwork.ArtWorkResponseDTO;
import gallery.example.digital_art_gallery.infra.entities.ArtWorkEntity;
import gallery.example.digital_art_gallery.infra.entities.ArtistEntity;

public class ArtWorkMapper {

    public static ArtWorkEntity toEntity(ArtWorkCreateDTO newArtWork) {
        return new ArtWorkEntity(
                null,
                newArtWork.getTitle(),
                newArtWork.getDescription(),
                newArtWork.getImageUrl(),
                null
        );
    }

    public static ArtWorkResponseDTO toResponse(ArtWorkEntity artWorkEntity) {

        ArtistEntity artist = artWorkEntity.getArtist();

        String artistName = (artist != null) ? artist.getName() : null;

        return new ArtWorkResponseDTO(
                artWorkEntity.getId(),
                artWorkEntity.getTitle(),
                artWorkEntity.getDescription(),
                artWorkEntity.getImageUrl(),
                artistName
        );
    }
}