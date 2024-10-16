package org.example;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import java.util.List;

@Getter
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
    public boolean isSubscribed() {
        return isSubscribed;
    }
}
