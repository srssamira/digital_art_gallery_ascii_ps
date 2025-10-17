package gallery.example.digital_art_gallery.services;

import gallery.example.digital_art_gallery.app.dtos.artwork.ArtWorkCreateDTO;
import gallery.example.digital_art_gallery.app.dtos.artwork.ArtWorkResponseDTO;

public interface ArtWorkService {

    ArtWorkResponseDTO createArtWork(ArtWorkCreateDTO newArtWork);
}
