package com.ebay.ws.client;

import com.ebay.common.Constants;
import com.ebay.ws.external.model.Product;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EBayFindAPIClient {
    /**
     *
     * @param universalProductCode
     * @return
     * @throws IOException
     */
    public Product findItemsByProduct(final String universalProductCode) throws IOException {
        //TODO: Add thread safety
        URL url =
                new URL(Constants.EBAY_FIND_API_SBX_URI + universalProductCode + Constants.EBAY_API_SBX_LINK_FILTER);
        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try {
                InputStream xml = connection.getInputStream();
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(xml);
                doc.getDocumentElement().normalize();
                EBayFindAPIResponseUnmarshaller eBayFindAPIResponseUnmarshaller = new EBayFindAPIResponseUnmarshaller();
                NodeList nodeList = doc.getElementsByTagName("item");
                eBayFindAPIResponseUnmarshaller.parseHttpResponse(nodeList, universalProductCode);
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            } catch (SAXException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("GET request not worked");
        }

        return null;
    }
}
