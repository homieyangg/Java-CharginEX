package ex2;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.opencsv.CSVWriter;

public class CharginDAOlmpl implements ICharginDAO {

	@Override
	public void findByRoad() {
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			String findsql = "SELECT * FROM Demo1 where road = ?";
			System.out.println("\n請輸入要查詢的國道:");
			@SuppressWarnings("resource")
			String scanner = new Scanner(System.in).nextLine();

			PreparedStatement stmt = conn.prepareStatement(findsql);
			stmt.setString(1, scanner);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int number = rs.getInt("number");
				String road = rs.getString("road");
				String rest = rs.getString("rest");
				String place = rs.getString("place");
				int amount = rs.getInt("amount");
				String norm = rs.getString("norm");

				// Display values
				System.out.println("編號:" + number + "  國道:" + road + "  服務區:" + rest + "  位置:" + place + "  數量:"
						+ amount + "  規格:" + norm);

			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addChargin(int a, String b, String c, String d, int e, String f) {
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			String addsql = "insert into Demo1 VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(addsql);

			ps.setInt(1, a);
			ps.setString(2, b);
			ps.setString(3, c);
			ps.setString(4, d);
			ps.setInt(5, e);
			ps.setString(6, f);
			int sum = ps.executeUpdate();
			System.out.println("新增成功！！，已新增筆數：" + sum);
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public boolean updateChargin(int a, String b, String c, String d, int e, String f) {
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			String updatesql = "UPDATE Demo1 SET number=?, road=?, rest=?, place=?, amount=?, norm=? WHERE number=?";
			PreparedStatement pstmt = conn.prepareStatement(updatesql);

			pstmt.setInt(1, a);
			pstmt.setString(2, b);
			pstmt.setString(3, c);
			pstmt.setString(4, d);
			pstmt.setInt(5, e);
			pstmt.setString(6, f);
			pstmt.setInt(7, a);

			int update = pstmt.executeUpdate();
			if (update > 0) {
				System.out.println("修改資料成功:" + update + "筆");
			} else {
				System.out.println("修改資料失敗，請重新設定");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public void Delete(int a) {
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			String deletesql = "DELETE Demo1 WHERE NUMBER=?";
			PreparedStatement pstmt = conn.prepareStatement(deletesql);

			pstmt.setInt(1, a);

			int sum = pstmt.executeUpdate();
			System.out.println("成功刪除了:" + sum + "筆");
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showAll() {
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			String showsql = "SELECT * FROM Demo1";
			PreparedStatement stmt = conn.prepareStatement(showsql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int number = rs.getInt("number");
				String road = rs.getString("road");
				String rest = rs.getString("rest");
				String place = rs.getString("place");
				int amount = rs.getInt("amount");
				String norm = rs.getString("norm");
				// Display
				System.out.println("編號:" + number + "  國道:" + road + "  服務區:" + rest + "  位置:" + place + "  數量:"
						+ amount + "  規格:" + norm);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void exportFile() throws IOException {
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			String showsql = "SELECT * FROM Demo1 ";
			PreparedStatement stmt = conn.prepareStatement(showsql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				// DisplayCSV-openCSV API
				CSVWriter writer = new CSVWriter(new FileWriter("res/Chargin.csv"));
				Boolean includeHeaders = true;

				writer.writeAll(rs, includeHeaders);
				writer.close();
				System.out.println("輸出完成,格式為CSV");
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void IORead() {
		String virus_data_url = "https://quality.data.gov.tw/dq_download_csv.php?nid=151729&md5_url=843449967996fe514109f17d07b3faa0";
		try {
			URL url = new URL(virus_data_url);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			
			
				@SuppressWarnings("deprecation")
				Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
				System.out.println("國道別,服務區,充電樁設置位置,車位數量,充電設備規格");
				for (CSVRecord csvRecord : records) {
					GetSet gRC = new GetSet();
					gRC.setRoad(csvRecord.get("國道別"));
					gRC.setRest(csvRecord.get("服務區"));
					gRC.setPlace(csvRecord.get("服務區"));
					gRC.setAmount(csvRecord.get("車位數量"));
					gRC.setNorm(csvRecord.get("充電設備規格"));
					
					System.out.println(gRC.road+","+gRC.rest+","+gRC.place+","+gRC.amount+","+gRC.norm);
				}
				
				in.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
