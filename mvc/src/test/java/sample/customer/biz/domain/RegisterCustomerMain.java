package sample.customer.biz.domain;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class RegisterCustomerMain {

    private static String CUSTOMER_REGISTER_URL
            = "http://localhost:8080/mvc/api/customer";

    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();

        PostMethod method = new PostMethod(CUSTOMER_REGISTER_URL);
        method.setRequestEntity(
            new StringRequestEntity(xml(), "text/xml", "UTF-8"));
        client.executeMethod(method);

        System.out.println(method.getStatusCode());
        System.out.println(method.getResponseBodyAsString());
    }

    private static String xml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<customer>")
          .append("  <name>追加郎</name>")
          .append("  <emailAddress>tsuika@aa.bb.cc</emailAddress>")
          .append("  <birthday>1985-03-21T00:00:00+09:00</birthday>")
          .append("  <favoriteNumber>7</favoriteNumber>")
          .append("</customer>");

        return sb.toString();
    }
}
