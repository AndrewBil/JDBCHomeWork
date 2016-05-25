import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 24.05.2016.
 */

public class Main {



    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        String sql0,sql1,sql2;

        sql0 = "SELECT project_no, COUNT(job) FROM works_on GROUP BY project_no";
        sql1 = "SELECT emp_no FROM works_on GROUP BY project_no HAVING project_no='p1' OR project_no='p2';";
        sql2 = "SELECT project_name, budget FROM project WHERE budget  BETWEEN 95000 AND 120000;";

        list.add(sql0);
        list.add(sql1);
        list.add(sql2);
        ExecuteSQL.execute(list);
    }
}