package org.example.VirtualLibraryMangement.bookManagement.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.VirtualLibraryMangement.bookManagement.Book;
import org.example.VirtualLibraryMangement.bookManagement.Inventory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class JsonFileFormat implements FileFormatParser{
    @Override
    public Map<Integer, Book> parseBooks(String filePath) {
        Map<Integer, Book> books = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        // Assuming the JSON structure is an array of books
        Book[] bookArray;
        try {
            bookArray = objectMapper.readValue(new File(filePath), Book[].class);
            for (Book book : bookArray) {
                books.put(book.getISBN(), book);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return books;
    }
}
