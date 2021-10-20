package com.epam.esm.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class GiftCertificateController {
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getInfo(){
        return "Ha-ha";
    }
}