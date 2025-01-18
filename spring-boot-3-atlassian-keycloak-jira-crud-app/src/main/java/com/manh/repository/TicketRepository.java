package com.manh.repository;

import com.manh.model.JiraTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository<JiraTicket, String> {

    @Query("SELECT t FROM JiraTicket t " +
            "WHERE (:status IS NULL OR t.status = :status) " +
            "AND (:classification IS NULL OR t.classification = :classification) " +
            "AND (:title IS NULL OR t.title LIKE CONCAT('%', :title, '%')) "+
            "AND (:priority IS NULL OR t.priority = :priority) " +
            "AND (:ticketNumber IS NULL OR t.ticket = :ticketNumber) " +
            "AND (:startDate IS NULL OR t.dateCreated >= :startDate) " +
            "AND (:endDate IS NULL OR t.dateCreated <= :endDate)")
    List<JiraTicket> queryTickets(@Param("status") String status,
                                  @Param("classification") String classification,
                                  @Param("title") String title,
                                  @Param("priority") String priority,
                                  @Param("ticketNumber") String ticketNumber,
                                  @Param("startDate") LocalDate startDate,
                                  @Param("endDate") LocalDate endDate);

    // Query to count tickets by status
    @Query("SELECT COUNT(t) FROM JiraTicket t WHERE t.status = :status")
    long countByStatus(@Param("status") String status);

    // Query to count tickets by priority
    @Query("SELECT COUNT(t) FROM JiraTicket t WHERE t.priority = :priority")
    long countByPriority(@Param("priority") String priority);

    // Query to count tickets by classification
    @Query("SELECT COUNT(t) FROM JiraTicket t WHERE t.classification = :classification")
    long countByClassification(@Param("classification") String classification);

    // Query to count tickets within a date range
    @Query("SELECT COUNT(t) FROM JiraTicket t WHERE t.dateCreated BETWEEN :startDate AND :endDate")
    long countByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Combined query to count tickets by status and priority
    @Query("SELECT COUNT(t) FROM JiraTicket t WHERE t.status = :status AND t.priority = :priority")
    long countByStatusAndPriority(@Param("status") String status, @Param("priority") String priority);

    // Combined query to count tickets by classification and date range
    @Query("SELECT COUNT(t) FROM JiraTicket t WHERE t.classification = :classification AND t.dateCreated BETWEEN :startDate AND :endDate")
    long countByClassificationAndDateRange(@Param("classification") String classification,
                                           @Param("startDate") LocalDate startDate,
                                           @Param("endDate") LocalDate endDate);
}