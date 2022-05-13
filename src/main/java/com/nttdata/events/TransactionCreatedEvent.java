package com.nttdata.events;

import com.nttdata.document.BootCoinMovement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransactionCreatedEvent extends Event<BootCoinMovement> {
}
