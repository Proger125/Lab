package edu.epam.esm.task.repository;

import edu.epam.esm.task.entity.Certificate;
import edu.epam.esm.task.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

@Repository
public interface CertificateRepository extends CrudRepository<Certificate, Long>,
        PagingAndSortingRepository<Certificate, Long> {

    @Modifying
    @Query("UPDATE Certificate c SET c.duration = :duration WHERE c.id = :id")
    @Transactional
    void updateCertificateDuration(@Param(value = "duration") int duration,
                                   @Param(value = "id") long id);

    @Modifying
    @Query("UPDATE Certificate c SET c.price = :price WHERE c.id = :id")
    @Transactional
    void updateCertificatePrice(@Param(value = "price") BigDecimal price,
                                @Param(value = "id") long id);

}
