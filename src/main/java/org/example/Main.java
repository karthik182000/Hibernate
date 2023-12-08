package org.example;

import org.example.cfg.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();


            Author author = new Author();
            author.setName("John Doe");


            Book book1 = new Book();
            book1.setTitle("Hibernate Basics");
            book1.setAuthor(author);

            Book book2 = new Book();
            book2.setTitle("Advanced Hibernate");
            book2.setAuthor(author);


            author.setBooks(Arrays.asList(book1, book2));

            session.persist(author);

            transaction.commit();

            Author retrievedAuthor = session.get(Author.class, author.getId());
            System.out.println("Retrieved Author: " + retrievedAuthor.getName());
            for (Book book : retrievedAuthor.getBooks()) {
                System.out.println("Book Title: " + book.getTitle());
            }

            APIService apiService = new APIService();
            apiService.callAPI(retrievedAuthor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}