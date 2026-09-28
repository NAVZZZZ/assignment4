package com.meridian.billing;

import org.apache.commons.lang3.StringUtils;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        String customerName = "  Naveen  ";

        String cleanedName = StringUtils.trim(customerName);

        System.out.println("Customer name: " + cleanedName);
        System.out.println("Is name empty? " + StringUtils.isEmpty(cleanedName));
    }
}
