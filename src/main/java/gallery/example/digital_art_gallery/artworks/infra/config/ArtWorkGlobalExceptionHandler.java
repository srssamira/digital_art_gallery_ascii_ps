package gallery.example.digital_art_gallery.artworks.infra.config;

import gallery.example.digital_art_gallery.artworks.app.dtos.globalexception.ArtWorkErrorResponseDTO;
import gallery.example.digital_art_gallery.artworks.domain.ArtWorkNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArtWorkGlobalExceptionHandler {

    // handler p exceções de dominio (404)
    @ExceptionHandler(ArtWorkNotFoundException.class)
    public ResponseEntity<ArtWorkErrorResponseDTO> handleNotFound(ArtWorkNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ArtWorkErrorResponseDTO error = new ArtWorkErrorResponseDTO(
                status.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, status);
    }

    // handler p erros de validacao (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArtWorkErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String defaultMessage = ex.getBindingResult().getFieldError() != null
                ? ex.getBindingResult().getFieldError().getDefaultMessage()
                : "Erro de validação de dados.";

        ArtWorkErrorResponseDTO error = new ArtWorkErrorResponseDTO(
                status.value(),
                "Requisição inválida: " + defaultMessage,
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, status);
    }

    // handler generico (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ArtWorkErrorResponseDTO> handleGenericException(HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ArtWorkErrorResponseDTO error = new ArtWorkErrorResponseDTO(
                status.value(),
                "Um erro inesperado ocorreu. Contate o administrador.",
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, status);
    }
}
