package gallery.example.digital_art_gallery.artists.app.ports.controllers;

import gallery.example.digital_art_gallery.artists.app.dtos.datemasking.ArtistCreateDTO;
import gallery.example.digital_art_gallery.artists.app.dtos.datemasking.ArtistResponseDTO;
import gallery.example.digital_art_gallery.artists.domain.ports.ArtistManagementPortIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistManagementPortIn artistManagementPortIn;

    public ArtistController(ArtistManagementPortIn artistManagementPortIn) {
        this.artistManagementPortIn = artistManagementPortIn;
    }

    @PostMapping
    public ResponseEntity<ArtistResponseDTO> generateArtist(@RequestBody ArtistCreateDTO newArtist) {
        ArtistResponseDTO createdArtist = artistManagementPortIn.createArtist(newArtist);
        return ResponseEntity.status(201).body(createdArtist);
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponseDTO>> getAllArtists() {
        List<ArtistResponseDTO> artists = artistManagementPortIn.getAllArtists();
        return ResponseEntity.status(200).body(artists);
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<ArtistResponseDTO> getArtistById(@PathVariable Long artistId) {
        ArtistResponseDTO artist = artistManagementPortIn.getArtistById(artistId);
        return ResponseEntity.status(200).body(artist);
    }

    @PutMapping("/{artistId}")
    public ResponseEntity<ArtistResponseDTO> updateArtist(@PathVariable Long artistId, @RequestBody ArtistCreateDTO updatedArtist) {
        ArtistResponseDTO artist = artistManagementPortIn.updateArtist(artistId, updatedArtist);
        return ResponseEntity.status(200).body(artist);
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long artistId) {
        artistManagementPortIn.deleteArtist(artistId);
        return ResponseEntity.status(204).build();
    }

}
