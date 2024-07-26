package roomescape.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import roomescape.domain.Reservation;

@Controller
public class ReservationController {

  private List<Reservation> reservations = new ArrayList<>();

  @GetMapping("/reservation")
  public String reservation() {
    return "reservation";
  } // GET /reservation 요청이 들어왔을 때 reservation.html 파일을 응답한다
  // 단순히 reservation 뷰를 반환한다

  @GetMapping("/reservations")
  public ResponseEntity<List<Reservation>> readReservation() {
    return ResponseEntity.ok().body(reservations);
  } // GET /reservations 요청이 들어왔을 때 저장된 모든 예약 정보를 반환한다
} // ResponseEntity 는 HTTP 응답을 위한 클래스이다. ok() 메소드는 200 OK 상태 코드를 반환한다
// body() 메소드는 응답 본문에 들어갈 데이터를 설정한다. 반환할 데이터는 reservations 필드에 저장된 예약 정보이다
