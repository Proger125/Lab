package edu.epam.esm.task.generating;

import edu.epam.esm.task.entity.Certificate;
import edu.epam.esm.task.repository.CertificateRepository;
import edu.epam.esm.task.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@SpringBootTest
public class CertificateGenerating {

    @Autowired
    private CertificateRepository repository;

    @Autowired
    private TagRepository tagRepository;

    private final Random random = new Random();

    @Test
    public void generateTenThousandsCertificatesTest(){
        for (int i = 0; i < 10000; i++){
            Certificate certificate = new Certificate();
            certificate.setName("Certificate_" + i);
            certificate.setDescription("Certificate_" + i + " description");
            certificate.setDuration(random.nextInt(60));
            certificate.setPrice(new BigDecimal(random.nextInt(1000)));
            certificate.setCreateDate(new Date());
            certificate.setLastUpdateDate(new Date());

            for (int j = 0; j < 10; j++){
                int randomTagId = random.nextInt(1000) + 48;
                certificate.getTags().add(tagRepository.findById((long) randomTagId).get());
            }
            repository.save(certificate);
        }
    }
}
