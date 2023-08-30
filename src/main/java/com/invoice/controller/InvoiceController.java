package com.invoice.controller;


import com.invoice.services.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    // Constructor-based dependency injection
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /**
     * Retrieves a map of invoice IDs and their corresponding tender types
     * for a given customer ID.
     *
     * @param customerId The ID of the customer
     * @return A ResponseEntity containing the map of invoice IDs and types
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Map<Long, String>> getInvoicesAndTenderTypes(@PathVariable long customerId) {
        Map<Long, String> result = invoiceService.getInvoicesAndTenderTypesForCustomer(customerId);
        return ResponseEntity.ok(result);
    }
}
