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
     //   Book book1= new Book("Mountain Is You", "XYZ",1,"ABC", "20/11/1998",3);
     //   inventory.addBook(book1);


        addBooksIntoInventory(inventory);

        Scanner sc= new Scanner(System.in);
        System.out.println("enter if you want to search book ");
        String search= sc.nextLine();

        if(search.equalsIgnoreCase("search")){
            searchingInLoop(inventory,sc);
        }


        //filter books :- As a library user, I want to search for books using criteria like title, author,
        // or ISBN so that I can find a specific book or set of books.

        // User input for search criteria
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter search criteria (leave blank if not applicable):");
//        System.out.print("Title: ");
//        String title = scanner.nextLine();
//        System.out.print("Author: ");
//        String author = scanner.nextLine();
//        System.out.print("ISBN: ");
//        int isbnInput = scanner.nextInt();
//
//
//        //search for book
//        List<Book> searchResults= inventory.searchBooks(title,author,isbnInput);
//
//        if(searchResults.isEmpty()){
//            System.out.println("Book not found");
//        }else{
//            System.out.println("Search results:");
//            for (Book book : searchResults) {
//                System.out.println(book);
//            }
//        }


        //search book using type and value
    //    Map<String, String> searchCriteria= getAdvancedSearch(scanner);

       // List<Book> filterResults= inventory.searchBooks(searchCriteria);
       // System.out.println("Filter Result :" +filterResults);
        //System.out.println(filterResults);


        //find the book using book number



    }

    private static void addBooksIntoInventory(Inventory inventory) {
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

    }


    public static void searchingInLoop(Inventory inventory, Scanner scanner){
        while(true){
        System.out.println(" Enter search criteria (title ,ISBN ,author or 'Advanced' ,leave empty for no filters");
        String criteria= scanner.nextLine();
        if(criteria.isEmpty()){
            break;
        }

        if(criteria.equalsIgnoreCase("advanced")){
            Map<String, String> searchCriteria= getAdvancedSearch(scanner);
            List<Book> filterResults= inventory.searchBooks(searchCriteria);
            displaySearch(filterResults);
        }else {
            List<Book> searchResults= inventory.searchBooks(criteria);
            displaySearch(searchResults);

            System.out.println("Would you like to apply addition features enter Yes/No");
            String additionalFilter=scanner.nextLine();
            if(additionalFilter.equalsIgnoreCase("yes")){
                Map<String, String> searchCriteria= getAdvancedSearch(scanner);
                searchCriteria.put("basic",criteria);
                List<Book> filterResults= inventory.searchBooks(searchCriteria);
                displaySearch(searchResults);
            }
        }

        System.out.println("would you like to continue search (yes/no) ?");
        String continueSearch= scanner.nextLine();

        if(!continueSearch.equalsIgnoreCase("yes")){
            break;
        }
        scanner.close();

        }
    }

    private static void displaySearch(List<Book> filterResults) {

        if(filterResults.isEmpty()){
            System.out.println("Book not found");
        }
        Scanner scanner= new Scanner(System.in);
        for (int i = 0; i <filterResults.size() ; i++) {
            Book book= filterResults.get(i);
            System.out.println((i+1)+ ": " + book.getTitle() + " by " +book.getAuthor()+" (ISBN :" +book.getISBN()+")");

        }

        System.out.println("Enter the book number for more details");
        String selectionBook= scanner.nextLine();
        if(!selectionBook.isEmpty()){
            try{
             int bookNumber=Integer.parseInt(selectionBook);
             if(bookNumber>0 && bookNumber<=filterResults.size()){
                 Book select= filterResults.get(bookNumber-1);
                 displayBookSearch(select);
             }
            }catch(NumberFormatException e){
             System.out.println();
            }
        }

    }

    public static void displayBookSearch(Book book) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("ISBN: " + book.getISBN());
        System.out.println("Genre: " + book.getGenre());
        System.out.println("Publication Date: " + book.getPublicationdate());
        System.out.println("Available Copies: " + (book.getNumberOfCopies() > 0 ? book.getNumberOfCopies() : "Out of Stock"));
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
