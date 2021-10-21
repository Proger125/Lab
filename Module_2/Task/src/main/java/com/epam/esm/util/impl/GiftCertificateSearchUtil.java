package com.epam.esm.util.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.util.SearchUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GiftCertificateSearchUtil implements SearchUtil<GiftCertificate> {
    @Override
    public List<GiftCertificate> getEntityByTagName(List<GiftCertificate> list, String tagName) {
        List<GiftCertificate> newList = new ArrayList<>();
        for (var certificate : list){
            for (var tag : certificate.getTags()){
                if (tag.getName().equals(tagName)){
                    newList.add(certificate);
                }
            }
        }
        return newList;
    }

    @Override
    public List<GiftCertificate> getEntityByNamePart(List<GiftCertificate> list, String namePart) {
        List<GiftCertificate> newList = new ArrayList<>();
        Pattern pattern = Pattern.compile(namePart);
        for (var certificate : list){
            Matcher matcher = pattern.matcher(certificate.getName());
            if (matcher.find()){
                newList.add(certificate);
            }
        }
        return newList;
    }
}
