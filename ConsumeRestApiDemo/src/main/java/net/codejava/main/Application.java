package net.codejava.main;

import net.codejava.client.ApiCall;
import net.codejava.models.Book;

public class Application {

    public static void main(String[] args) {
        System.out.println(ApiCall.getAllBooks());
        System.out.println(ApiCall.getBookById(1));
        System.out.println(ApiCall.createBook(new Book(400, "Java", true)));
        System.out.println(ApiCall.deleteBookById(1));
        System.out.println(ApiCall.updateBookById(2, new Book(200, "Java", true)));
        System.out.println(ApiCall.getBookById(0));
        System.out.println(ApiCall.deleteBookById(0));
    }
}
