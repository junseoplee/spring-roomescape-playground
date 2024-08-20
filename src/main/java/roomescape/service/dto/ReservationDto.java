package roomescape.service.dto;

import java.time.LocalDate;
import roomescape.domain.Reservation;

public class ReservationDto {

  private final Long id;
  private final String name;
  private final LocalDate date;
  private final Long timeId;
  private final ReservationTimeDto time;

  public ReservationDto(Long id, String name, LocalDate date, Long timeId, ReservationTimeDto time) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.timeId = timeId;
    this.time = time;
  }

  public static ReservationDto from(final Reservation reservation) {
    return new ReservationDto(
        reservation.getId(),
        reservation.getName(),
        reservation.getDate(),
        reservation.getTime().getId(),
        ReservationTimeDto.from(reservation.getTime())
    );
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public LocalDate getDate() {
    return date;
  }

  public Long getTimeId() {
    return timeId;
  }

  public ReservationTimeDto getTime() {
    return time;
  }
}
