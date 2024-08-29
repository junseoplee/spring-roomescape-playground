package roomescape.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationRepository;
import roomescape.exception.ReservationNotFoundException;
import roomescape.service.dto.ReservationDto;

@Transactional
@Service
public class ReservationService {

  private final ReservationRepository reservationRepository;

  public ReservationService(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  @Transactional(readOnly = true)
  public List<ReservationDto> findAll() {
    return reservationRepository.findAll().stream()
                                .map(ReservationDto::from)
                                .toList();
  }

  public ReservationDto save(final ReservationDto reservationDto) {
    Reservation newReservation = reservationDto.toEntity();
    Reservation savedReservation = reservationRepository.save(newReservation);
    return ReservationDto.from(savedReservation);
  }

  public void deleteById(Long id) {
    if (!reservationRepository.deleteById(id)) {
      throw new ReservationNotFoundException();
    }
  }
}
