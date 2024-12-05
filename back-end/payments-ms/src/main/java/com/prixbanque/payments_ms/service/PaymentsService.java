package com.prixbanque.payments_ms.service;

import com.prixbanque.payments_ms.dto.PaymentsDTO;
import com.prixbanque.payments_ms.model.Payments;
import com.prixbanque.payments_ms.model.Status;
import com.prixbanque.payments_ms.repository.PaymentsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentsService {
    @Autowired
    private PaymentsRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PaymentsDTO> getAll(Pageable pagination) {
        return repository
                .findAll(pagination)
                .map(p -> modelMapper.map(p, PaymentsDTO.class));
    }

    public PaymentsDTO getById(Long id) {
        Payments payments = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payments not found"));
        return modelMapper.map(payments, PaymentsDTO.class);
    }

    public PaymentsDTO save(PaymentsDTO paymentsDTO) {
        Payments payments = modelMapper.map(paymentsDTO, Payments.class);
        payments.setStatus(Status.CREATED);
        repository.save(payments);

        return modelMapper.map(payments, PaymentsDTO.class);
    }

    public PaymentsDTO update(Long id, PaymentsDTO paymentsDTO) {
        Payments payments = modelMapper.map(paymentsDTO, Payments.class);
        payments.setId(id);
        payments = repository.save(payments);
        return modelMapper.map(payments, PaymentsDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
