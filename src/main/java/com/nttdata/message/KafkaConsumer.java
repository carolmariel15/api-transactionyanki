package com.nttdata.message;

import com.nttdata.events.Event;
import com.nttdata.events.CreatedEvent;
import com.nttdata.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class KafkaConsumer {

    private final TransactionRepository transactionalRepository;

    @KafkaListener(topics = "topic-bootcoinm",groupId = "myGroup")
    public void consumerTransactionBootCoin(String e) {
        Event<?> event = null;
        if (event.getClass().isAssignableFrom(CreatedEvent.class)) {
            CreatedEvent createdEvent = (CreatedEvent) event;
            log.info("Recibido.... data={}",  createdEvent.getData().toString());

            var bm = createdEvent.getData();
            //if(bm.getPayMode()==2) addTransaction(bm);
        }
    }

    /*public void addTransaction(BootCoinMovement bm) {
        Mono<Transaction> t = null;

        t.map(v -> {
            v.setId(bm.getTransactionNumber());
            v.setClientId(bm.getApplicantId());
            v.setPhone(bm.getPhone());
            v.setCurrencyId(1);
            v.setTypeOperationId(2);
            v.setOriginAccount(bm.getAccountNumber());
            v.setAmount(bm.getAmount());
            v.setDateTransaction(LocalDateTime.now());
            return transactionalRepository.save(v);
        });

    }*/
}
