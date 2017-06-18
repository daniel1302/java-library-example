package PkProject.Entity;


public class Book {
    private Integer id;
    private String title;
    private String isbn;
    private String authors;
    private String description;
    private Integer publicaitonYear;
    private Publisher publisher;
    private Status status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPublicaitonYear() {
        return publicaitonYear;
    }

    public void setPublicaitonYear(Integer publicaitonYear) {
        this.publicaitonYear = publicaitonYear;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
}
