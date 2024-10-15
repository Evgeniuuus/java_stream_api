package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try(FileReader reader = new FileReader("C:\\JavaProjects\\Java_Stream_API22\\src\\main\\java\\org\\example\\books.json")){
            Type visitorListType = new TypeToken<List<Visitor>>() {}.getType();
            List<Visitor> visitors = gson.fromJson(reader, visitorListType);
            // -------------------------------------------------------------------------------------------------------
            int k = 0;
            List<String> people = new ArrayList<>();
            String name;
            for (Visitor visitor : visitors) {
                name = visitor.getFirstName() + " " + visitor.getLastName();
                people.add(name);
                k++;
            }
            System.out.println(people);
            System.out.println("Total visitors:" + k + "\n------------------------------------------");

            // -------------------------------------------------------------------------------------------------------
            k = 0;
            List<String> knigi = new ArrayList<>();
            for (Visitor visitor : visitors) {
                for (Book book : visitor.getFavoriteBooks()) {
                    if (!knigi.contains(book.getTitle())) {
                        knigi.add(book.getTitle());
                        k++;
                    }
                }
            }
            System.out.println(knigi);
            System.out.println("Total books : " + k + "\n------------------------------------------");

            //-------------------------------------------------------------------------------------------------------
            List<Integer> years = new ArrayList<>();
            List<String> knigi_sort = new ArrayList<>();
            for (Visitor visitor : visitors) {
                for (Book book : visitor.getFavoriteBooks()) {
                    if (!knigi_sort.contains(book.getTitle())) {
                        knigi_sort.add(book.getTitle());
                        years.add(book.getYear());
                    }
                }
            }

            for (int i = 0; i < years.size() - 1; i++)
                for (int j = i + 1; j < years.size(); j++)
                    if (years.get(i) > years.get(j)){
                        int t = years.get(j);
                        years.set(j, years.get(i));
                        years.set(i, t);

                        String t1 = knigi_sort.get(j);
                        knigi_sort.set(j, knigi_sort.get(i));
                        knigi_sort.set(i, t1);
                    }

            System.out.println("Sorted list of books by year : " + knigi_sort + "\n------------------------------------------");

            // -------------------------------------------------------------------------------------------------------
            boolean f = false;
            for (Visitor visitor : visitors) {
                for (Book book : visitor.getFavoriteBooks()) {
                    if (Objects.equals(book.getAuthor(), "Jane Austen")) {
                        f = true;
                        break;
                    }
                }
            }
            System.out.print("Does anyone have the author Jane Austen in their favorites? : ");
            if (f) System.out.println("Yes");
            else System.out.println("No");
            System.out.println("------------------------------------------");

            // -------------------------------------------------------------------------------------------------------
            List<Integer> maximum = new ArrayList<>();
            int max;
            for (Visitor visitor : visitors) {
                max = 0;
                for (Book book : visitor.getFavoriteBooks()) {
                    max++;
                }
                maximum.add(max);
            }

            max = -1;
            for (Integer i : maximum)
                if (i > max) max = i;
            System.out.println("Maximum number of books in favorites : " + max + "\n------------------------------------------");

            // -------------------------------------------------------------------------------------------------------\
            double average = 0;
            k = 0;
            for(Integer i : maximum){
                k++;
                average += i;
            }
            average = average/k;
            int average_int = (int) Math.ceil(average);

            List<String> nomera = new ArrayList<>();
            List<Integer> maximum2 = new ArrayList<>();
            for (Visitor visitor : visitors) {
                k = 0;
                if(visitor.isSubscribed()){
                    nomera.add(visitor.getPhoneNumber());
                    for (Book book : visitor.getFavoriteBooks()) {
                        k++;
                    }
                    maximum2.add(k);
                }
            }

            sms_messages sms_messages = new sms_messages();

            for (int i = 0; i < nomera.size(); i++) {
                if (maximum2.get(i) > average_int) {
                    sms_messages.sendSMS(nomera.get(i), "you are a bookworm");
                } else if (maximum2.get(i) < average_int) {
                    sms_messages.sendSMS(nomera.get(i), "read more");
                } else {
                    sms_messages.sendSMS(nomera.get(i), "fine");
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
