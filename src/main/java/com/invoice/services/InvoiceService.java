package com.invoice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.invoice.model.Invoice;
import com.invoice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    // Constructor-based dependency injection

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
    /**
     * Retrieves a map of invoice IDs and their corresponding tender types
     * for a given customer ID.
     *
     * @param customerId The ID of the customer
     * @return A map containing invoice IDs as keys and tender types as values
     */
    public Map<Long, String> getInvoicesAndTenderTypesForCustomer(Long customerId) {
        Map<Long, String> invoiceTypeMap = new HashMap<>();

        List<Invoice> invoices = invoiceRepository.findByCustomerId(customerId);
        for (Invoice invoice : invoices) {
            Object invoiceData = invoice.getInvoiceData();
            if (invoiceData != null) {
                try {
                    //Parse the invoiceData and get Tender Type
                    String type = extractTypeFromInvoiceData(invoiceData);
                    invoiceTypeMap.put(invoice.getInvoiceId(), type);
                } catch (JsonProcessingException e) {
                    // Handle JSON parsing error if needed
                }
            }
        }

        return invoiceTypeMap;
    }

    /**
     * Extracts the "type" value from the "invoice_data" JSON.
     *
     * @param invoiceData The JSON data in the "invoice_data" field
     * @return The extracted "type" value as a String
     * @throws JsonProcessingException If there's an issue parsing the JSON
     */
    public String extractTypeFromInvoiceData(Object invoiceData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(objectMapper.writeValueAsString(invoiceData));

        JsonNode tenderDetailsNode = rootNode.get("tenderDetails");
        if (tenderDetailsNode != null) {
            JsonNode typeNode = tenderDetailsNode.get("type");
            if (typeNode != null) {
                return typeNode.asText();
            }
        }

        return null; // Return null if "type" not found
    }
}
