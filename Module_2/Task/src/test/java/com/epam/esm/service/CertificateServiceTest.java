package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.RepositoryException;
import com.epam.esm.exception.ServiceException;
import com.epam.esm.repository.GiftCertificateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
public class CertificateServiceTest {

    @Mock
    @Inject
    private GiftCertificateRepository repository;

    @InjectMocks
    @Inject
    private GiftCertificateService service;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveCertificateTest() throws RepositoryException, ServiceException {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Holiday certificate");

        when(repository.save(any(GiftCertificate.class))).thenReturn(1);

        service.save(certificate);

        when(repository.getById(1)).thenReturn(certificate);

        GiftCertificate newCertificate = service.getById(1);

        assertEquals(certificate, newCertificate);
    }

    @Test
    public void getByIdCertificateTest() throws RepositoryException, ServiceException {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Holiday certificate");

        when(repository.getById(1)).thenReturn(certificate);

        GiftCertificate newCertificate = service.getById(1);

        assertEquals(certificate, newCertificate);
    }

    @Test
    public void getAllCertificatesTest() throws RepositoryException, ServiceException {
        List<GiftCertificate> certificates = new ArrayList<>();
        GiftCertificate certificate1 = new GiftCertificate();
        certificate1.setId(1);
        certificate1.setName("Holiday certificate");
        certificates.add(certificate1);

        GiftCertificate certificate2 = new GiftCertificate();
        certificate2.setId(2);
        certificate2.setName("Birthday certificate");
        certificates.add(certificate2);

        when(repository.getAll()).thenReturn(certificates);

        List<GiftCertificate> newCertificates = service.getAll("", "", "");

        assertEquals(certificates, newCertificates);
    }

    @Test
    public void deleteByIdCertificateTest() throws RepositoryException, ServiceException {
        List<GiftCertificate> certificates = new ArrayList<>();
        GiftCertificate certificate1 = new GiftCertificate();
        certificate1.setId(1);
        certificate1.setName("Holiday certificate");
        certificates.add(certificate1);

        GiftCertificate certificate2 = new GiftCertificate();
        certificate2.setId(2);
        certificate2.setName("Birthday certificate");
        certificates.add(certificate2);

        when(repository.delete(1)).then(invocationOnMock -> {
            certificates.remove(0);
            return 1;
        });

        service.delete(1);

        when(repository.getAll()).thenReturn(certificates);

        List<GiftCertificate> newCertificates = service.getAll("", "", "");

        assertEquals(certificates, newCertificates);
    }

    @Test
    public void deleteAllCertificates() throws RepositoryException, ServiceException {
        List<GiftCertificate> certificates = new ArrayList<>();
        GiftCertificate certificate1 = new GiftCertificate();
        certificate1.setId(1);
        certificate1.setName("Holiday certificate");
        certificates.add(certificate1);

        GiftCertificate certificate2 = new GiftCertificate();
        certificate2.setId(2);
        certificate2.setName("Birthday certificate");
        certificates.add(certificate2);

        when(repository.deleteAll()).then(invocationOnMock -> {
            certificates.clear();
            return 1;
        });

        service.deleteAll();

        when(repository.getAll()).thenReturn(certificates);

        List<GiftCertificate> newCertificates = service.getAll("", "", "");

        assertEquals(certificates, newCertificates);
    }

    @Test
    public void updateCertificateTest() throws RepositoryException, ServiceException {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("Holiday certificate");

        GiftCertificate updateCertificate = new GiftCertificate();
        certificate.setName("Birthday certificate");

        when(repository.update(1, updateCertificate)).then(invocationOnMock -> {
            GiftCertificate newCertificate = new GiftCertificate();
            newCertificate.setId(1);
            newCertificate.setName("Birthday certificate");
            return newCertificate;
        });

        GiftCertificate newCertificate = service.update(1, updateCertificate);

        certificate.setName("Birthday certificate");

        assertEquals(certificate, newCertificate);
    }
}
