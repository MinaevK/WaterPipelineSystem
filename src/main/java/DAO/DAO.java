package DAO;

import Entity.Pipeline;
import Entity.Point;

import java.sql.*;

public class DAO {
    Connection conn;

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:file:D:\\Course\\WaterPipelineSystem\\Resources\\DB;MV_STORE=false";

    //  Database credentials
    static final String USER = "user";
    static final String PASS = "UserPass";

    public DAO() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void saveInputData(Pipeline pipeline) {
        PreparedStatement stmt = null;

        int startPoint = pipeline.getStartPoint().getId();
        int endPoint = pipeline.getEndPoint().getId();
        int length = pipeline.getLength();
        try {
            stmt = conn.prepareStatement("INSERT INTO WATERPIPELINESYSTEMDB.INPUT (IDX, IDY, LENGTH) "
                    + "VALUES (?,?,?)");
            stmt.setInt(1, startPoint);
            stmt.setInt(2, endPoint);
            stmt.setInt(3, length);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void savePointsData(Point pointA, Point pointB) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO WATERPIPELINESYSTEMDB.POINTS (IDA, IDB) "
                    + "VALUES (?,?,?)");
            stmt.setInt(1, pointA.getId());
            stmt.setInt(2, pointB.getId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
