package org.example.VirtualLibraryMangement.bookManagement;

import org.example.VirtualLibraryMangement.bookManagement.factory.FileFormatParser;
import org.example.VirtualLibraryMangement.bookManagement.searching.BookSearch;

import java.util.*;
import java.util.stream.Collectors;

public class Inventory implements BookSearch {
   // private int ISBN;
    Map<Integer, Book> listOfBooks;
    FileFormatParser fileFormatStratergy;

    public Inventory() {
        listOfBooks= new HashMap<>();
    }


    public Map<Integer, Book> getListOfBooks() {
        return listOfBooks;
    }

    public void setListOfBooks(Map<Integer, Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    public FileFormatParser getFileFormatStratergy() {
        return fileFormatStratergy;
    }

    public void setFileFormatStratergy(FileFormatParser fileFormatStratergy) {
        this.fileFormatStratergy = fileFormatStratergy;
    }

    @Override
    public String toString() {
        return "Inventory{" +

                "listOfBooks=" + listOfBooks +
                '}';
    }


    public void addBook(Book book) {
        if(!listOfBooks.containsKey(book.getISBN())){
            listOfBooks.put(book.getISBN(), book);
        }
    }


    @Override
    public List<Book> searchBooks(String criteria) {
        List<Book> collect = listOfBooks.values().stream().filter(book -> ( book.getTitle().equalsIgnoreCase(criteria)) &&
                (  book.getAuthor().equalsIgnoreCase(criteria)) &&
                ( book.getISBN() == Integer.parseInt(criteria))).collect(Collectors.toList());
        System.out.println(collect);
        return collect;

    }

    @Override
    public List<Book> searchBooks(Map<String, String> searchCriteria) {
        List<Book> collect = listOfBooks.values().stream().filter(book -> (boolean) matchCriteria(book, searchCriteria)).collect(Collectors.toList());
        return collect;
    }

    private Boolean matchCriteria(Book book, Map<String, String> searchCriteria) {

        for(Map.Entry<String ,String> entry:searchCriteria.entrySet()){
            switch (entry.getKey()){
                case "title":
                    if(!book.getTitle().equals(entry.getValue())) return false;
                    break;
                case "author":
                    if(!book.getAuthor().equals(entry.getValue())) return false;
                    break;
                case "ISBN":
                    if (book.getISBN()!=Integer.parseInt(entry.getValue())) return false;
                    break;
                case "genre":
                    if (!book.getGenre().equals(entry.getValue())) return false;
                    break;
                case "publicationDate":
                    if (!book.getPublicationdate().toString().contains(entry.getValue())) return false;
                    break;
                case "basic":
                    if (!(book.getTitle().contains(entry.getValue()) ||
                            book.getAuthor().contains(entry.getValue()) ||
                            book.getISBN()!=Integer.parseInt(entry.getValue()))) return false;
                    break;
                default:
                    return false;
            }

        }
        return true;
    }


}
