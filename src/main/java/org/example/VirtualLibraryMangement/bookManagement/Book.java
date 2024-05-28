package org.example.VirtualLibraryMangement.bookManagement;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Book {

    private String title;
    private String author;
    @JsonProperty("ISBN")
    private int ISBN;
    private String genre;
    private String publicationdate;
    private int numberOfCopies;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublicationdate() {
        return publicationdate;
    }

    public void setPublicationdate(String publicationdate) {
        this.publicationdate = publicationdate;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public Book(String title, String author, int ISBN, String genre, String publicationdate, int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.publicationdate = publicationdate;
        this.numberOfCopies = numberOfCopies;
    }

    public Book() {}

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN=" + ISBN +
                ", genre='" + genre + '\'' +
                ", publicationdate='" + publicationdate + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                '}';
    }
}
