package com.nttdata.message;

import com.nttdata.document.Transaction;
import reactor.core.publisher.Mono;

public interface KafkaConsumer {

    public Mono<Transaction> addTransaction() ;

}
