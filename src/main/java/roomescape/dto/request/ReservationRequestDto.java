package roomescape.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationRequestDto {

  @NotBlank(message = "[ERROR] 이름이 비어 있습니다.")
  private final String name;

  @NotNull(message = "[ERROR] 날짜가 비어 있습니다.")
  private final LocalDate date;

  @NotNull(message = "[ERROR] 시간이 비어 있습니다.")
  private final LocalTime time;

  public ReservationRequestDto(String name, LocalDate date, LocalTime time) {
    this.name = name;
    this.date = date;
    this.time = time;
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
