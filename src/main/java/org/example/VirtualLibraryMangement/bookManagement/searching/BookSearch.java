package org.example.VirtualLibraryMangement.bookManagement.searching;

import org.example.VirtualLibraryMangement.bookManagement.Book;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public interface BookSearch {

    public List<Book> searchBooks(String criteria);
    public List<Book> searchBooks(Map<String,String> books);

}
