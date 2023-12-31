package com.example.MarysPizza.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MarysPizza.models.Cliente;
import com.example.MarysPizza.repositories.ClienteRepository;

import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController{
    
    
    Logger log =  LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    ClienteRepository repository;
    
    @GetMapping
    public Page<Cliente> Index(@PageableDefault(size = 5)Pageable pageable) {
        return repository.findAll(pageable);
    }
    
    @GetMapping("{idCliente}")
    public ResponseEntity<Cliente> show(@PathVariable Long idCliente){
        log.info("buscar cliente com id" + idCliente);
        return ResponseEntity.ok(getCliente(idCliente));
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody @Valid Cliente cliente) {
        log.info("criar cliente" + cliente);
        repository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @DeleteMapping("{idCliente}")
    public ResponseEntity<Cliente> destroy(@PathVariable Long idCliente) {
        log.info("apagar cliente com id" + idCliente);
        repository.delete(getCliente(idCliente));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{idCliente}")
    public ResponseEntity<Cliente> update(@PathVariable Long idCliente, @RequestBody @Valid Cliente cliente) {
        log.info("atualizar cliente com id" + idCliente);
        getCliente(idCliente);
        cliente.setIdCliente(idCliente);
        repository.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    private Cliente getCliente(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente não existente")
        );  
    }
    
}