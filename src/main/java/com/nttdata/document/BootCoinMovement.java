package com.nttdata.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Unwrapped.Nullable;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BootCoinMovement {

    private Integer id;
    private String applicantId;
    private Double amount;
    private Integer payMode;
    private Boolean accepted;
    private String accountNumber;
    private String phone;
    private String transactionNumber;
    private String destinationAccount;
}
