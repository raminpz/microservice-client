package com.microserviclient.utilities.errors;

import com.microserviclient.model.entity.ClientEntity;
import com.microserviclient.utilities.constants.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validations {

    @Autowired
    Constants constants;

    public Mono<ClientEntity> validateCreateClient(ClientEntity client) {


        if(client.getClientType().getDescription().equals(constants.getClientPersonal())){
            return Mono.just(client);
        }else {
            return Mono.just(client);
        }
    }

}
