package gallery.example.digital_art_gallery.services.impl;

import gallery.example.digital_art_gallery.app.dtos.artwork.ArtWorkCreateDTO;
import gallery.example.digital_art_gallery.app.dtos.artwork.ArtWorkResponseDTO;
import gallery.example.digital_art_gallery.app.mappers.ArtWorkMapper;
import gallery.example.digital_art_gallery.infra.entities.ArtWorkEntity;
import gallery.example.digital_art_gallery.infra.entities.ArtistEntity;
import gallery.example.digital_art_gallery.infra.repositories.ArtWorkJpaRepository;
import gallery.example.digital_art_gallery.infra.repositories.ArtistJpaRepository;
import gallery.example.digital_art_gallery.services.ArtWorkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtWorkServiceImpl implements ArtWorkService {

    private final ArtWorkJpaRepository artWorkJpaRepository;
    private final ArtistJpaRepository artistJpaRepository;

    public ArtWorkServiceImpl(ArtWorkJpaRepository artWorkJpaRepository, ArtistJpaRepository artistJpaRepository) {
        this.artWorkJpaRepository = artWorkJpaRepository;
        this.artistJpaRepository = artistJpaRepository;
    }

    @Override
    public ArtWorkResponseDTO createArtWork(ArtWorkCreateDTO newArtWork) {

        ArtistEntity artist = artistJpaRepository.findById(newArtWork.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artista com ID " + newArtWork.getArtistId() + " não encontrado."));

        ArtWorkEntity artWork = ArtWorkMapper.toEntity(newArtWork);

        artWork.setArtist(artist);

        ArtWorkEntity savedArtWork = artWorkJpaRepository.save(artWork);

        return ArtWorkMapper.toResponse(savedArtWork);
    }

    @Override
    public List<ArtWorkResponseDTO> getAllArtWorks() {
        List<ArtWorkEntity> artWorkEntities = artWorkJpaRepository.findAll();
        return artWorkEntities.stream()
                .map(ArtWorkMapper::toResponse)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public ArtWorkResponseDTO getArtWorkById(Long artWorkId) {
        ArtWorkEntity artWorkEntity = artWorkJpaRepository.findById(artWorkId)
                .orElseThrow(() -> new RuntimeException("Obra de arte com ID " + artWorkId + " não encontrada."));
        return ArtWorkMapper.toResponse(artWorkEntity);
    }

    @Override
    public ArtWorkResponseDTO updateArtWork(Long artWorkId, ArtWorkCreateDTO updatedArtWork) {
        ArtWorkEntity existingArtWork = artWorkJpaRepository.findById(artWorkId)
                .orElseThrow(() -> new RuntimeException("Obra de arte com ID " + artWorkId + " não encontrada."));

        ArtistEntity artist = artistJpaRepository.findById(updatedArtWork.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artista com ID " + updatedArtWork.getArtistId() + " não encontrado."));

        existingArtWork.setTitle(updatedArtWork.getTitle());
        existingArtWork.setDescription(updatedArtWork.getDescription());
        existingArtWork.setImageUrl(updatedArtWork.getImageUrl());
        existingArtWork.setArtist(artist);

        ArtWorkEntity savedArtWork = artWorkJpaRepository.save(existingArtWork);
        return ArtWorkMapper.toResponse(savedArtWork);
    }

}