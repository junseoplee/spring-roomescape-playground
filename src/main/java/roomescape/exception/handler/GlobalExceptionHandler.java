package roomescape.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import roomescape.exception.NotFoundReservationException;

@ControllerAdvice // 전역 예외 처리
public class GlobalExceptionHandler {

  @ExceptionHandler({NotFoundReservationException.class, IllegalArgumentException.class})
  public ResponseEntity handleException(RuntimeException e) {
    return ResponseEntity.badRequest().build();
    // @ExceptionHandler 로 특정 예외 처리
    // badRequest() 는 400 Bad Request 상태 코드를 반환한다
  }
}
