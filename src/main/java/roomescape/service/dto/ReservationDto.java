package roomescape.service.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.domain.Reservation;

public class ReservationDto {

  private final Long id;
  private final String name;
  private final LocalDate date;
  private final LocalTime time;

  public ReservationDto(Long id, String name, LocalDate date, LocalTime time) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.time = time;
  }

  public static ReservationDto from(final Reservation reservation) {
    return new ReservationDto(
        reservation.getId(),
        reservation.getName(),
        reservation.getDate(),
        reservation.getTime()
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

  public LocalTime getTime() {
    return time;
  }
}
