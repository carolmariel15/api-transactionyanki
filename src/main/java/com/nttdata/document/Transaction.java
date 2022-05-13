package com.nttdata.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped.Nullable;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "transaction_yanki")
public class Transaction {

	@Id
	private String id;
    @Nullable
    private Integer typeAccountId;
    private String clientId;
    private String phone;
    @Nullable
    private String accountId;
    private Integer currencyId;
    private Integer typeOperationId;
    @Nullable
    private String originAccount;
    @Nullable
    private String destinationAccount;
    private Double amount;
    private LocalDateTime dateTransaction;

    
}
