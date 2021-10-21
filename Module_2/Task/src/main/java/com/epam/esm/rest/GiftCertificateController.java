package com.epam.esm.rest;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.ServiceException;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GiftCertificateController {

    @Autowired
    private GiftCertificateService service;

    @GetMapping(value = "/certificates/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GiftCertificate> getGiftCertificateById(@PathVariable("id") long id){
        try{
            GiftCertificate certificate = service.getById(id);
            if (certificate == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(certificate, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/certificates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GiftCertificate>> getAllCertificates(
            @RequestParam(value = "tag", defaultValue = "", required = false) String tag,
            @RequestParam(value = "search", defaultValue = "", required = false) String search,
            @RequestParam(value = "sort", defaultValue = "", required = false) String sort
    ){
        try{
            List<GiftCertificate> certificates = service.getAll(tag, search, sort);
            if (certificates.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(certificates, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/certificates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createGiftCertificate(@RequestBody GiftCertificate certificate){
        try {
            service.save(certificate);
            return new ResponseEntity<>("Certificate was successfully saved", HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/certificates/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteGiftCertificateById(@PathVariable("id") long id){
        try {
            service.delete(id);
            return new ResponseEntity<>("Gift certificate was deleted", HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/certificates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAllGiftCertificates(){
        try {
            service.deleteAll();
            return new ResponseEntity<>("All gift certificates were deleted", HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/certificates/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GiftCertificate> updateGiftCertificateById(@PathVariable("id") long id,
                                                                     @RequestBody GiftCertificate certificate){
        try{
            GiftCertificate newCertificate = service.update(id, certificate);
            if (newCertificate == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(newCertificate, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}