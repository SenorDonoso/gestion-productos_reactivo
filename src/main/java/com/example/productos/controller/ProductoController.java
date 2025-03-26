package com.example.productos.controller;

import com.example.productos.model.Producto;
import com.example.productos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;
    private final MessageSource messageSource;

    @GetMapping
    public Flux<Producto> listarproductos() {
        return service.listarproductos();
    }

    @PostMapping
    public Mono<ResponseEntity<Producto>> crear(@Valid @RequestBody Producto producto) {
        return service.guardar(producto)
                .map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Producto>> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Producto>> actualizar(@PathVariable String id, @Valid @RequestBody Producto producto) {
        return service.actualizar(id, producto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id) {
        return service.eliminar(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @GetMapping("/mensaje")
    public String mensaje(@RequestParam(name = "lang", defaultValue = "es") String lang) {
        Locale locale = Locale.forLanguageTag(lang);
        return messageSource.getMessage("mensaje.bienvenida", null, locale);
    }
}