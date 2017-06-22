package PkProject.Controller;

import PkProject.DAO.BookDAO;
import PkProject.Entity.Book;
import PkProject.Model.Validator.EmptyValidator;
import PkProject.Model.Validator.ValidatorChain;
import java.util.ArrayList;
import java.util.HashMap;


public class BookManageController extends AbstractController {

    protected Boolean needToBeLoggedIn = true;
    HashMap<String, Object> params = new HashMap<>();
    @Override
    public void doGET() {
        HashMap<String, Object> params = new HashMap<>();
        
        String bookId = this.request.getParameter("book-id");
        Book book = null;
        if (bookId != null && bookId.matches("\\d+")) {
            book = BookDAO.getById(Integer.parseInt(bookId));
        } 
        if (book == null) {
            book = new Book();
        }
        
        System.out.println(book.getTitle());
        System.out.println(book.getIsbn());
        System.out.println(book.getAuthors());
        System.out.println(book.getDescription());
        System.out.println(book.getPublicaitonYear());
        
        params.put("book", book);
        
        this.render("Book:form", params);
    }

    @Override
    public void doPOST() {
        String title = this.request.getParameter("title");
        String isbn = this.request.getParameter("isbn");
        String authors = this.request.getParameter("authors");
        String desc = this.request.getParameter("desc");
        String pub_year = this.request.getParameter("pub_year");
        HashMap<String, Object> params = new HashMap<>();
        
        
        String bookId = this.request.getParameter("id");
        Book book = null;
        if (bookId != null && bookId.matches("\\d+")) {
            book = BookDAO.getById(Integer.parseInt(bookId));
        } 
        if (book == null) {
            book = new Book();
        }
        
        if (title != null && isbn != null && authors != null && desc != null && pub_year != null) {
            ValidatorChain validator = new ValidatorChain();
            validator.addValidator(new EmptyValidator("title", title, "Tytuł"));
            validator.addValidator(new EmptyValidator("isbn", isbn,  "ISBN"));
            validator.addValidator(new EmptyValidator("authors", authors, "Autorzy"));
            validator.addValidator(new EmptyValidator("desc", desc, "Opis"));
            validator.addValidator(new EmptyValidator("pub_year", pub_year, "Rok publikacji"));
            
            validator.validate();
            if (!validator.isVlid()) {
                params.put("registerErrors", validator.getErrors());
            } else {
                ArrayList<String> errors = new ArrayList<>();
                
                if (!pub_year.matches("\\d+")) {
                    errors.add("Rok produkcji jest niepoprawny");
                    params.put("registerErrors", errors);
                } else {
                    Integer publicationYear = Integer.parseInt(pub_year);
                    
                    params.put("message", "Dane zostały zapisane");
                
                    HashMap<String, String> bookData = new HashMap<>();                
                    bookData.put("title", title);
                    bookData.put("isbn", isbn);
                    bookData.put("authors", authors);
                    bookData.put("description", desc);
                    bookData.put("publication_year", pub_year);
                    bookData.put("publisher_id", "1");
                    
                    if (book.getId() > 0) {
                        BookDAO.update(book.getId(), bookData);
                    } else {
                        BookDAO.insert(bookData);
                    }
                }
            }
        }
        
        book = BookDAO.getById(Integer.parseInt(bookId));
        params.put("book", book);
        this.render("Book:form", params);        
    }
    
    @Override
    protected Boolean onlyForLoggedIn() {
        return true;
    }
    
    
    @Override
    protected Boolean onlyForAdmin() {
        return true;
    }
}
