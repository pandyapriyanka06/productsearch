package com.ebay;

import com.ebay.ws.client.EBayFindAPIClient;
import com.ebay.ws.external.model.Product;

import java.util.Scanner;

/**
 * Executes search program.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(">>>>>> EBay product search <<<<<<<<<<");
        boolean continueSearching = true;
        while (continueSearching){
            System.out.println("Please enter Universal Product Code : ");
            Scanner sc = new Scanner(System.in);
            Long upc = sc.nextLong();
            try {
                new EBayFindAPIClient().findItemsByProduct(upc.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("Do you want to search more items ? ");
            sc = new Scanner(System.in);
            continueSearching = sc.nextBoolean();
        }
    }
}
