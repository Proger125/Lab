package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.RepositoryException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Test
    public void shouldSaveGiftCertificate() throws RepositoryException {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(5, "holidays"));
        tags.add(new Tag(6, "winter"));

        GiftCertificate certificate = new GiftCertificate(1, "birthday", "Happy birthday",
                new BigDecimal("100"), 10, new Date(), new Date(), tags);

        int result = repository.save(certificate);

        assertEquals(4, result);
    }

    @Test
    public void shouldGetGiftCertificateById() throws RepositoryException, ParseException {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Holiday certificate");
        certificate.setDescription("The best holidays in your life!");
        certificate.setPrice(new BigDecimal(1000));
        certificate.setDuration(14);

        certificate.setCreateDate(format.parse("2005-08-09T18:31:42"));
        certificate.setLastUpdateDate(format.parse("2005-08-09T18:31:42"));

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));

        certificate.setTags(tags);

        GiftCertificate result = repository.getById(1);

        assertEquals(certificate, result);
    }

    @Test
    public void shouldGetAllCertificates() throws RepositoryException, ParseException {
        List<GiftCertificate> certificates = new ArrayList<>();

        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Holiday certificate");
        certificate.setDescription("The best holidays in your life!");
        certificate.setPrice(new BigDecimal(1000));
        certificate.setDuration(14);

        certificate.setCreateDate(format.parse("2005-08-09T18:31:42"));
        certificate.setLastUpdateDate(format.parse("2005-08-09T18:31:42"));

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));

        certificate.setTags(tags);

        certificates.add(certificate);

        List<GiftCertificate> result = repository.getAll();
        assertEquals(certificates, result);
    }

    @Test
    public void shouldDeleteCertificateById() throws RepositoryException {
        int result = repository.delete(1);
        assertEquals(2, result);
    }

    @Test
    public void shouldDeleteAllCertificates() throws RepositoryException {
        int result = repository.deleteAll();
        assertEquals(2, result);
    }

    @Test
    public void shouldUpdateCertificateById() throws RepositoryException, ParseException {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Holiday certificate");
        certificate.setDescription("The best holidays in your life!");
        certificate.setPrice(new BigDecimal(1000));
        certificate.setDuration(14);

        certificate.setCreateDate(format.parse("2005-08-09T18:31:42"));
        certificate.setLastUpdateDate(format.parse("2005-08-09T18:31:42"));

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));
        tags.add(new Tag(2, "winter"));

        certificate.setTags(tags);

        GiftCertificate updatedCertificate = repository.update(1, certificate);

        assertEquals(certificate, updatedCertificate);
    }

    @Test
    public void shouldGetCertificatesByTagName() throws RepositoryException, ParseException {
        List<GiftCertificate> certificates = new ArrayList<>();

        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Holiday certificate");
        certificate.setDescription("The best holidays in your life!");
        certificate.setPrice(new BigDecimal(1000));
        certificate.setDuration(14);

        certificate.setCreateDate(format.parse("2005-08-09T18:31:42"));
        certificate.setLastUpdateDate(format.parse("2005-08-09T18:31:42"));

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));

        certificate.setTags(tags);

        certificates.add(certificate);

        List<GiftCertificate> result = repository.getByTagName("summer");

        assertEquals(certificates, result);
    }

    @Test
    public void shouldGetCertificatesByNamePart() throws RepositoryException, ParseException {
        List<GiftCertificate> certificates = new ArrayList<>();

        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Holiday certificate");
        certificate.setDescription("The best holidays in your life!");
        certificate.setPrice(new BigDecimal(1000));
        certificate.setDuration(14);

        certificate.setCreateDate(format.parse("2005-08-09T18:31:42"));
        certificate.setLastUpdateDate(format.parse("2005-08-09T18:31:42"));

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));

        certificate.setTags(tags);

        certificates.add(certificate);

        List<GiftCertificate> result = repository.getByPartOfName("Holiday");

        assertEquals(certificates, result);
    }

    @Test
    public void shouldGetCertificatesByDescPart() throws RepositoryException, ParseException {
        List<GiftCertificate> certificates = new ArrayList<>();

        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Holiday certificate");
        certificate.setDescription("The best holidays in your life!");
        certificate.setPrice(new BigDecimal(1000));
        certificate.setDuration(14);

        certificate.setCreateDate(format.parse("2005-08-09T18:31:42"));
        certificate.setLastUpdateDate(format.parse("2005-08-09T18:31:42"));

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1, "summer"));

        certificate.setTags(tags);

        certificates.add(certificate);

        List<GiftCertificate> result = repository.getByPartOfDescription("best holidays");

        assertEquals(certificates, result);
    }
}
