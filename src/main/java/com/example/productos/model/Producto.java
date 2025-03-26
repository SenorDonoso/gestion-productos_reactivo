package com.example.productos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos") // Nombre de la colección en MongoDB
@Data // Lombok: genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Lombok: constructor vacío
@AllArgsConstructor // Lombok: constructor con todos los campos
@Builder // Lombok: patrón builder
@Entity
@Data
public class Producto {

    @Id
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @Positive(message = "El precio debe ser positivo")
    private double precio;
}