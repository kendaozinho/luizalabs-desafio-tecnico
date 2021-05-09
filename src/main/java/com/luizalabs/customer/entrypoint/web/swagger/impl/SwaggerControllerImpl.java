package com.luizalabs.customer.entrypoint.web.swagger.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerControllerImpl {
  public SwaggerControllerImpl() {
  }

  @RequestMapping({"/", "/docs", "/swagger", "/swagger-ui.html"})
  public String redirect() {
    return "redirect:/swagger-ui/";
  }
}
