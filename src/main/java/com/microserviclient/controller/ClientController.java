package com.microserviclient.controller;


import com.microserviclient.model.entity.ClientEntity;
import com.microserviclient.model.request.CreateClientRequest;
import com.microserviclient.model.request.UpdateClientRequest;
import com.microserviclient.service.ClientService;
import com.microserviclient.utilities.constants.Constants;
import com.microserviclient.utilities.errors.DuplicatedUniqueFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/banking")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    Constants constants;

    @RequestMapping("/")
    public String home(){
        return "Client-Service running at port: " + constants.getClientPort();
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<ClientEntity>> createClient(@RequestBody CreateClientRequest clientDTO) {
        return clientService.create(clientDTO)
                .flatMap(createdClient -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(createdClient)))
                .onErrorResume(DuplicatedUniqueFieldException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).build()))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<ClientEntity>> updateClient(@RequestBody UpdateClientRequest clientDTO) {
        return clientService.update(clientDTO)
                .flatMap(updatedClient -> Mono.just(ResponseEntity.ok(updatedClient)))
                .onErrorResume(DuplicatedUniqueFieldException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).build()))
                .onErrorResume(NoSuchElementException.class, error -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/findAll")
    public Mono<ResponseEntity<Flux<ClientEntity>>> getAllClients(){
        //return clientService.findAll();
        return Mono.just(ResponseEntity.ok().body(clientService.findAll()));
    }

    @GetMapping("/getById/{id}")
    public Mono<ResponseEntity<ClientEntity>> getClientById(@PathVariable(value = "id") String clientId) {
        return clientService.findById(clientId)
                .map(saveClient -> ResponseEntity.ok(saveClient))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/getByDocumentNumber/{doc}")
    public Mono<ResponseEntity<ClientEntity>> getClientByDocumentNumber(@PathVariable(value = "doc") String documentNumber) {
        return clientService.findByDocumentNumber(documentNumber)
                .map(saveClient -> ResponseEntity.ok(saveClient))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/getByEmail/{email}")
    public Mono<ResponseEntity<ClientEntity>> getClientByEmail(@PathVariable(value = "email") String email) {
        return clientService.findByEmail(email)
                .map(saveClient -> ResponseEntity.ok(saveClient))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/getByName/{name}")
    public Flux<ClientEntity> getClientByName(@PathVariable(value = "name") String name) {
        return clientService.findByName(name);
    }

    @GetMapping("/getByRuc/{ruc}")
    public Mono<ResponseEntity<ClientEntity>> getClientByRuc(@PathVariable(value = "ruc") String ruc) {
        return clientService.findByRuc(ruc)
                .map(saveClient -> ResponseEntity.ok(saveClient))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Delete client by id.
     */
    @DeleteMapping("/removeById/{id}")
    public Mono<ClientEntity> deleteClient(@PathVariable("id") String clientId) {
        return clientService.removeById(clientId);
    }

    @DeleteMapping("/deleteClient/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable(value = "id") String clientId) {

        return clientService.deleteClient(clientId)  // First, you search the Bucket you want to delete.
                .flatMap(existingClient ->Mono.just(new ResponseEntity<Void>(HttpStatus.OK))                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));  // or you return 404 NOT FOUND to say the Bucket was not found
    }

    @DeleteMapping("/deleteAllClients")
    public Mono<Void> deleteAllClients(){
        return clientService.deleteAll();
    }

}
