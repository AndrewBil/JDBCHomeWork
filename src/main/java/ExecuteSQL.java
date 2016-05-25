import java.sql.*;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by andrew on 25.05.2016.
 */
public class ExecuteSQL {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sample";
    static final String USER = "andrew";
    static final String PASS = "4414";


    public static void execute(List<String> list) {
        ResultsToHTML resultsToHTML = new ResultsToHTML();
        for (String sql : list) {
            Connection conn = null;
            Statement stmt = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                resultsToHTML.write(rs, sql,System.getProperty("user.dir")+"//results//"+"sqlresult" +new StringJoiner(".").add(new Integer(list.indexOf(sql)).toString()).add("html"));
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException se2) {
                }
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }
}
