package com.manh.controller;


import com.manh.model.JiraTicket;
import com.manh.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class QueryFormController {

    Logger logger  = LoggerFactory.getLogger("QueryFormController");

    @Autowired
    private TicketService service;
    @GetMapping("/query")
    public String showQueryForm(Model model) {
        // Add an empty JiraTicket object for form binding
        model.addAttribute("queryCriteria", new JiraTicket());
        model.addAttribute("tickets", List.of()); // Empty results by default
        return "ticket-query"; // Corresponds to the Thymeleaf HTML template
    }

    @PostMapping("/results")
    public String queryTickets(
            @ModelAttribute("queryCriteria") JiraTicket queryCriteria,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {
        logger.info("Received Query Result Request : "+ queryCriteria.toString());
        // Query tickets based on criteria
        List<JiraTicket> tickets = service.queryTicketsNew(
                queryCriteria.getStatus(),
                queryCriteria.getClassification(),
                queryCriteria.getTitle(),
                queryCriteria.getPriority(),
                queryCriteria.getTicket(),
                startDate,
                endDate);

        // Add query results and criteria back to the model
        model.addAttribute("queryCriteria", queryCriteria);
        model.addAttribute("tickets", tickets);

        // Return the query form view
        return "ticket-query";
    }
}