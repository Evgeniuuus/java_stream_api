package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("C:\\JavaProjects\\Java_Stream_API22\\src\\main\\java\\org\\example\\books.json")) {
            Type visitorListType = new TypeToken<List<Visitor>>() {
            }.getType();
            List<Visitor> visitors = gson.fromJson(reader, visitorListType);

            // ---------------------------------------------------------------------------------------------------------
            List<String> people = visitors.stream()
                    .map(visitor -> visitor.getFirstName() + " " + visitor.getLastName())
                    .collect(Collectors.toList());
            System.out.println(people);
            System.out.println("Total visitors: " + people.size() + "\n------------------------------------------");

            // ---------------------------------------------------------------------------------------------------------
            List<String> books = visitors.stream()
                    .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                    .map(Book::getTitle)
                    .distinct()
                    .collect(Collectors.toList());
            System.out.println(books);
            System.out.println("Total books: " + books.size() + "\n------------------------------------------");

            // ---------------------------------------------------------------------------------------------------------
            List<String> sortedBooks = visitors.stream()
                    .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                    .sorted(Comparator.comparingInt(Book::getYear))
                    .map(Book::getTitle)
                    .distinct()
                    .toList();
            System.out.println("Sorted list of books by year: " + sortedBooks + "\n------------------------------------------");

            // ---------------------------------------------------------------------------------------------------------
            boolean hasJaneAustenBooks = visitors.stream()
                    .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                    .anyMatch(book -> book.getAuthor().equals("Jane Austen"));
            System.out.println("Does anyone have the author Jane Austen in their favorites? : " + hasJaneAustenBooks);
            System.out.println("------------------------------------------");

            // ---------------------------------------------------------------------------------------------------------
            List<Integer> maximum = Collections.singletonList(visitors.stream()
                    .mapToInt(visitor -> visitor.getFavoriteBooks().size())
                    .max()
                    .orElse(0));
            System.out.println("Maximum number of books in favorites: " + maximum + "\n------------------------------------------");

            // ---------------------------------------------------------------------------------------------------------
            double average = visitors.stream()
                    .mapToInt(visitor -> visitor.getFavoriteBooks().size())
                    .average()
                    .orElse(0.0);
            int averageInt = (int) Math.ceil(average);

            List<String> phoneNumbers = visitors.stream()
                    .filter(Visitor::isSubscribed)
                    .map(Visitor::getPhoneNumber)
                    .toList();

            List<Integer> bookCounts = visitors.stream()
                    .filter(Visitor::isSubscribed)
                    .mapToInt(visitor -> visitor.getFavoriteBooks().size())
                    .boxed()
                    .toList();

            sms_messages sms_messages = new sms_messages();
            for (int i = 0; i < phoneNumbers.size(); i++) {
                if (bookCounts.get(i) > averageInt) {
                    sms_messages.sendSMS(phoneNumbers.get(i), "you are a bookworm");
                } else if (bookCounts.get(i) < averageInt) {
                    sms_messages.sendSMS(phoneNumbers.get(i), "read more");
                } else {
                    sms_messages.sendSMS(phoneNumbers.get(i), "fine");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
