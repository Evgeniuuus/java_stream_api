package org.example;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Visitor {
    @SerializedName("name")
    private String firstName;
    @SerializedName("surname")
    private String lastName;
    @SerializedName("phone")
    private String phoneNumber;
    private List<Book> favoriteBooks;
    @SerializedName("subscribed")
    private boolean isSubscribed;
}