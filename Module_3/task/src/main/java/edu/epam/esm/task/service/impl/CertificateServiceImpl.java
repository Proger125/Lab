package edu.epam.esm.task.service.impl;

import edu.epam.esm.task.entity.Certificate;
import edu.epam.esm.task.entity.Tag;
import edu.epam.esm.task.entity.dto.CertificateUpdateDto;
import edu.epam.esm.task.repository.CertificateRepository;
import edu.epam.esm.task.repository.TagRepository;
import edu.epam.esm.task.service.CertificateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository repository;
    private final TagRepository tagRepository;

    public CertificateServiceImpl(CertificateRepository repository, TagRepository tagRepository) {
        this.repository = repository;
        this.tagRepository = tagRepository;
    }

    @Override
    public Page<Certificate> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Certificate> getById(long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Certificate save(Certificate certificate) {
        Set<Tag> tags = certificate.getTags();
        for (var tag : tags){
            Optional<Tag> existedTag = tagRepository.findTagByName(tag.getName());
            if (existedTag.isPresent()){
                Tag newTag = existedTag.get();
                tag.setId(newTag.getId());
            }else{
                tagRepository.save(tag);
            }
        }
        return repository.save(certificate);
    }

    @Override
    public boolean deleteById(long id) {
        boolean result = false;
        if (repository.existsById(id)){
            repository.deleteById(id);
            result = true;
        }

        return result;
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional
    @Override
    public boolean updateCertificate(long id, CertificateUpdateDto dto) {
        boolean result = false;
        if (repository.existsById(id)){
            if (dto.getPrice() == null){
                repository.updateCertificateDuration(dto.getDuration(), id);
                result = true;
            } else if (dto.getDuration() == null){
                repository.updateCertificatePrice(dto.getPrice(), id);
                result = true;
            }
        }

        return result;
    }

    @Transactional
    @Override
    public Page<Certificate> getCertificatesByTags(String[] tags, Pageable pageable) {
        String firstTagName = tags[0];
        Optional<Tag> optionalTag = tagRepository.findTagByName(firstTagName);
        if (optionalTag.isPresent()){
            Tag tag = optionalTag.get();
            Set<Certificate> certificates = tag.getCertificates();
            List<Certificate> certificateList = certificates.stream().toList();
            for (var t : tags){
                certificateList = certificateList.stream().filter(a -> a.getTags()
                        .contains(new Tag(0, t))).collect(Collectors.toList());
            }
            return new PageImpl<>(certificateList, pageable, certificates.size());
        } else {
            return Page.empty();
        }
    }
}
