package roomescape.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {

  private final Long id;
  @NotBlank
  private final String name;
  @NotNull
  private final LocalDate date;
  @NotNull
  private final LocalTime time;

  public Reservation(Long id, String name, LocalDate date, LocalTime time) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.time = time;
  }

  public static Reservation createNewReservation (Long id, Reservation reservation) {
    return new Reservation(id, reservation.name, reservation.date, reservation.time);
  }

  public long getId() {
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
