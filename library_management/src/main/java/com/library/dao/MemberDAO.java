package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.library.db.DBConnection;
import com.library.model.Member;

public class MemberDAO {

    // Add member
    public void addMember(Member member) {

        String sql = "INSERT INTO member (name, email, phone, address) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getPhone());
            ps.setString(4, member.getAddress());

            ps.executeUpdate();
            System.out.println("Member added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View members
    public void viewMembers() {

        String sql = "SELECT * FROM member";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("---- MEMBER LIST ----");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("member_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("email") + " | " +
                    rs.getString("phone") + " | " +
                    rs.getString("address")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
