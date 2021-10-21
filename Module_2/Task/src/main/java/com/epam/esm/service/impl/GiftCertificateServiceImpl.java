package com.epam.esm.service.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.RepositoryException;
import com.epam.esm.exception.ServiceException;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    @Autowired
    private GiftCertificateRepository repository;

    @Override
    public int save(GiftCertificate tag) throws ServiceException {
        try{
            return repository.save(tag);
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute save() request in GiftCertificateService", e);
        }
    }

    @Override
    public GiftCertificate getById(long id) throws ServiceException {
        try{
            return repository.getById(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute getById() request in GiftCertificateService", e);
        }
    }

    @Override
    public List<GiftCertificate> getAll() throws ServiceException {
        try{
            return repository.getAll();
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute getAll() request in GiftCertificateService", e);
        }
    }

    @Override
    public int delete(long id) throws ServiceException {
        try {
            return repository.delete(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute delete() request in GiftCertificateService", e);
        }
    }

    @Override
    public int deleteAll() throws ServiceException {
        try {
            return repository.deleteAll();
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute deleteAll() request in GiftCertificateService", e);
        }
    }

    @Override
    public GiftCertificate update(long id, GiftCertificate certificate) throws ServiceException {
        try {
            return repository.update(id, certificate);
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute update() request in GiftCertificateService");
        }
    }

    @Override
    public List<GiftCertificate> getByTagName(String tagName) throws ServiceException {
        try{
            return repository.getByTagName(tagName);
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute getByTagName() request in GiftCertificateService", e);
        }
    }

    @Override
    public List<GiftCertificate> getByPartOfName(String namePart) throws ServiceException {
        try{
            return repository.getByPartOfName(namePart);
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute getByPartOfName() request in GiftCertificateService", e);
        }
    }

    @Override
    public List<GiftCertificate> getByPartOfDesc(String descPart) throws ServiceException {
        try {
            return repository.getByPartOfDescription(descPart);
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute getByPartOfDesc() request in GiftCertificateService", e);
        }
    }

    @Override
    public List<GiftCertificate> getAllCertificatesSortedByParamASC(String param) throws ServiceException {
        try {
            List<GiftCertificate> certificates = repository.getAll();
            switch (param){
                case "name":{
                    certificates.sort(Comparator.comparing(GiftCertificate::getName));
                    break;
                }
                case "date":{
                    certificates.sort(Comparator.comparing(GiftCertificate::getCreateDate));
                    break;
                }
            }

            return certificates;
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute getAllCertificateSortedByParamASC() request in GiftCertificateService", e);
        }
    }

    @Override
    public List<GiftCertificate> getAllCertificatesSortedByParamDESC(String param) throws ServiceException {
        try {
            List<GiftCertificate> certificates = repository.getAll();
            switch (param){
                case "name":{
                    certificates.sort(Comparator.comparing(GiftCertificate::getName).reversed());
                    break;
                }
                case "date":{
                    certificates.sort(Comparator.comparing(GiftCertificate::getCreateDate).reversed());
                    break;
                }
            }

            return certificates;
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to execute getAllCertificateSortedByParamASC() request in GiftCertificateService", e);
        }
    }

}
