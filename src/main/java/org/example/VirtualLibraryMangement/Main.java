package org.example.VirtualLibraryMangement;

import org.example.VirtualLibraryMangement.bookManagement.Book;
import org.example.VirtualLibraryMangement.bookManagement.Inventory;
import org.example.VirtualLibraryMangement.bookManagement.factory.FileFormatParser;
import org.example.VirtualLibraryMangement.bookManagement.factory.FormatFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Book book1= new Book("Mountain Is You", "XYZ",1,"ABC", "20/11/1998",3);
        inventory.addBook(book1);

        String filePath="src/main/resources/Books.json";
        String fileType="json";

        FileFormatParser fileFormatParser= FormatFactory.getFileFormatObject(fileType);
        Map<Integer,Book> Books=  fileFormatParser.parseBooks(filePath);

        int bookAdded=0;
        int duplicateSkipped=0;
        for(Book book : Books.values()){
          if(!inventory.getListOfBooks().containsKey(book.getISBN())){
              inventory.addBook(book);
              bookAdded++;
          }else{
              duplicateSkipped++;
          }
        }
        System.out.println(inventory);


        //filter books :- As a library user, I want to search for books using criteria like title, author,
        // or ISBN so that I can find a specific book or set of books.

        // User input for search criteria
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter search criteria (leave blank if not applicable):");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("ISBN: ");
        int isbnInput = scanner.nextInt();


        //search for book
        List<Book> searchResults= inventory.searchBooks(title,author,isbnInput);

        if(searchResults.isEmpty()){
            System.out.println("Book not found");
        }else{
            System.out.println("Search results:");
            for (Book book : searchResults) {
                System.out.println(book);
            }
        }


        //search book using type and value
        Map<String, String> searchCriteria= getAdvancedSearch(scanner);

        List<Book> filterResults= inventory.searchBooks(searchCriteria);
        System.out.println("Filter Result :" +filterResults);
        //System.out.println(filterResults);


    }


    public static Map<String, String> getAdvancedSearch(Scanner scanner){
        Map<String,String> criteria= new HashMap<>();

        while(true){
            System.out.println("Enter filter type (title, author, ISBN, genre, publicationDate) or 'done' to finish: ");
            String filterType= scanner.nextLine();
            if(filterType.equals("done")){
                break;
            }
            System.out.println("Enter filter Value :");
            String filterValue= scanner.nextLine();
            criteria.put(filterType,filterValue);
        }
        return criteria;
    }


       // System.out.println(inventory);

}
