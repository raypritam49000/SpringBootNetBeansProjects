package net.codejava.filereader;

public interface PropertyFileValue {
    public static final String ADD_BOOK = PropertyFileReader.getValue("ADD_BOOK");
    public static final String GET_BOOKS = PropertyFileReader.getValue("GET_BOOKS");
    public static final String GET_BOOK = PropertyFileReader.getValue("GET_BOOK");
    public static final String UPDATE_BOOK = PropertyFileReader.getValue("UPDATE_BOOK");
    public static final String DELETE_BOOK = PropertyFileReader.getValue("DELETE_BOOK");

}
