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
public class ResultsHTML {

    public int write(ResultSet rs, String fileName) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        int rowCount = 0;

        printWriter.println("<P ALIGN='left'><TABLE BORDER=1>");
        ResultSetMetaData rsmd = null;
        try {
            rsmd = rs.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int columnCount = 0;
        try {
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        printWriter.println("<TR>");

        for (int i = 0; i < columnCount; i++) {
            try {
                printWriter.println("<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        printWriter.println("</TR>");

        try {
            while (rs.next()) {
                rowCount++;
                printWriter.println("<TR>");

                for (int i = 0; i < columnCount; i++) {
                    printWriter.println("<TD>" + rs.getString(i + 1) + "</TD>");


                }
                printWriter.println("</TR>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printWriter.println("</TABLE></P>");
        printWriter.close();
        return rowCount;
    }


}

