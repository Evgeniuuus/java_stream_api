package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        List<Visitor> data_Visitors = null;
        try (FileReader reader = new FileReader("books.json")) {
            Type visitorListType = new TypeToken<List<Visitor>>() {}.getType();
            data_Visitors = gson.fromJson(reader, visitorListType);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        if (data_Visitors != null) {
            System.out.println("------------------------Задание 1------------------------");
            data_Visitors.stream()
                    .map(visitor -> visitor.getFirstName() + " " + visitor.getLastName())
                    .forEach(System.out::println);
            System.out.println("Всего посетителей: " + data_Visitors.size());


            System.out.println("\n------------------------Задание 2------------------------");
            data_Visitors.stream()
                    .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                    .distinct()
                    .sorted(Comparator.comparing(Book::getTitle))
                    .map(book -> String.format("%s, %s, %d, %s, %s",
                            book.getTitle(), book.getAuthor(), book.getYear(), book.getIsbn(), book.getPublisher()))
                    .forEach(System.out::println);
            long uniqueBooksCount = data_Visitors.stream()
                    .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                    .distinct()
                    .count();
            System.out.println("Всего уникальных книг: " + uniqueBooksCount);


            System.out.println("\n------------------------Задание 3------------------------");
            data_Visitors.stream()
                    .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                    .distinct()
                    .sorted(Comparator.comparing(Book::getYear))
                    .map(book -> String.format("%s, %s, %d, %s, %s",
                            book.getTitle(), book.getAuthor(), book.getYear(), book.getIsbn(), book.getPublisher()))
                    .forEach(System.out::println);


            System.out.println("\n------------------------Задание 4------------------------");
            boolean hasAuthor = data_Visitors.stream()
                    .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                    .anyMatch(book -> "Jane Austen".equalsIgnoreCase(book.getAuthor()));
            if (hasAuthor) {
                System.out.println("Книга этого автора есть у посетителей в избранном");
            } else {
                System.out.println("Книг данного автора нет у посетителей в избранном");
            }


            System.out.println("\n------------------------Задание 5------------------------");
            int maxFavoriteBooks = data_Visitors.stream()
                    .map(visitor -> visitor.getFavoriteBooks().size())
                    .max(Comparator.naturalOrder())
                    .orElse(0);
            System.out.println("Максимальное кол-во книг в избранном: " + maxFavoriteBooks);


            System.out.println("\n------------------------Задание 6------------------------");
            double averageBooks = data_Visitors.stream()
                    .mapToInt(visitor -> visitor.getFavoriteBooks().size())
                    .average().orElse(0);
            data_Visitors.stream()
                    .filter(Visitor::isSubscribed)
                    .map(visitor -> {
                        int favoriteBooksCount = visitor.getFavoriteBooks().size();
                        String sms;
                        if (favoriteBooksCount > averageBooks) {
                            sms = "You are a Bookworm";
                        } else if (favoriteBooksCount < averageBooks) {
                            sms = "Read more";
                        } else {
                            sms = "Fine!";
                        }
                        return visitor.getPhoneNumber() + ", " + sms;
                    })
                    .forEach(System.out::println);
        } else {
            System.out.println("Error file");
        }
    }
}