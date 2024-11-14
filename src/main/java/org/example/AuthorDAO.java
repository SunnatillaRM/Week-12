package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
public List<Author> getAllAuthors() throws SQLException {
    List<Author> authors = new ArrayList<>();
    try (Connection conn = DatabaseUtil.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM Authors")) {
        while (rs.next()) {
            authors.add(new Author(rs.getInt("AuthorID"), rs.getString("FirstName"), rs.getString("LastName")));
        }
    }
    return authors;
}
    public void insertAuthor(String firstName, String lastName) throws
            SQLException {
        String query = "INSERT INTO Authors (FirstName, LastName) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.executeUpdate();
        }
    }
    public void updateAuthor(int authorID, String firstName, String
            lastName) throws SQLException {
        String query = "UPDATE Authors SET FirstName = ?, LastName = ? WHERE AuthorID = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, authorID);
            pstmt.executeUpdate();
        }
    }
    public void deleteAuthor(int authorID) throws SQLException {
        String query = "DELETE FROM Authors WHERE AuthorID = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, authorID);
            pstmt.executeUpdate();
        }
    }
}