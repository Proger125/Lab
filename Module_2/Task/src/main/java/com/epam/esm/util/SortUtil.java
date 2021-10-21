package com.epam.esm.util;

import com.epam.esm.entity.Entity;

import java.util.List;

public interface SortUtil<T extends Entity> {
    void sortASC(List<T> list, String param);
    void sortDESC(List<T> list, String param);
}
