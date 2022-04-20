package ex2;

import java.io.IOException;

public interface ICharginDAO {

	public static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Charging";
	public static final String USER = "sa";
	public static final String PASSWORD = "passw0rd";

//用國道查詢	
	public void findByRoad();

//新增資料
	public void addChargin(int a, String b, String c, String d, int e, String f);

//修改資料
	public boolean updateChargin(int a, String b, String c, String d, int e, String f);

//刪除資料
	public void Delete(int a);

//顯示全部資料
	public void showAll();

//輸出CSV
	public void exportFile() throws IOException;
//讀取資料來源
	public void IORead();
}
