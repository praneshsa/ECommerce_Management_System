package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {


    private static final String url = "jdbc:mysql://localhost:3306/e_commerce_db";
    private static final String user = "root";
    private static final String pwd = "Pranesh@29";

    public static Connection connectDB() {

        Connection con = null;

        try {

            //Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pwd);


        }catch(Exception e) {

            e.printStackTrace();

        }

        return con;
    }

}
