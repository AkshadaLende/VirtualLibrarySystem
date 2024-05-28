package org.example.VirtualLibraryMangement.bookManagement.factory;

import org.example.VirtualLibraryMangement.bookManagement.Book;
import org.example.VirtualLibraryMangement.bookManagement.Inventory;

import java.io.File;
import java.util.Map;

public class XmlFileFormat implements  FileFormatParser{
    @Override
    public Map<Integer, Book> parseBooks(String file) {

        //xml parsing logic
        return null;
    }
}
