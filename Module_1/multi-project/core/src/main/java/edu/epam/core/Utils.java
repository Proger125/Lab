package edu.epam.core;

import edu.epam.utils.StringUtils;

public class Utils {
    public boolean isAllPositiveNumbers(String ... strings){
        boolean flag = true;
        StringUtils utils = new StringUtils();
        for (var str : strings){
            flag &= utils.isPositiveNumber(str);
        }
        return flag;
    }
}
