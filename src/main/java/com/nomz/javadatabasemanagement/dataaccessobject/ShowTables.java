import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ShowTables{
    public static void showTables(Connection connection, String dbName) throws SQLException {

            DatabaseMetaData metaData = connection.getMetaData();

            ResultSet resultSet = metaData.getTables(dbName, null, "%", null);
             int i = 1;
            System.out.println(dbName + " tables:");
            while (resultSet.next()) {
                String tableName = resultSet.getString(3);
                System.out.println("Table "+i+" : " + tableName);
                i++;
            }
    }
}
