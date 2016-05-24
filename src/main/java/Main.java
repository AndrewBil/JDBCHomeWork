import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 24.05.2016.
 */
public class Main {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/sample";
    static final String USER = "andrew";
    static final String PASS = "4414";


    private static void executeSQl(List<String> list) {

        ResultsHTML resultsHTML = new ResultsHTML();
        for (String sql : list) {
            Connection conn = null;
            Statement stmt = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                resultsHTML.write(rs, "sql" + list.indexOf(sql) + ".html");
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


    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        String sql0;
        String sql1;
        String sql2;
        sql0 = "SELECT project_no, COUNT(job) FROM works_on GROUP BY project_no";
        sql1 = "SELECT emp_no FROM works_on GROUP BY project_no HAVING project_no='p1' OR project_no='p2';";
        sql2 = "SELECT project_name, budget FROM project WHERE budget  BETWEEN 95000 AND 120000;";
        list.add(sql0);
        list.add(sql1);
        list.add(sql2);
        executeSQl(list);
    }
}