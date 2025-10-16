package gallery.example.digital_art_gallery.app.dtos.artist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtistCreateDTO {

    private String name;
    private String bio;
    private String email;
    private String instagram;
    private String twitter;
    private String website;

}
