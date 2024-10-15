package org.example;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;

import java.util.List;

public class Visitor {
    @Getter
    @SerializedName("name")
    private String firstName;
    @Getter
    @SerializedName("surname")
    private String lastName;
    @Getter
    @SerializedName("phone")
    private String phoneNumber;
    @Getter
    private List<Book> favoriteBooks;
    @SerializedName("subscribed")
    private boolean isSubscribed;

    public boolean isSubscribed() {
        return isSubscribed;
    }
}
