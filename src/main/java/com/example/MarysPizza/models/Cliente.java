package com.example.MarysPizza.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {
    @Id

    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idCliente;

    @NotBlank
    @Pattern(regexp = "^\\S+\\s\\S+(\\s\\S+)*$")
    private String nome;

    @NotBlank
    @Pattern(regexp = "^\\d{3}.\\d{3}.\\d{3}-\\d{2}$")
    private String cpf;

    @NotBlank 
    @Email 
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s\\d{4,5}-\\d{4}$")
    private String telefone;

    @NotBlank 
    @Size(min = 8) 
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$&*]).+$")
    private String senha;

    @NotBlank 
    @Pattern(regexp = "^[a-zA-Z0-9_]+$")
    private String usuario;

    @NotNull 
    @Past
    private LocalDate dataNascimento;
}
