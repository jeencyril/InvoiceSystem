package com.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INVOICE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @Column(name = "invoice_id")
    private long invoiceId;

    @Column(name = "customer_id")
    private long customerID;

    @Column(name = "invoice_data", columnDefinition = "json")
    private Object invoiceData;

}