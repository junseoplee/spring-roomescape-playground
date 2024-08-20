package roomescape.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/reservation")
  public String reservation() {
    return "reservation";
  }

  @GetMapping("/time")
  public String time() {
    return "time";
  }
}