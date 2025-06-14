 
package model.service;

import model.entity.Position;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionService extends BaseService {
    private static final String TABLE_NAME = "Positions";

    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        try (ResultSet rs = executeQuery(sql)) {
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
        try (ResultSet rs = executeQuery(sql, positionId)) {
            if (rs.next()) {
                return new Position(
                    rs.getInt("position_id"),
                    rs.getString("position_name"),
                    rs.getInt("department_id"),
                    rs.getTimestamp("created_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }
}