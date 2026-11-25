package model;

public class Reader {
    private final int id;
    private final String name;

    public Reader(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Reader{id=" + id + ", name='" + name + "'}";
    }
}