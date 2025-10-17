package gallery.example.digital_art_gallery.artists.infra.config;

import gallery.example.digital_art_gallery.artists.app.dtos.globalexception.ErrorResponseDTO;
import gallery.example.digital_art_gallery.artists.domain.exceptions.ArtistNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // handler p exceções de dominio (404)
    @ExceptionHandler(ArtistNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(ArtistNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponseDTO error = new ErrorResponseDTO(
                status.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, status);
    }

    // handler p erros de validacao (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String defaultMessage = ex.getBindingResult().getFieldError() != null
                ? ex.getBindingResult().getFieldError().getDefaultMessage()
                : "Erro de validação de dados.";

        ErrorResponseDTO error = new ErrorResponseDTO(
                status.value(),
                "Requisição inválida: " + defaultMessage,
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, status);
    }

    // handler generico (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponseDTO error = new ErrorResponseDTO(
                status.value(),
                "Um erro inesperado ocorreu. Contate o administrador.",
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, status);
    }
}
