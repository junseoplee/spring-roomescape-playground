package roomescape.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import roomescape.exception.ErrorMessage;

public class Reservation {

  private final Long id;

  private final String name;

  private final LocalDate date;

  private final LocalTime time;

  public Reservation(Long id, String name, LocalDate date, LocalTime time) {
    validateDate(date);
    this.id = id;
    this.name = name;
    this.date = date;
    this.time = time;
  }

  public static Reservation of(Long id, Reservation reservation) {
    return new Reservation(id, reservation.name, reservation.date, reservation.time);
  }

  private void validateDate(LocalDate date) {
    if (date.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
    }
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Reservation reservation)) {
      return false;
    }
    return Objects.equals(id, reservation.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
