package roomescape.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import roomescape.domain.Reservation;

@Controller
public class ReservationController {

  private List<Reservation> reservations = new ArrayList<>();
  private AtomicLong index = new AtomicLong(1);

  @GetMapping("/reservation")
  public String reservation() {
    return "reservation";
    // GET /reservation 요청이 들어왔을 때 reservation.html 파일을 응답한다
    // 단순히 reservation 뷰를 반환한다
  }

  @GetMapping("/reservations")
  public ResponseEntity<List<Reservation>> readReservation() {
    return ResponseEntity.ok().body(reservations);
    // GET /reservations 요청이 들어왔을 때 저장된 모든 예약 정보를 반환한다
    // ResponseEntity 는 HTTP 응답을 위한 클래스이다. ok() 메소드는 200 OK 상태 코드를 반환한다
    // body() 메서드는 응답 본문에 들어갈 데이터를 설정한다. 반환할 데이터는 reservations 필드에 저장된 예약 정보이다
  }

  @PostMapping("/reservations")
  public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
    Reservation newReservation
        = Reservation.createNewReservation(index.getAndIncrement(), reservation);
    reservations.add(newReservation);
    return ResponseEntity.created(URI.create("/reservations"
        + newReservation.getId())).build();
    // POST /reservations 요청이 들어왔을 때 새로운 예약 정보를 저장한다
    // @RequestBody 어노테이션은 요청 본문에 들어있는 데이터를 Reservation 객체로 변환한다
    // ResponseEntity.created() 201 Created 상태 코드를 반환한다
    // URI.create() 새로 생성된 예약 정보의 URI 를 생성한다
    // build() 본문 없이 ResponseEntity 객체를 빌드한다
  }
}
