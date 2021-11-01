package edu.epam.esm.task.service;

import edu.epam.esm.task.entity.Certificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CertificateService {
    Page<Certificate> getAll(Pageable pageable);
    Optional<Certificate> getById(long id);
    Certificate save(Certificate certificate);
    void deleteById(long id);
    void deleteAll();
}
