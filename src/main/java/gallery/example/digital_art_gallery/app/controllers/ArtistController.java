package gallery.example.digital_art_gallery.app.controllers;

import gallery.example.digital_art_gallery.app.dtos.artist.ArtistCreateDTO;
import gallery.example.digital_art_gallery.app.dtos.artist.ArtistResponseDTO;
import gallery.example.digital_art_gallery.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping
    public ResponseEntity<ArtistResponseDTO> generateArtist(@RequestBody ArtistCreateDTO newArtist) {
        ArtistResponseDTO createdArtist = artistService.createArtist(newArtist);
        return ResponseEntity.status(201).body(createdArtist);
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponseDTO>> getAllArtists() {
        List<ArtistResponseDTO> artists = artistService.getAllArtists();
        return ResponseEntity.status(200).body(artists);
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<ArtistResponseDTO> getArtistById(@PathVariable Long artistId) {
        ArtistResponseDTO artist = artistService.getArtistById(artistId);
        return ResponseEntity.status(200).body(artist);
    }

    @PutMapping("/{artistId}")
    public ResponseEntity<ArtistResponseDTO> updateArtist(@PathVariable Long artistId, @RequestBody ArtistCreateDTO updatedArtist) {
        ArtistResponseDTO artist = artistService.updateArtist(artistId, updatedArtist);
        return ResponseEntity.status(200).body(artist);
    }

}
