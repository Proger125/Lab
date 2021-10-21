package com.epam.esm.util;

import com.epam.esm.entity.Entity;

import java.util.List;

public interface SearchUtil <T extends Entity>{
    List<T> getEntityByTagName(List<T> list, String tag);
    List<T> getEntityByNamePart(List<T> list, String namePart);
}
