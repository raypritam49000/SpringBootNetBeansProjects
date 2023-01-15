package net.codejava.client;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import net.codejava.filereader.PropertyFileValue;
import net.codejava.models.Book;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

public class ApiCall {

    public static CloseableHttpClient closeableHttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial(new TrustSelfSignedStrategy()).build();
        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();
        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, allowAllHosts);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(connectionFactory).build();
        return httpclient;
    }

    public static Map getBookById(int id) {
        HashMap response = new HashMap();
        try {
            if (id <= 0) {
                response.put("message", "Please enter id is greater than 0");
                response.put("status", "BAD_REQUEST");
                response.put("statusCode", 400);
                response.put("isSuccess", false);
                return response;
            }

            CloseableHttpClient httpclient = closeableHttpClient();
            HttpGet httpGet = new HttpGet(PropertyFileValue.GET_BOOK + id);
            HttpResponse httpResponse = httpclient.execute(httpGet);
            BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
            String output = "";
            String res = "";
            while ((output = br.readLine()) != null) {
                res = res + output;
            }
            Gson gson = new Gson();
            Book book = gson.fromJson(res, Book.class);
            System.out.println(book);

            if (book != null && book.getId() != null && book.getTitle() != null && book.getUserId() != null && book.getCompleted() != null) {
                response.put("message", "Book Details");
                response.put("data", book);
                response.put("status", "OK");
                response.put("statusCode", 200);
                response.put("isSuccess", true);
                return response;
            } else {
                response.put("message", "Data Not Found");
                response.put("status", "NOT_FOUND");
                response.put("statusCode", 404);
                response.put("isSuccess", false);
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", e.getMessage());
            response.put("status", "INTERNAL_SERVER_ERROR");
            response.put("statusCode", 500);
            response.put("isSuccess", false);
            return response;
        }
    }

    public static Map getAllBooks() {
        HashMap response = new HashMap();
        try {
            CloseableHttpClient httpclient = closeableHttpClient();
            HttpGet httpGet = new HttpGet(PropertyFileValue.GET_BOOKS);
            HttpResponse httpResponse = httpclient.execute(httpGet);
            BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
            String output = "";
            String res = "";
            while ((output = br.readLine()) != null) {
                res = res + output;
            }
            Gson gson = new Gson();
            List<Book> books = gson.fromJson(res, List.class);

            if (books != null && books.size() > 0 && !books.isEmpty()) {
                response.put("message", "Book Details");
                response.put("data", books);
                response.put("status", "OK");
                response.put("statusCode", 200);
                response.put("isSuccess", true);
                return response;
            } else {
                response.put("message", "Data Not Found");
                response.put("status", "NOT_FOUND");
                response.put("statusCode", 404);
                response.put("isSuccess", false);
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", e.getMessage());
            response.put("status", "INTERNAL_SERVER_ERROR");
            response.put("statusCode", 500);
            response.put("isSuccess", false);
            return response;
        }
    }

    public static Map createBook(Book bookData) {
        HashMap response = new HashMap();
        Gson gson = new Gson();
        String bookJsonData = gson.toJson(bookData);
        try {
            CloseableHttpClient httpclient = closeableHttpClient();
            HttpPost httpPost = new HttpPost(PropertyFileValue.ADD_BOOK);
            StringEntity entity = new StringEntity(bookJsonData);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpclient.execute(httpPost);
            BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
            String output = "";
            String res = "";
            while ((output = br.readLine()) != null) {
                res = res + output;
            }

            Book book = gson.fromJson(res, Book.class);

            if (book != null && book.getId() != null && book.getTitle() != null && book.getUserId() != null && book.getCompleted() != null) {
                response.put("message", "Book Saved Successfully");
                response.put("data", book);
                response.put("status", "CREATED");
                response.put("statusCode", 201);
                response.put("isSuccess", true);
                return response;
            } else {
                response.put("message", "Data Not Found");
                response.put("status", "NOT_FOUND");
                response.put("statusCode", 404);
                response.put("isSuccess", false);
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", e.getMessage());
            response.put("status", "INTERNAL_SERVER_ERROR");
            response.put("statusCode", 500);
            response.put("isSuccess", false);
            return response;
        }
    }

    public static Map deleteBookById(int id) {
        HashMap response = new HashMap();
        try {
            if (id <= 0) {
                response.put("message", "Please enter id is greater than 0");
                response.put("status", "BAD_REQUEST");
                response.put("statusCode", 400);
                response.put("isSuccess", false);
                return response;
            }

            CloseableHttpClient httpclient = closeableHttpClient();
            HttpDelete httpDelete = new HttpDelete(PropertyFileValue.DELETE_BOOK + id);
            HttpResponse httpResponse = httpclient.execute(httpDelete);

            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                response.put("message", "Error Occur when Book are Deleted");
                response.put("status", "BAD_REQUEST");
                response.put("statusCode", 400);
                response.put("isSuccess", false);
                return response;
            } else {
                response.put("message", "Book Deleted");
                response.put("status", "SUCCESS");
                response.put("statusCode", 200);
                response.put("isSuccess", true);
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", e.getMessage());
            response.put("status", "INTERNAL_SERVER_ERROR");
            response.put("statusCode", 500);
            response.put("isSuccess", false);
            return response;
        }
    }

    public static Map updateBookById(int id, Book bookData) {
        HashMap response = new HashMap();
        Gson gson = new Gson();
        String bookJsonData = gson.toJson(bookData);

        try {
            if (id <= 0) {
                response.put("message", "Please enter id is greater than 0");
                response.put("status", "BAD_REQUEST");
                response.put("statusCode", 400);
                response.put("isSuccess", false);
                return response;
            }

            CloseableHttpClient httpclient = closeableHttpClient();
            HttpPut httpPut = new HttpPut(PropertyFileValue.UPDATE_BOOK + id);
            StringEntity entity = new StringEntity(bookJsonData);
            httpPut.setEntity(entity);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpclient.execute(httpPut);

            BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
            String output = "";
            String res = "";
            while ((output = br.readLine()) != null) {
                res = res + output;
            }
            Book book = gson.fromJson(res, Book.class);

            if (book != null && book.getId() != null && book.getTitle() != null && book.getUserId() != null && book.getCompleted() != null) {
                response.put("message", "Book Details Updated");
                response.put("data", book);
                response.put("status", "UPDATED");
                response.put("statusCode", 203);
                response.put("isSuccess", true);
                return response;
            } else {
                response.put("message", "Error occure While Book Details Updated");
                response.put("status", "BAD_GATWAY");
                response.put("statusCode", 400);
                response.put("isSuccess", false);
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", e.getMessage());
            response.put("status", "INTERNAL_SERVER_ERROR");
            response.put("statusCode", 500);
            response.put("isSuccess", false);
            return response;
        }
    }

}
