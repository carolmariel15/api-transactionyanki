package com.nttdata.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BootCoinMovement {

    private String id;
    private String applicantId;
    private Double amount;
    private Integer payMode;
    private Boolean accepted;
    private String accountNumber;
    private String phone;
    private String transactionNumber;
    private String destinationAccount;
}
