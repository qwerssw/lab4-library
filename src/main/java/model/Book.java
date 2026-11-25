package model;

public class Book {
    private final int id;
    private final String title;
    private final String author;
    private boolean taken;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.taken = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isTaken() { return taken; }
    public void setTaken(boolean taken) { this.taken = taken; }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', taken=" + taken + "}";
    }
}