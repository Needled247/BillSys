package com.bill.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by HP on 14-3-7.
 */
public class MuitipleDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return null;
    }
}
