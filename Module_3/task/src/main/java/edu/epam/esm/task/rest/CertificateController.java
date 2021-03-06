package edu.epam.esm.task.rest;

import edu.epam.esm.task.entity.Certificate;
import edu.epam.esm.task.entity.Tag;
import edu.epam.esm.task.entity.dto.CertificateUpdateDto;
import edu.epam.esm.task.exception.ServiceException;
import edu.epam.esm.task.service.CertificateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CertificateController {

    private final CertificateService service;


    public CertificateController(CertificateService service) {
        this.service = service;
    }

    @GetMapping("/certificates")
    public CollectionModel<Certificate> getAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "5", required = false) int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        try {
            Page<Certificate> certificates = service.getAll(pageable);

            Link link = linkTo(methodOn(CertificateController.class).getAll(page, size)).withSelfRel();

            createLinksToCertificates(certificates);
            return CollectionModel.of(certificates, link);
        } catch (ServiceException e){
            return CollectionModel.empty();
        }
    }

    @GetMapping("/certificates/{id}")
    public ResponseEntity<Certificate> getById(@PathVariable long id){
        try {
            Optional<Certificate> optionalCertificate = service.getById(id);

            if (optionalCertificate.isPresent()) {
                Certificate certificate = optionalCertificate.get();
                deleteTagCertificateConnection(certificate);
                Link selfLink = linkTo(methodOn(CertificateController.class).getById(id)).withSelfRel();
                certificate.add(selfLink);
                createTagsLinks(certificate);

                return new ResponseEntity<>(certificate, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ServiceException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/certificates")
    public ResponseEntity<Certificate> save(@RequestBody Certificate certificate){
        Date now = new Date();

        certificate.setCreateDate(now);
        certificate.setLastUpdateDate(now);

        try {
            certificate = service.save(certificate);
            Link selfLink = linkTo(methodOn(CertificateController.class).save(certificate)).withSelfRel();
            certificate.add(selfLink);
            return new ResponseEntity<>(certificate, HttpStatus.OK);
        } catch (ServiceException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/certificates")
    public ResponseEntity<String> deleteAll(){
        try {
            service.deleteAll();

            return new ResponseEntity<>("All certificates were deleted", HttpStatus.OK);
        } catch (ServiceException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/certificates/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        try {
            boolean result = service.deleteById(id);

            if (result) {
                return new ResponseEntity<>("Certificate was deleted", HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ServiceException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/certificates/{id}")
    public ResponseEntity<String> updateCertificate(@PathVariable long id,
                                                 @RequestBody CertificateUpdateDto dto){
        dto.setUpdateDate(new Date());
        try {
            boolean result = service.updateCertificate(id, dto);

            if (result) {
                return new ResponseEntity<>("Certificate was updated", HttpStatus.OK);
            }
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        } catch (ServiceException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certificates/find-by-tag")
    public CollectionModel<Certificate> getByTags(@RequestParam(value = "tag") String[] tags,
                                                   @RequestParam(value = "page", defaultValue = "0",
                                                           required = false) int page,
                                                   @RequestParam(value = "size", defaultValue = "5",
                                                           required = false) int size){
        Pageable pageable = PageRequest.of(page, size);
        try {
            Page<Certificate> certificates = service.getCertificatesByTags(tags, pageable);

            Link link = linkTo(methodOn(CertificateController.class).getByTags(tags, page, size)).withSelfRel();

            createLinksToCertificates(certificates);
            return CollectionModel.of(certificates, link);
        } catch (ServiceException e){
            return CollectionModel.empty();
        }
    }

    private void createLinksToCertificates(Page<Certificate> certificates){
        for (var certificate : certificates){
            deleteTagCertificateConnection(certificate);
            Link selfLink = linkTo(methodOn(CertificateController.class).getById(certificate.getId()))
                    .withSelfRel();
            certificate.add(selfLink);
            createTagsLinks(certificate);
        }
    }

    private void deleteTagCertificateConnection(Certificate certificate){
        Set<Tag> tags = certificate.getTags();

        for (var tag : tags){
            tag.setCertificates(null);
        }
    }

    private void createTagsLinks(Certificate certificate){
        Set<Tag> tags = certificate.getTags();

        for (var tag : tags){
            if (!tag.hasLinks()){
                Link selfLink = linkTo(methodOn(TagController.class).getById(tag.getId())).withSelfRel();
                tag.add(selfLink);
            }
        }
    }
}
