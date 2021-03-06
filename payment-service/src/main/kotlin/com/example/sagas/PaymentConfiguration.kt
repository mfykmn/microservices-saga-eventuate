package com.example.sagas

import com.example.sagas.sagas.createorder.ReserveInvoiceCommandHandler
import io.eventuate.tram.commands.consumer.CommandDispatcher
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory
import io.eventuate.tram.sagas.spring.participant.SagaParticipantConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ComponentScan
@Import(SagaParticipantConfiguration::class)
@EnableJpaRepositories
@EnableAutoConfiguration
class PaymentConfiguration {

    @Bean
    fun reserveInvoiceCommandDispatcher(
        sagaCommandDispatcherFactory: SagaCommandDispatcherFactory,
        target: ReserveInvoiceCommandHandler
    ): CommandDispatcher {
        return sagaCommandDispatcherFactory
            .make("reserveInvoiceCommandDispatcher",
                target.commandHandlerDefinitions()
        )
    }
}
