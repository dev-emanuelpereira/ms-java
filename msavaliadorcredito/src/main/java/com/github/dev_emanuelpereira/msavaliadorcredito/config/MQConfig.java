package com.github.dev_emanuelpereira.msavaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.emissao-cartoes}")
    private String emissaoCartoesQueue;

    public Queue queueEmissaoCartoes(){
        return new Queue(emissaoCartoesQueue, true);
    }
}
