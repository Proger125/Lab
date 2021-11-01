package edu.epam.esm.task.repository;

import edu.epam.esm.task.entity.Certificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends CrudRepository<Certificate, Long>,
        PagingAndSortingRepository<Certificate, Long> {

}
