package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.db.DBConnection;
import com.library.model.Book;

public class BookDAO {

    // Method to add a new book into database
    public void addBook(Book book) {

        String sql = "INSERT INTO books (title, author, publisher, category_id) VALUES (?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getPublisher());
            ps.setInt(4, book.getCategoryId());

            ps.executeUpdate();
            System.out.println("Book added successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to view all books
    public void viewBooks() {

        String sql = "SELECT * FROM books";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            System.out.println("---- BOOK LIST ----");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("book_id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("author") + " | " +
                        rs.getString("publisher")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
