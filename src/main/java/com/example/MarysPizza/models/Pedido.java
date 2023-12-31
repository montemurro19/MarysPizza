package com.example.MarysPizza.models;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.MarysPizza.controllers.PedidoController;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @NotNull 
    @Past
    private LocalDateTime horarioPedido;

    @Min(0)
    private BigDecimal valorTotal;

    private String observacao;

    private String status;

    private Number avaliacao;

    @OneToMany
    private List<Item> items;

    @OneToOne
    private Cliente cliente;

    @OneToOne
    private Endereco endereco;

    public EntityModel<Pedido> toModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(PedidoController.class).show(idPedido)).withSelfRel(),
            linkTo(methodOn(PedidoController.class).destroy(idPedido)).withRel("delete"),
            linkTo(methodOn(PedidoController.class).index(Pageable.unpaged(), null)).withRel("listAll")
        );
    }
    
}
