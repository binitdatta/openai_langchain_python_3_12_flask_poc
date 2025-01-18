package com.manh.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ticket_table_final", schema = "keycloak_spicey_jiras")
public class JiraTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Integer id;
    private String ticket;
    private String status;
    private String title;
    private String classification;
    private LocalDate dateCreated; // Changed to LocalDate
    private String priority;
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

//    public Ticket(String ticket, String status, String title, String classification, LocalDate dateCreated, String priority, String remarks) {
//        this.ticket = ticket;
//        this.status = status;
//        this.title = title;
//        this.classification = classification;
//        this.dateCreated = dateCreated;
//        this.priority = priority;
//        this.remarks = remarks;
//    }

//    public Ticket(Integer id, String ticket, String status, String title, String classification, LocalDate dateCreated, String priority, String remarks) {
//        this.id = id;
//        this.ticket = ticket;
//        this.status = status;
//        this.title = title;
//        this.classification = classification;
//        this.dateCreated = dateCreated;
//        this.priority = priority;
//        this.remarks = remarks;
//    }

    public JiraTicket() {
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "Id='" + id + '\'' +
                "ticket='" + ticket + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", classification='" + classification + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", priority='" + priority + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
