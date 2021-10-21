package com.epam.esm.service.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.RepositoryException;
import com.epam.esm.exception.ServiceException;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.util.SearchUtil;
import com.epam.esm.util.SortUtil;
import com.epam.esm.util.impl.GiftCertificateSearchUtil;
import com.epam.esm.util.impl.GiftCertificateSortUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public List<GiftCertificate> getAll(String tag, String search, String sort) throws ServiceException {
        SortUtil<GiftCertificate> sortUtil = new GiftCertificateSortUtil();
        SearchUtil<GiftCertificate> searchUtil = new GiftCertificateSearchUtil();
        try{
            List<GiftCertificate> certificates = repository.getAll();
            if (!Objects.equals(tag, "")){
                certificates = searchUtil.getEntityByTagName(certificates, tag);
            }
            if (!Objects.equals(search, "")){
                certificates = searchUtil.getEntityByNamePart(certificates, search);
            }
            if (!Objects.equals(sort, "")){
                String param = sort.substring(0, sort.indexOf("_"));
                String type = sort.substring(sort.indexOf("_") + 1);
                if (type.equals("asc")){
                    sortUtil.sortASC(certificates, param);
                }
                if (type.equals("desc")){
                    sortUtil.sortDESC(certificates, param);
                }
            }
            return certificates;
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
}
