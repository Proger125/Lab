package com.epam.esm.rest;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class GiftCertificateController {

    private final GiftCertificateService service;

    public GiftCertificateController(GiftCertificateService service) {
        this.service = service;
    }

    @GetMapping("/certificates")
    public ResponseEntity<String> getAllGiftCertificates(){
        return new ResponseEntity<>("All certificates", HttpStatus.OK);
    }

    @PostMapping("/certificates")
    public ResponseEntity<String> createGiftCertificate(@RequestBody GiftCertificate certificate){
        try{
            service.save(certificate);
            return new ResponseEntity<>("Gift certificate was created successfully.", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
