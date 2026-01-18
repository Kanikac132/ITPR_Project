package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.db.DBConnection;

public class IssueReturnDAO {

    // ================= ISSUE BOOK =================
    public void issueBook(int bookId, int memberId) {

        String sql = "INSERT INTO issue_return (book_id, member_id, issue_date, status) "
                   + "VALUES (?, ?, CURDATE(), 'ISSUED')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ps.setInt(2, memberId);

            ps.executeUpdate();
            System.out.println("Book issued successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= RETURN BOOK =================
    public void returnBook(int transactionId) {

        String sql = "UPDATE issue_return "
                   + "SET return_date = CURDATE(), status = 'RETURNED' "
                   + "WHERE transaction_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, transactionId);
            ps.executeUpdate();

            System.out.println("Book returned successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= VIEW ISSUED BOOKS =================
    public void viewIssuedBooks() {

        String sql = "SELECT * FROM issue_return";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("---- ISSUE RECORDS ----");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("transaction_id") + " | " +
                        rs.getInt("book_id") + " | " +
                        rs.getInt("member_id") + " | " +
                        rs.getDate("issue_date") + " | " +
                        rs.getDate("return_date") + " | " +
                        rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
