package dto;

import java.time.LocalDateTime;

public record ErrorMessage(int statusCode, String message, LocalDateTime timestamp) {
}
