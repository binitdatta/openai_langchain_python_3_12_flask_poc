package com.manh.service;


import com.manh.model.JiraTicket;
import com.manh.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    public List<JiraTicket> getAllTickets() {
        return repository.findAll();
    }

    public JiraTicket getTicketById(String ticket) {
        return repository.findById(ticket).orElse(null);
    }

    public JiraTicket saveOrUpdate(JiraTicket ticket) {
        if (ticket.getId() != null) {
            System.out.println("Updating ticket with ID: " + ticket.getId());
        } else {
            System.out.println("Creating a new ticket");
        }
        return repository.save(ticket);
    }


    public void deleteTicket(String ticket) {
        repository.deleteById(ticket);
    }

    public List<JiraTicket> queryTickets(String status, String classification, String title, String priority, String ticketNumber, LocalDate startDate, LocalDate endDate) {
        return repository.queryTickets(status, classification, title, priority, ticketNumber, startDate, endDate);
    }

    public List<JiraTicket> queryTicketsNew(String status, String classification, String title, String priority, String ticket, LocalDate startDate, LocalDate endDate) {
        // Convert empty strings to null

        // Remove surrounding quotes from the title if present
        if (title != null && title.startsWith("'") && title.endsWith("'")) {
            title = title.substring(1, title.length() - 1);
        }

        status = (status != null && status.isEmpty()) ? null : status;
        // title = (title != null && title.isEmpty()) ? null : title;
        title = (title != null && !title.isEmpty()) ? "%" + title + "%" : null;

        classification = (classification != null && classification.isEmpty()) ? null : classification;
        priority = (priority != null && priority.isEmpty()) ? null : priority;
        ticket = (ticket != null && ticket.isEmpty()) ? null : ticket;

        return repository.queryTickets(status, classification, title, priority, ticket, startDate, endDate);
    }

    // Method to count tickets by status
    public long countTicketsByStatus(String status) {
        return (status != null && !status.isEmpty()) ? repository.countByStatus(status) : 0;
    }

    // Method to count tickets by priority
    public long countTicketsByPriority(String priority) {
        return (priority != null && !priority.isEmpty()) ? repository.countByPriority(priority) : 0;
    }

    // Method to count tickets by classification
    public long countTicketsByClassification(String classification) {
        return (classification != null && !classification.isEmpty()) ? repository.countByClassification(classification) : 0;
    }

    // Method to count tickets within a date range
    public long countTicketsByDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return 0; // Return 0 if either date is null
        }
        return repository.countByDateRange(startDate, endDate);
    }

    // Method to count tickets by status and priority
    public long countTicketsByStatusAndPriority(String status, String priority) {
        if ((status == null || status.isEmpty()) || (priority == null || priority.isEmpty())) {
            return 0;
        }
        return repository.countByStatusAndPriority(status, priority);
    }

    // Method to count tickets by classification and date range
    public long countTicketsByClassificationAndDateRange(String classification, LocalDate startDate, LocalDate endDate) {
        if (classification == null || classification.isEmpty() || startDate == null || endDate == null) {
            return 0;
        }
        return repository.countByClassificationAndDateRange(classification, startDate, endDate);
    }
}

