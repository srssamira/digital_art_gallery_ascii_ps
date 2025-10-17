package gallery.example.digital_art_gallery.artworks.app.ports.controllers;

import gallery.example.digital_art_gallery.artworks.app.dtos.ArtWorkCreateDTO;
import gallery.example.digital_art_gallery.artworks.app.dtos.ArtWorkResponseDTO;
import gallery.example.digital_art_gallery.artworks.domain.ports.ArtWorkManagementPortIn;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artworks")
public class ArtWorkController {

    private final ArtWorkManagementPortIn artWorkManagementPortIn;

    public ArtWorkController(ArtWorkManagementPortIn artWorkManagementPortIn) {
        this.artWorkManagementPortIn = artWorkManagementPortIn;
    }

    @PostMapping
    public ResponseEntity<ArtWorkResponseDTO> generateArtWork(@Valid @RequestBody ArtWorkCreateDTO newArtWork) {
        ArtWorkResponseDTO createdArtWork = artWorkManagementPortIn.createArtWork(newArtWork);
        return ResponseEntity.status(201).body(createdArtWork);
    }

    @GetMapping
    public ResponseEntity<List<ArtWorkResponseDTO>> getAllArtWorks() {
        List<ArtWorkResponseDTO> artWorks = artWorkManagementPortIn.getAllArtWorks();
        return ResponseEntity.status(200).body(artWorks);
    }

    @GetMapping("/{artWorkId}")
    public ResponseEntity<ArtWorkResponseDTO> getArtWorkById(@PathVariable Long artWorkId) {
        ArtWorkResponseDTO artWork = artWorkManagementPortIn.getArtWorkById(artWorkId);
        return ResponseEntity.status(200).body(artWork);
    }

    @PutMapping("/{artWorkId}")
    public ResponseEntity<ArtWorkResponseDTO> updateArtWork(@PathVariable Long artWorkId, @Valid @RequestBody ArtWorkCreateDTO updatedArtWork) {
        ArtWorkResponseDTO artWork = artWorkManagementPortIn.updateArtWork(artWorkId, updatedArtWork);
        return ResponseEntity.status(200).body(artWork);
    }

    @DeleteMapping("/{artWorkId}")
    public ResponseEntity<Void> deleteArtWork(@PathVariable Long artWorkId) {
        artWorkManagementPortIn.deleteArtWork(artWorkId);
        return ResponseEntity.status(204).build();
    }
}
