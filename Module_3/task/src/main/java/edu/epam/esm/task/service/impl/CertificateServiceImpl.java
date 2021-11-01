package edu.epam.esm.task.service.impl;

import edu.epam.esm.task.entity.Certificate;
import edu.epam.esm.task.repository.CertificateRepository;
import edu.epam.esm.task.service.CertificateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository repository;

    public CertificateServiceImpl(CertificateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Certificate> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Certificate> getById(long id) {
        return repository.findById(id);
    }

    @Override
    public Certificate save(Certificate certificate) {
        return repository.save(certificate);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
