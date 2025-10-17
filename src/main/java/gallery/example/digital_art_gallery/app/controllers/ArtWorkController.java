package gallery.example.digital_art_gallery.app.controllers;

import gallery.example.digital_art_gallery.app.dtos.artwork.ArtWorkCreateDTO;
import gallery.example.digital_art_gallery.app.dtos.artwork.ArtWorkResponseDTO;
import gallery.example.digital_art_gallery.services.ArtWorkService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artworks")
public class ArtWorkController {

    private final ArtWorkService artWorkService;

    public ArtWorkController(ArtWorkService artWorkService) {
        this.artWorkService = artWorkService;
    }

    @PostMapping
    public ResponseEntity<ArtWorkResponseDTO> generateArtWork(@Valid @RequestBody ArtWorkCreateDTO newArtWork) {
        ArtWorkResponseDTO createdArtWork = artWorkService.createArtWork(newArtWork);
        return ResponseEntity.status(201).body(createdArtWork);
    }

    @GetMapping
    public ResponseEntity<List<ArtWorkResponseDTO>> getAllArtWorks() {
        List<ArtWorkResponseDTO> artWorks = artWorkService.getAllArtWorks();
        return ResponseEntity.status(200).body(artWorks);
    }

    @GetMapping("/{artWorkId}")
    public ResponseEntity<ArtWorkResponseDTO> getArtWorkById(@PathVariable Long artWorkId) {
        ArtWorkResponseDTO artWork = artWorkService.getArtWorkById(artWorkId);
        return ResponseEntity.status(200).body(artWork);
    }

    @PutMapping("/{artWorkId}")
    public ResponseEntity<ArtWorkResponseDTO> updateArtWork(@PathVariable Long artWorkId, @Valid @RequestBody ArtWorkCreateDTO updatedArtWork) {
        ArtWorkResponseDTO artWork = artWorkService.updateArtWork(artWorkId, updatedArtWork);
        return ResponseEntity.status(200).body(artWork);
    }

    @DeleteMapping("/{artWorkId}")
    public ResponseEntity<Void> deleteArtWork(@PathVariable Long artWorkId) {
        artWorkService.deleteArtWork(artWorkId);
        return ResponseEntity.status(204).build();
    }
}
