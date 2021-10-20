package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CertificateRepositoryTest {

    @Inject
    private GiftCertificateRepository repository;

    @Test
    public void shouldSaveGiftCertificate(){
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(5, "holidays"));
        tags.add(new Tag(6, "winter"));

        GiftCertificate certificate = new GiftCertificate(1, "birthday", "Happy birthday",
                new BigDecimal("100"), 10, new Date(), new Date(), tags);

        int result = repository.save(certificate);

        assertEquals(4, result);
    }

    @Test
    public void shouldGetGiftCertificateById() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Holiday certificate");
        certificate.setDescription("The best holidays in your life!");
        certificate.setPrice(new BigDecimal(1000));
        certificate.setDuration(14);

        TemporalAccessor accessor = DateTimeFormatter.ISO_INSTANT.parse("2005-08-09T18:31:42.201Z");
        Instant instant = Instant.from(accessor);
        certificate.setCreateDate(Date.from(instant));
        certificate.setLastUpdateDate(Date.from(instant));

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));

        certificate.setTags(tags);

        GiftCertificate result = repository.getById(1);

        assertEquals(certificate, result);
    }

    @Test
    public void shouldGetAllCertificates(){
        List<GiftCertificate> certificates = new ArrayList<>();

        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Holiday certificate");
        certificate.setDescription("The best holidays in your life!");
        certificate.setPrice(new BigDecimal(1000));
        certificate.setDuration(14);

        TemporalAccessor accessor = DateTimeFormatter.ISO_INSTANT.parse("2005-08-09T18:31:42.201Z");
        Instant instant = Instant.from(accessor);
        certificate.setCreateDate(Date.from(instant));
        certificate.setLastUpdateDate(Date.from(instant));

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));

        certificate.setTags(tags);

        certificates.add(certificate);

        List<GiftCertificate> result = repository.getAll();
        assertEquals(certificates, result);
    }
}
