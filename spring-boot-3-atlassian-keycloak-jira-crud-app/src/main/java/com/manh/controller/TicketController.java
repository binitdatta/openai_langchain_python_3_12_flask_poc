package com.manh.controller;


import com.manh.model.JiraTicket;
import com.manh.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @GetMapping
    public String listTickets(Model model) {
        List<JiraTicket> tickets = service.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "ticket-list";
    }

    @GetMapping("/new")
    public String newTicketForm(Model model) {
        model.addAttribute("jiraTicket", new JiraTicket());
        return "ticket-form";
    }

    @PostMapping
    public String saveTicket(@ModelAttribute("jiraTicket") JiraTicket jiraTicket, BindingResult result) {
        if (result.hasErrors()) {
            return "ticket-form"; // Return to form with errors
        }
        service.saveOrUpdate(jiraTicket);
        return "redirect:/tickets";
    }


    @GetMapping("/edit/{id}")
    public String editTicketForm(@PathVariable String id, Model model) {
        JiraTicket ticket = service.getTicketById(id);
        model.addAttribute("jiraTicket", ticket);
        return "ticket-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable String id) {
        service.deleteTicket(id);
        return "redirect:/tickets";
    }
}
