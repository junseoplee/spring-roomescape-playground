package roomescape.domain;

import java.util.List;

public interface ReservationTimeRepository {

  List<ReservationTime> findAll();

  ReservationTime findById(Long id);

  ReservationTime save(ReservationTime reservationTime);

  boolean deleteById(Long id);
}
