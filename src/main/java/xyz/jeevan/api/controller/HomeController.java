package xyz.jeevan.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by jeevan on 16/7/17.
 */
@Controller
@ApiIgnore
public class HomeController {

  /**
   * Redirect to swagger documentation page.
   */
  @RequestMapping("/")
  public String home() {
    return "redirect:swagger-ui.html";
  }
}
