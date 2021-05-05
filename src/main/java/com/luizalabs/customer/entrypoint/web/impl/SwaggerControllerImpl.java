package com.luizalabs.customer.entrypoint.web.impl;

import com.luizalabs.customer.entrypoint.web.SwaggerController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerControllerImpl implements SwaggerController {
  @RequestMapping({"/", "/docs", "/swagger", "/swagger-ui.html"})
  public String redirect() {
    return "redirect:/swagger-ui/";
  }
}
