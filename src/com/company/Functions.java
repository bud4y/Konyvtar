package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Functions {
    private Connection sql;
    private PreparedStatement ps;

    public Functions(String URL) {
        try {
            sql = DriverManager.getConnection(URL, "root", "root");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addUser(String name, String email, String address) {
        try {
            ps = sql.prepareStatement("INSERT INTO user (name, email, address) VALUES(?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, address);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }

    }

    public void removeUser(int userID) {
        try {
            ps = sql.prepareStatement("DELETE FROM user where userID=?");
            ps.setInt(1, userID);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }

    }


    public void addBook(String title, String author) {
        try {
            ps = sql.prepareStatement("INSERT INTO books (title, author) VALUES(?, ?)");
            ps.setString(1, title);
            ps.setString(2, author);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }
    }

    public void removeBookID(int bookID) {
        try {
            ps = sql.prepareStatement("DELETE FROM books where bookID=?");
            ps.setInt(1, bookID);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }


    }

    public void removeBookCopy(int copyID) {
        try {
            ps = sql.prepareStatement("DELETE FROM storage where copyID=?");
            ps.setInt(1, copyID);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }

    }

    public void borrowBook(int User_userID, int Borrows_copyID, String dateOfBorrow) {
        try {
            //TODO check for available copy
            ps = sql.prepareStatement("INSERT INTO borrows (User_userID, Borrows_copyID, dateOfBorrow) VALUES(?,?,?)");
            ps.setInt(1, User_userID);
            ps.setInt(2, Borrows_copyID);
            ps.setString(3, dateOfBorrow);


            ps.executeUpdate();

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }

    }

    public void returnBook(int borrowID, String date) {
        try {
            ps = sql.prepareStatement("UPDATE borrows SET dateOfReturn WHERE borrowID = ? VALUE ?");
            ps.setInt(1, borrowID);
            ps.setString(2, date);
            ps.executeQuery();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }
    }

    public List<Book> searchBookByTitle(String userText) {
        try {
            ps = sql.prepareStatement("SELECT * from books where title LIKE ?");
            ps.setString(1, ("%" + userText + "%"));
            ResultSet result = ps.executeQuery();
            return bookListFromResultSet(result);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }
    }

    public List<Book> searchBookByAuthor(String userText) {
        try {
            ps = sql.prepareStatement("SELECT * from books where author LIKE ?");
            ps.setString(1, ("%" + userText + "%"));
            ResultSet result = ps.executeQuery();
            return bookListFromResultSet(result);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }
    }

    private List<Book> bookListFromResultSet(ResultSet result) throws SQLException {
        List<Book> search = new ArrayList<>();
        while (result.next()) {
            Book book = new Book(result.getInt("bookID"),
                    result.getString("title"),
                    result.getString("author"));
            search.add(book);
        }
        return search;
    }

    private List<Book> bookListFromResultSetForBorrows(ResultSet result) throws SQLException {
        List<Book> search = new ArrayList<>();
        while (result.next()) {
            Book book = new Book(result.getInt("bookID"),
                    result.getString("title"),
                    result.getString("author"),
                    result.getInt("borrowID"));
            //book.setBookID();
            search.add(book);
        }
        return search;
    }

    private List<User> userListFromResultSet(ResultSet result) throws SQLException {
        List<User> search = new ArrayList<>();
        while (result.next()) {
            User user = new User(result.getInt("userID"),
                    result.getString("name"), result.getString("email"), result.getString("address"));
            search.add(user);
        }
        return search;
    }


    public Map<User, List<Book>> getUsersWithBorrowedBooks() {
        Map<User, List<Book>> map = new HashMap<>();
        try {
            PreparedStatement ps2 = sql.prepareStatement("SELECT DISTINCT userID, name, email, address FROM library.user\n" +
                    "JOIN borrows ON User_userID = userID\n" +
                    "WHERE dateOfReturn IS NULL;");
            ResultSet result = ps2.executeQuery();

            while (result.next()) {
                User u = new User(result.getInt("userID"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("address"));
                map.put(u, getBorrowedBooksOf(u.getUserID()));
            }
            return map;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }
    }

    public List<Book> getBorrowedBooksOf(int UserID) {
        try {
            ps = sql.prepareStatement("SELECT  B.bookID, B.title, B.author, borrowID FROM books B \n" +
                    "                    JOIN storage S ON B.bookID = S.bookID\n" +
                    "                    JOIN borrows bor ON S.copyID = bor.Borrows_copyID\n" +
                    "                    WHERE bor.User_userID = ? AND bor.dateOfReturn IS NULL ");
            ps.setInt(1, UserID);
            ResultSet res = ps.executeQuery();
            return bookListFromResultSetForBorrows(res);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }
    }


    public List<Book> bookList() {
        try {
            ps = sql.prepareStatement("SELECT * from books");

            ResultSet result = ps.executeQuery();
            return bookListFromResultSet(result);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }

    }

    public List<User> userList() {
        try {
            ps = sql.prepareStatement("SELECT * from user");

            ResultSet result = ps.executeQuery();
            return userListFromResultSet(result);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getMessage());
        }

    }


}
