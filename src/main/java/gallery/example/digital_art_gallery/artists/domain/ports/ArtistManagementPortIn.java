package gallery.example.digital_art_gallery.artists.domain.ports;

import gallery.example.digital_art_gallery.artists.app.dtos.datemasking.ArtistCreateDTO;
import gallery.example.digital_art_gallery.artists.app.dtos.datemasking.ArtistResponseDTO;

import java.util.List;

public interface ArtistManagementPortIn {

    ArtistResponseDTO createArtist(ArtistCreateDTO newArtist);

    List<ArtistResponseDTO> getAllArtists();

    ArtistResponseDTO getArtistById(Long artistId);

    ArtistResponseDTO updateArtist(Long artistId, ArtistCreateDTO updatedArtist);

    void deleteArtist(Long artistId);

}
