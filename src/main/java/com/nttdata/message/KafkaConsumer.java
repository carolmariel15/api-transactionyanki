package com.nttdata.message;

import com.nttdata.document.BootCoinMovement;
import com.nttdata.document.Transaction;
import com.nttdata.events.Event;
import com.nttdata.events.TransactionCreatedEvent;
import com.nttdata.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@AllArgsConstructor
@Component
public class KafkaConsumer {

    private final TransactionRepository transactionalRepository;

    @KafkaListener(topics = "topic-bootcoinm", containerFactory = "kafkaListenerContainerFactory")
    public void consumerTransactionBootCoin(Event<?> event) {
        if (event.getClass().isAssignableFrom(TransactionCreatedEvent.class)) {
            TransactionCreatedEvent customerCreatedEvent = (TransactionCreatedEvent) event;
            log.info("Recibido.... data={}",  customerCreatedEvent.getData().toString());

            var bm = customerCreatedEvent.getData();
            if(bm.getPayMode()==2) addTransaction(bm);
        }
    }

    public void addTransaction(BootCoinMovement bm) {
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

    }
}
