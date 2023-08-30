package com.invoice.InvoiceSystem;

import com.invoice.controller.InvoiceController;
import com.invoice.services.InvoiceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    @Test
    public void testGetInvoicesAndTenderTypes() throws Exception {
        long customerId = 1;

        Map<Long, String> expectedResponse = new HashMap<>();
        expectedResponse.put(1001L, "Credit");
        expectedResponse.put(1002L, "Cash");

        // Mock the behavior of the invoiceService
        when(invoiceService.getInvoicesAndTenderTypesForCustomer(anyLong()))
                .thenReturn(expectedResponse);

        mockMvc.perform(get("/invoices/customer/123", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.1001").value("Credit"))
                .andExpect(jsonPath("$.1002").value("Cash"));
    }

/*
   Negative Test case
 */

    @Test
    public void testGetInvoiceTypeMapByCustomerId_NoInvoices() throws Exception {
        // Mock the behavior of the invoiceService when no invoices are found
        when(invoiceService.getInvoicesAndTenderTypesForCustomer(123L)).thenReturn(Collections.emptyMap());

        // Perform a GET request to the endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/invoices/customer/123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());  // Check the response JSON

        // Additional assertions or verifications can be added here
    }
}
