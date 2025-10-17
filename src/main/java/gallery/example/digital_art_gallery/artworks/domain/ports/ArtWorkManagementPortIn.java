package gallery.example.digital_art_gallery.artworks.domain.ports;

import gallery.example.digital_art_gallery.artworks.app.dtos.ArtWorkCreateDTO;
import gallery.example.digital_art_gallery.artworks.app.dtos.ArtWorkResponseDTO;

import java.util.List;

public interface ArtWorkManagementPortIn {

    ArtWorkResponseDTO createArtWork(ArtWorkCreateDTO newArtWork);

    List<ArtWorkResponseDTO> getAllArtWorks();

    ArtWorkResponseDTO getArtWorkById(Long artWorkId);

    ArtWorkResponseDTO updateArtWork(Long artWorkId, ArtWorkCreateDTO updatedArtWork);

    void deleteArtWork(Long artWorkId);

}
