package sample.customer.biz.domain;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class FindCustomerByIdMain {
    private static String CUSTOMER_FIND_URL
            = "http://localhost:8080/mvc/api/customer/3";

    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod(CUSTOMER_FIND_URL);
        client.executeMethod(method);

        System.out.println(method.getStatusCode());
        System.out.println(method.getResponseBodyAsString());
    }
}
