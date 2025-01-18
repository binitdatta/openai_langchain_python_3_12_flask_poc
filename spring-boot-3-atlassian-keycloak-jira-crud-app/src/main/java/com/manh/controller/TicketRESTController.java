package com.manh.controller;

import com.manh.model.JiraTicket;
import com.manh.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tickets-query")
public class TicketRESTController {

    @Autowired
    private TicketService service;

    @PostMapping("/results")
    public List<JiraTicket> queryTickets(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String classification,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) String ticketNumber,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return service.queryTickets(status, classification, title, priority, ticketNumber, startDate, endDate);
    }

    @GetMapping("/bot/results")
    public List<JiraTicket> queryTicketsForBots(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String classification,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) String ticketNumber,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return service.queryTickets(status, classification, title, priority, ticketNumber, startDate, endDate);
    }

    // Endpoint to count tickets by status
    @GetMapping("/count/status")
    public long countTicketsByStatus(@RequestParam String status) {
        return service.countTicketsByStatus(status);
    }

    // Endpoint to count tickets by priority
    @GetMapping("/count/priority")
    public long countTicketsByPriority(@RequestParam String priority) {
        return service.countTicketsByPriority(priority);
    }

    // Endpoint to count tickets by classification
    @GetMapping("/count/classification")
    public long countTicketsByClassification(@RequestParam String classification) {
        return service.countTicketsByClassification(classification);
    }

    // Endpoint to count tickets within a date range
    @GetMapping("/count/date-range")
    public long countTicketsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return service.countTicketsByDateRange(startDate, endDate);
    }

    // Endpoint to count tickets by status and priority
    @GetMapping("/count/status-priority")
    public long countTicketsByStatusAndPriority(
            @RequestParam String status,
            @RequestParam String priority) {
        return service.countTicketsByStatusAndPriority(status, priority);
    }

    // Endpoint to count tickets by classification and date range
    @GetMapping("/count/classification-date-range")
    public long countTicketsByClassificationAndDateRange(
            @RequestParam String classification,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return service.countTicketsByClassificationAndDateRange(classification, startDate, endDate);
    }
}
