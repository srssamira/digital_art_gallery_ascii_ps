package gallery.example.digital_art_gallery.services;

import gallery.example.digital_art_gallery.app.dtos.artwork.ArtWorkCreateDTO;
import gallery.example.digital_art_gallery.app.dtos.artwork.ArtWorkResponseDTO;

import java.util.List;

public interface ArtWorkService {

    ArtWorkResponseDTO createArtWork(ArtWorkCreateDTO newArtWork);

    List<ArtWorkResponseDTO> getAllArtWorks();

    ArtWorkResponseDTO getArtWorkById(Long artWorkId);

    ArtWorkResponseDTO updateArtWork(Long artWorkId, ArtWorkCreateDTO updatedArtWork);

    void deleteArtWork(Long artWorkId);

}
