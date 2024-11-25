package com.banking_system.service_account_management.utils;

import org.springframework.stereotype.Component;

@Component
public class Util {
    public boolean isInRange(int value, int start, int end) {
        return value >= start && value <= end;
    }

    public boolean isAnOrangeNumber(String number) {        
        int debut = Integer.parseInt(number.substring(1, 3));
        return isInRange(debut, 55, 59) || isInRange(debut, 85, 99);
    }

    public boolean isAnMtnNumber(String number) {        
        int debut = Integer.parseInt(number.substring(1, 3));
        return isInRange(debut, 50, 54) || isInRange(debut, 70, 84);
    }
}
