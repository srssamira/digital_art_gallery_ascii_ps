package gallery.example.digital_art_gallery.services;

import gallery.example.digital_art_gallery.app.dtos.artist.ArtistCreateDTO;
import gallery.example.digital_art_gallery.app.dtos.artist.ArtistResponseDTO;

public interface ArtistService {

    ArtistResponseDTO createArtist(ArtistCreateDTO newArtist);

}
