package com.microserviclient.service;

import com.microserviclient.model.entity.ClientEntity;
import com.microserviclient.model.request.CreateClientRequest;
import com.microserviclient.model.request.UpdateClientRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Mono<ClientEntity> create(CreateClientRequest clientDTO);
    Mono<ClientEntity> update(UpdateClientRequest clientDTO);
    Flux<ClientEntity> findAll();
    Mono<ClientEntity> findById(String id);
    Mono<ClientEntity> findByDocumentNumber(String documentNumber);
    Mono<ClientEntity> findByEmail(String email);
    Flux<ClientEntity> findByName(String name);
    Mono<ClientEntity> findByRuc(String ruc);
    // Logical on status
    Mono<ClientEntity> deleteClient(String id);
    // on database
    Mono<ClientEntity> removeById(String id);
    // on database
    Mono<Void> deleteAll();

}
