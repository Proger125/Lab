package com.epam.esm.util.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.util.SortUtil;

import java.util.Comparator;
import java.util.List;

public class GiftCertificateSortUtil implements SortUtil<GiftCertificate> {

    @Override
    public void sortASC(List<GiftCertificate> list, String param) {
        switch (param){
            case "name":{
                list.sort(Comparator.comparing(GiftCertificate::getName));
                break;
            }
            case "date":{
                list.sort(Comparator.comparing(GiftCertificate::getCreateDate));
            }
        }
    }

    @Override
    public void sortDESC(List<GiftCertificate> list, String param) {
        switch (param){
            case "name":{
                list.sort(Comparator.comparing(GiftCertificate::getName).reversed());
                break;
            }
            case "date":{
                list.sort(Comparator.comparing(GiftCertificate::getCreateDate).reversed());
            }
        }
    }
}
