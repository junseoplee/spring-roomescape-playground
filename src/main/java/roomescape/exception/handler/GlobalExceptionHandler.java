package roomescape.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import roomescape.exception.ReservationNotFoundException;
import roomescape.exception.dto.ErrorResponseDto;

@RestControllerAdvice // 전역 예외 처리
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDto> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult()
                            .getAllErrors()
                            .stream()
                            .map(error -> error.getDefaultMessage())
                            .collect(Collectors.toList());

    ErrorResponseDto errorResponse = new ErrorResponseDto(
        LocalDateTime.now(),
        HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        String.join(", ", errors),
        ex.getBindingResult().getTarget().toString()
    );

    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(ReservationNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleNotFoundReservationException(ReservationNotFoundException e, HttpServletRequest request) {
    ErrorResponseDto errorResponse = new ErrorResponseDto(
        LocalDateTime.now(),
        HttpStatus.NOT_FOUND.value(),
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        e.getMessage(),
        request.getRequestURI()
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
    ErrorResponseDto errorResponse = new ErrorResponseDto(
        LocalDateTime.now(),
        HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        e.getMessage(),
        request.getRequestURI()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }
}
