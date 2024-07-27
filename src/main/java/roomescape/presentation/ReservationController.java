package roomescape.presentation;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import roomescape.domain.ErrorMessage;
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
    return ResponseEntity.created(URI.create("/reservations/" + newReservation.getId()))
                         .header("Content-Type", "application/json")
                         .body(newReservation);
    // POST /reservations 요청이 들어왔을 때 새로운 예약 정보를 저장한다
    // @RequestBody 어노테이션은 요청 본문에 들어있는 데이터를 Reservation 객체로 변환한다
    // ResponseEntity.created() 201 Created 상태 코드를 반환한다
    // URI.create() 새로 생성된 예약 정보의 URI 를 생성한다
    // header() 메서드는 Content-Type 헤더를 설정한다
  }

  @DeleteMapping("/reservations/{id}")
  public ResponseEntity<Void> deleteReservation(@PathVariable(name = "id") Long id) {
    Reservation deletingReservation =
        reservations.stream()
                    .filter(reservation -> Objects.equals(reservation.getId(), id))
                    .findAny()
                    .orElseThrow(() -> new RuntimeException(ErrorMessage.NO_RESERVATION.getMessage()));
    reservations.remove(deletingReservation);
    return ResponseEntity.noContent().build();
    // DELETE /reservations/{id} 요청이 들어왔을 때 지정된 id의 예약 정보를 삭제한다
    // @PathVariable 어노테이션은 URL 경로의 {id} 값을 매개변수 id에 바인딩한다
    // 삭제가 완료되면 204 No Content 상태 코드를 반환한다
  }
}
