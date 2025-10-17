package gallery.example.digital_art_gallery.services;

import gallery.example.digital_art_gallery.app.dtos.artist.ArtistCreateDTO;
import gallery.example.digital_art_gallery.app.dtos.artist.ArtistResponseDTO;

import java.util.List;

public interface ArtistService {

    ArtistResponseDTO createArtist(ArtistCreateDTO newArtist);

    List<ArtistResponseDTO> getAllArtists();

    ArtistResponseDTO getArtistById(Long artistId);

    ArtistResponseDTO updateArtist(Long artistId, ArtistCreateDTO updatedArtist);

    void deleteArtist(Long artistId);

}
