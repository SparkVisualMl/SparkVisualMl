import java.sql.*;
public class OracleConn {





        String dbUrl = "jdbc:oracle:thin:@192.168.30.242:1522:orcl2";
        String theUser = "ZLEMR";
        String thePw = "ZLEMR";
        Connection c = null;
        Statement conn;
        ResultSet rs = null;

        public OracleConn() {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
                c = DriverManager.getConnection(dbUrl, theUser, thePw);
                conn = c.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public boolean executeUpdate(String sql) {
            try {
                conn.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        public ResultSet executeQuery(String sql) {
            rs = null;
            try {
                rs = conn.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rs;
        }

        public void close() {
            try {
                conn.close();
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            ResultSet rs;
            OracleConn conn = new OracleConn();
            rs = conn.executeQuery("SELECT * FROM ZLEMR.BZ_DOC_LOG");
            System.out.printf(rs.toString());
            try {
                while (rs.next()) {
                    Object b = rs.getObject("CONTENT");
                    System.out.println( b.toString() );

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
