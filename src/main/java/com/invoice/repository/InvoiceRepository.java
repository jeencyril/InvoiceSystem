package com.invoice.repository;

import com.invoice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {

    /**
     * Retrieves a list of invoices for a given customer ID.
     *
     * @param customerId The ID of the customer
     * @return A list of invoices associated with the customer ID
     */
    List<Invoice> findByCustomerId(long customerId);
}