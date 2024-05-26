package fer.infsus.atk.DTO;


import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp, String message, String details) {

}

