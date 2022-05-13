package com.nttdata.message;

import com.nttdata.document.BootCoinMovement;
import com.nttdata.document.Transaction;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@AllArgsConstructor
@Component
public class KafkaConsumerImpl implements  KafkaConsumer{
    private static Mono<BootCoinMovement> bootCoinMovementMono = null;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerImpl.class);

    @KafkaListener(topics = "topic-bootcoinm",groupId = "myGroup")
    public void consumerTransactionBootCoin(BootCoinMovement bm) {
        System.out.println("entraa ");
        System.out.println(bm);

        LOGGER.info("Recibido.... data={}"+ bm);

            if(bm.getPayMode()==2) bootCoinMovementMono = Mono.just(bm);

    }

    @Override
    public Mono<Transaction> addTransaction() {
        var t = Mono.just(new Transaction());

        return bootCoinMovementMono.flatMap(bm -> {
            return t.map(v -> {
                v.setId(bm.getTransactionNumber());
                v.setClientId(bm.getApplicantId());
                v.setPhone(bm.getPhone());
                v.setCurrencyId(1);
                v.setTypeOperationId(2);
                v.setOriginAccount(bm.getAccountNumber());
                v.setAmount(bm.getAmount());
                v.setDateTransaction(LocalDateTime.now());
                return v;
            });
        });

    }

}
