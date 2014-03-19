package com.bill.dao;

/**
 * Created by HP on 14-3-7.
 */
public abstract class BillContextHolder {
    public final static String DATA_SOURCE_ORACLE = "BMU";
    public final static String DATA_SOURCE_MYSQL = "RADIUS";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }
}
