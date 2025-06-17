package model.service;

import model.entity.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionService extends BaseService {
    private static final String TABLE_NAME = "Positions";

    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                Position position = new Position(
                    rs.getInt("position_id"),
                    rs.getString("position_name"),
                    rs.getInt("department_id"),
                    rs.getTimestamp("created_at")
                );
                positions.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }
        return positions;
    }

    public Position getPositionById(int positionId) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE position_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, positionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Position(
                        rs.getInt("position_id"),
                        rs.getString("position_name"),
                        rs.getInt("department_id"),
                        rs.getTimestamp("created_at")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    // ✅ HÀM MỚI: Lấy danh sách vị trí theo department_id
    public List<Position> getPositionsByDepartmentId(int departmentId) {
        List<Position> positions = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE department_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Position position = new Position(
                        rs.getInt("position_id"),
                        rs.getString("position_name"),
                        rs.getInt("department_id"),
                        rs.getTimestamp("created_at")
                    );
                    positions.add(position);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }
        return positions;
    }
}
