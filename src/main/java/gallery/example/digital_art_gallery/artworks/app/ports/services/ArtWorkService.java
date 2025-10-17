package gallery.example.digital_art_gallery.artworks.app.ports.services;

import gallery.example.digital_art_gallery.artworks.app.dtos.datemasking.ArtWorkCreateDTO;
import gallery.example.digital_art_gallery.artworks.app.dtos.datemasking.ArtWorkResponseDTO;
import gallery.example.digital_art_gallery.artworks.infra.adapters.ArtWorkJpaAdapterOut;
import gallery.example.digital_art_gallery.artworks.infra.entities.ArtWorkEntity;
import gallery.example.digital_art_gallery.artworks.infra.mappers.ArtWorkMapper;
import gallery.example.digital_art_gallery.artists.infra.entities.ArtistEntity;
import gallery.example.digital_art_gallery.artists.infra.adapters.ArtistJpaAdapterOut;
import gallery.example.digital_art_gallery.artworks.domain.ports.ArtWorkManagementPortIn;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtWorkService implements ArtWorkManagementPortIn {

    private final ArtWorkJpaAdapterOut artWorkRepository;
    private final ArtistJpaAdapterOut artistRepository;

    public ArtWorkService(ArtWorkJpaAdapterOut artWorkRepository, ArtistJpaAdapterOut artistRepository) {
        this.artWorkRepository = artWorkRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public ArtWorkResponseDTO createArtWork(ArtWorkCreateDTO newArtWork) {

        ArtistEntity artist = artistRepository.findById(newArtWork.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artista com ID " + newArtWork.getArtistId() + " não encontrado."));

        ArtWorkEntity artWork = ArtWorkMapper.toEntity(newArtWork);

        artWork.setArtist(artist);

        ArtWorkEntity savedArtWork = artWorkRepository.save(artWork);

        return ArtWorkMapper.toResponse(savedArtWork);
    }

    @Override
    public List<ArtWorkResponseDTO> getAllArtWorks() {
        List<ArtWorkEntity> artWorkEntities = artWorkRepository.findAll();
        return artWorkEntities.stream()
                .map(ArtWorkMapper::toResponse)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public ArtWorkResponseDTO getArtWorkById(Long artWorkId) {
        ArtWorkEntity artWorkEntity = artWorkRepository.findById(artWorkId)
                .orElseThrow(() -> new RuntimeException("Obra de arte com ID " + artWorkId + " não encontrada."));
        return ArtWorkMapper.toResponse(artWorkEntity);
    }

    @Override
    public ArtWorkResponseDTO updateArtWork(Long artWorkId, ArtWorkCreateDTO updatedArtWork) {
        ArtWorkEntity existingArtWork = artWorkRepository.findById(artWorkId)
                .orElseThrow(() -> new RuntimeException("Obra de arte com ID " + artWorkId + " não encontrada."));

        ArtistEntity artist = artistRepository.findById(updatedArtWork.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artista com ID " + updatedArtWork.getArtistId() + " não encontrado."));

        existingArtWork.setTitle(updatedArtWork.getTitle());
        existingArtWork.setDescription(updatedArtWork.getDescription());
        existingArtWork.setImageUrl(updatedArtWork.getImageUrl());
        existingArtWork.setArtist(artist);

        ArtWorkEntity savedArtWork = artWorkRepository.save(existingArtWork);
        return ArtWorkMapper.toResponse(savedArtWork);
    }

    @Override
    public void deleteArtWork(Long artWorkId) {
        ArtWorkEntity existingArtWork = artWorkRepository.findById(artWorkId)
                .orElseThrow(() -> new RuntimeException("Obra de arte com ID " + artWorkId + " não encontrada."));
        artWorkRepository.delete(existingArtWork);
    }

}