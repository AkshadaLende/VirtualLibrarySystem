package org.example.VirtualLibraryMangement.bookManagement.factory;

import org.example.VirtualLibraryMangement.bookManagement.Book;
import org.example.VirtualLibraryMangement.bookManagement.Inventory;

import java.io.File;
import java.util.Map;

public interface FileFormatParser {

    public Map<Integer, Book> parseBooks(String file);
}
