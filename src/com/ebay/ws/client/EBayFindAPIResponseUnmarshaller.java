package com.ebay.ws.client;

import com.ebay.ws.external.model.Product;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Reads and parses ebay response.
 */
public class EBayFindAPIResponseUnmarshaller {

    /**
     * Parses ebay response and prints product details.
     * @param nodeList
     * @param upcNumber
     * @throws IOException
     */
    public void parseHttpResponse(NodeList nodeList, String upcNumber )
            throws IOException {
        List<Product> products = new ArrayList<>( );
        Product product = null;

        if ( nodeList != null ) {
            double currentPrice = 0;
            double shippingCharges = 0;
            double totalCharges = 0;
            String productName = "";

            for ( int number = 0; number < nodeList.getLength( ); number++ ) {
                Node node = nodeList.item( number );

                if ( node.getNodeType( ) == Node.ELEMENT_NODE ) {
                    Element eElement = ( Element )node;

                    if (eElement.getElementsByTagName( "title" ).item( 0 )
                                    .getTextContent( ) != null ) {
                        productName =
                                eElement.getElementsByTagName( "title" ).item( 0 )
                                        .getTextContent( );
                    }

                    if (eElement.getElementsByTagName( "currentPrice" ).item(
                                    0 ).getTextContent( ) != null ) {
                        currentPrice =
                                Float.parseFloat( eElement.getElementsByTagName(
                                        "currentPrice" ).item( 0 )
                                        .getTextContent( ) );
                    }

                    if (eElement.getElementsByTagName( "shippingServiceCost" )
                                    .item( 0 ) != null ) {
                        shippingCharges =
                                Float.parseFloat( eElement.getElementsByTagName(
                                        "shippingServiceCost" ).item( 0 )
                                        .getTextContent( ) );
                    }
                    totalCharges = currentPrice + shippingCharges;
                }

                product = new Product(productName,totalCharges );
                products.add( product );
            }
        }
        this.printProduct( products, nodeList.getLength( ), upcNumber );
    }

    private void printProduct( List<Product> productList, int itemsCount, String upcNumber ) throws IOException {
        if ( !productList.isEmpty( ) ) {
            Iterator<Product> itr = productList.iterator( );

            System.out.println( "Number of items : " + itemsCount );
            System.out.println(
                    "**Items are sorted in ascending order on 'Total Charges'" );
            System.out.println(
                    "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------" );
            System.out.printf( "%5s %50s %45s %23s %35s", "No of records",
                    "Product Name", "Current Price", "Shipping charges",
                    "Total Charges(Current + Shipping)" );
            System.out.println( );
            System.out.println(
                    "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------" );

            String format =
                    "| %1$-8d | %2$-80s  | %3$-20.2f | %4$-10.2f \t| %5$-20.2f |\n";
            int countItems = 0;

            while ( itr.hasNext( ) )
            {
                Product productDTO = ( Product )itr.next( );
                countItems++;
                System.out.format( format, countItems,
                        productDTO.getTitle(),
                        productDTO.getPrice() );
            }

            System.out.println(
                    "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------" );
        }
        else {
            throw new IOException(
                    "Error 404: Did not find any product for given UPC number: " + upcNumber );
        }
    }
}
