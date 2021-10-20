package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
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
                new BigDecimal("100"), 10, new DateTime(), new DateTime(), tags);

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

        DateTimeFormatter parser = ISODateTimeFormat.dateTime();
        DateTime createDate = parser.parseDateTime("2005-08-09T18:31:42.201Z");

        certificate.setCreateDate(createDate);
        certificate.setLastUpdateDate(createDate);

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

        DateTimeFormatter parser = ISODateTimeFormat.dateTime();
        DateTime createDate = parser.parseDateTime("2005-08-09T18:31:42.201Z");

        certificate.setCreateDate(createDate);
        certificate.setLastUpdateDate(createDate);

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));

        certificate.setTags(tags);

        certificates.add(certificate);

        List<GiftCertificate> result = repository.getAll();
        assertEquals(certificates, result);
    }

    @Test
    public void shouldDeleteCertificateById(){
        int result = repository.delete(1);
        assertEquals(2, result);
    }

    @Test
    public void shouldDeleteAllCertificates(){
        int result = repository.deleteAll();
        assertEquals(2, result);
    }

    @Test
    public void shouldUpdateCertificateById(){
        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Holiday certificate");
        certificate.setDescription("The best holidays in your life!");
        certificate.setPrice(new BigDecimal(1000));
        certificate.setDuration(14);

        DateTimeFormatter parser = ISODateTimeFormat.dateTime();
        DateTime createDate = parser.parseDateTime("2005-08-09T18:31:42.201Z");

        certificate.setCreateDate(createDate);
        certificate.setLastUpdateDate(createDate);

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));
        tags.add(new Tag(2, "winter"));

        certificate.setTags(tags);

        GiftCertificate updatedCertificate = repository.update(1, certificate);

        assertEquals(certificate, updatedCertificate);
    }

    @Test
    public void shouldGetCertificatesByTagName(){
        List<GiftCertificate> certificates = new ArrayList<>();

        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Holiday certificate");
        certificate.setDescription("The best holidays in your life!");
        certificate.setPrice(new BigDecimal(1000));
        certificate.setDuration(14);

        DateTimeFormatter parser = ISODateTimeFormat.dateTime();
        DateTime createDate = parser.parseDateTime("2005-08-09T18:31:42.201Z");

        certificate.setCreateDate(createDate);
        certificate.setLastUpdateDate(createDate);

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));

        certificate.setTags(tags);

        certificates.add(certificate);

        List<GiftCertificate> result = repository.getByTagName("summer");

        assertEquals(certificates, result);
    }
}
