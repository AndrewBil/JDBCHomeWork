import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by andrew on 24.05.2016.
 */
public class ResultsToHTML {

    public int write(ResultSet rs, String sql, String fileName) throws IOException {
        FileWriter fileWriter = null;
        WebDriver driver = new FirefoxDriver();
        try {
            fileWriter = new FileWriter(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        int rowCount = 0;
        printWriter.println("<!DOCTYPE html>");
        printWriter.println("<html>");
        printWriter.println("<head>");
        printWriter.println("<style>");
        printWriter.println("table, th, td");
        printWriter.println("{border: 1px solid black;");
        printWriter.println("border-collapse: collapse;}");
        printWriter.println("th, td {padding: 15px;}");
        printWriter.println("th {text-align: left;}");
        printWriter.println("</style>");
        printWriter.println("</head>");
        printWriter.println("<body>");
        printWriter.println("<p>"+sql+"</p>");
        printWriter.println();
        printWriter.println("<table style='width:300px'>");
        ResultSetMetaData rsmd = null;
        try
        {
            rsmd = rs.getMetaData();
        } catch (
                SQLException e
                )
        {
            e.printStackTrace();
        }

        int columnCount = 0;
        try
        {
            columnCount = rsmd.getColumnCount();
        } catch (
                SQLException e
                )
        {
            e.printStackTrace();
        }
        printWriter.println("<tr>");
        for (
                int i = 0;
                i < columnCount; i++)
        {
            try {
                printWriter.println("<th>" + rsmd.getColumnLabel(i + 1) + "</th>");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        printWriter.println("</tr>");
        try
        {
            while (rs.next()) {
                rowCount++;
                printWriter.println("<tr>");
                for (int i = 0; i < columnCount; i++) {
                    printWriter.println("<td>" + rs.getString(i + 1) + "</td>");
                }
                printWriter.println("</tr>");
            }
        } catch (
                SQLException e
                )
        {
            e.printStackTrace();
        }
        printWriter.println("</table>");
        printWriter.println("</body>");
        printWriter.println("</html>");
        printWriter.close();
        driver.get("file:///"+fileName);
        //driver.quit();
        return rowCount;
    }
}

