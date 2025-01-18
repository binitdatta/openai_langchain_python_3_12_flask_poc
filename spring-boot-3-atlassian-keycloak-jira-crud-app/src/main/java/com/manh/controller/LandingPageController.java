package com.manh.controller;

/**
 * Controller class for handling requests to the landing page.
 *
 * This class defines the endpoint for displaying the landing page of the application.
 * It utilizes Spring MVC annotations to map the root URL ("/") to a Thymeleaf template.
 *
 * <p><b>Usage:</b></p>
 * This controller is automatically discovered by Spring Boot's component scanning and
 * serves the Thymeleaf template `landing-page.html`.
 *
 * @author [Binit Datta]
 * @since 1.0
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPageController {

    @GetMapping("/")
    public String showLandingPage() {
        return "landing-page"; // Refers to the Thymeleaf template `landing-page.html`
    }
}