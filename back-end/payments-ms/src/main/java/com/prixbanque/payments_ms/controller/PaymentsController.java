package com.prixbanque.payments_ms.controller;

import com.prixbanque.payments_ms.dto.PaymentsDTO;
import com.prixbanque.payments_ms.service.PaymentsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentsController {
    private final PaymentsService paymentsService;

    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping
    public Page<PaymentsDTO> list(@PageableDefault(size = 10) Pageable pagination) {
        return paymentsService.getAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentsDTO> detail(@PathVariable @NotNull Long id) {
        PaymentsDTO paymentsDTO = paymentsService.getById(id);
        return ResponseEntity.ok(paymentsDTO);
    }

    @PostMapping
    public ResponseEntity<PaymentsDTO> create(
            @Valid @RequestBody PaymentsDTO paymentsDTO,
            UriComponentsBuilder uriBuilder) {
        PaymentsDTO payments = paymentsService.save(paymentsDTO);
        URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(payments.getId()).toUri();
        return ResponseEntity.created(uri).body(payments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentsDTO> update(
            @PathVariable @NotNull Long id,
            @RequestBody @Valid PaymentsDTO paymentsDTO) {
        PaymentsDTO payments = paymentsService.update(id, paymentsDTO);
        return ResponseEntity.ok(payments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull Long id) {
        paymentsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
