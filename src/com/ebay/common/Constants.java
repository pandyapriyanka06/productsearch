package com.ebay.common;

public class Constants {
    public final static String APPLICATION_NAME = "Priyanka-testFind-SBX-f2e5592aa-ef0321b1";
    public final static String EBAY_FIND_API_SBX_URI = "https://svcs.sandbox.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsByProduct&X-EBAY-SOA-SECURITY-APPNAME="+
                 APPLICATION_NAME  +
            "&RESPONSE-DATA-FORMAT=XML&REST-PAYLOAD&productId.@type=ReferenceID&productId=";
    public static final String EBAY_API_SBX_LINK_FILTER =
            "&sortOrder=PricePlusShippingLowest&paginationInput.entriesPerPage=100&GLOBAL-ID=EBAY-US";
}
