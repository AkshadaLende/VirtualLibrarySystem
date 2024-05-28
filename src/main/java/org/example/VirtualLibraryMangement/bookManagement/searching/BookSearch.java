package org.example.VirtualLibraryMangement.bookManagement.searching;

import org.example.VirtualLibraryMangement.bookManagement.Book;

import java.util.List;
import java.util.Map;

public interface BookSearch {

    public List<Book> searchBooks(String title, String auther, int ISBN);
    public List<Book> searchBooks(Map<String,String> books);

}
