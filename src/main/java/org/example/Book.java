package org.example;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;

@Getter
public class Book {
    @SerializedName("name")
    private String title;
    private String author;
    @SerializedName("publishingYear")
    private int year;
    private String isbn;
    private String publisher;
}
