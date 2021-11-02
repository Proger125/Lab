package edu.epam.esm.task.service;

import edu.epam.esm.task.entity.Certificate;
import edu.epam.esm.task.entity.dto.CertificateUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CertificateService {
    Page<Certificate> getAll(Pageable pageable);
    Optional<Certificate> getById(long id);
    Certificate save(Certificate certificate);
    boolean deleteById(long id);
    void deleteAll();
    boolean updateCertificate(long id, CertificateUpdateDto dto);
    Page<Certificate> getCertificatesByTags(String[] tags, Pageable pageable);
}
