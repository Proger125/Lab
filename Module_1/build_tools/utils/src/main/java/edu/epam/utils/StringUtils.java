package edu.epam.utils;


import java.math.BigDecimal;
import java.math.BigInteger;

import static org.apache.commons.lang3.math.NumberUtils.*;

public class StringUtils {
    public boolean isPositiveNumber(String str){
        if (isCreatable(str)){
            Number number = createNumber(str);
            if (number instanceof Integer){
                return number.intValue() > INTEGER_ZERO;
            } else if (number instanceof Long){
                return number.longValue() > LONG_ZERO;
            } else if (number instanceof Byte){
                return number.byteValue() > BYTE_ZERO;
            } else if (number instanceof Short){
                return number.shortValue() > SHORT_ZERO;
            } else if (number instanceof Float){
                return number.floatValue() > FLOAT_ZERO;
            } else if (number instanceof Double){
                return number.doubleValue() > DOUBLE_ZERO;
            } else if (number instanceof BigInteger){
                return ((BigInteger)number).signum() == INTEGER_ONE;
            } else if (number instanceof BigDecimal){
                return ((BigDecimal)number).compareTo(BigDecimal.ZERO) == INTEGER_ONE;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
