package ex2;

import java.io.IOException;
import java.util.Scanner;

public class CharginAction {

	public static void main(String[] args) throws InterruptedException, IOException {

		System.out.println("歡迎您使用充電樁查詢修改系統!!!");
		Thread.sleep(1000);
		System.out.println("請輸入:1.查詢國道充電站,2.新增國道充電站,3.修改資料,4.刪除資料,5.輸出檔案,6.全部資料,7.檢視資料來源,8.離開");
		// 查詢系統
		Scanner scanner = new Scanner(System.in);
		CharginDAOlmpl dao = new CharginDAOlmpl();
		while (scanner.hasNext()) {
			String input = scanner.next();
			// 查詢系統
			if (input.equals("1")) {
				System.out.println("歡迎進入查詢系統!!!");
				Thread.sleep(1000);
				dao.findByRoad();
				System.out.println("繼續使用請輸入1~7，離開系統請輸入8");
			}
			// 新增系統
			else if (input.equals("2")) {
				System.out.println("歡迎進入新增系統!!!");
				Thread.sleep(1000);

				System.out.println("\n請輸入這是第幾筆資料:");
				int inumber = scanner.nextInt();
				System.out.println("\n請輸入國道幾號:");
				String iroad = scanner.next();
				System.out.println("\n請輸入位於哪個服務區:");
				String irest = scanner.next();
				System.out.println("\n請輸入充電樁位子:");
				String iplace = scanner.next();
				System.out.println("\n請輸入充電樁數量:");
				int iamount = scanner.nextInt();
				System.out.println("\n請輸入充電樁規格:");
				String inorm = scanner.next();
				dao.addChargin(inumber, iroad, irest, iplace, iamount, inorm);
				System.out.println("繼續使用請輸入1~7，離開系統請輸入8");
			}
			// 修改系統
			else if (input.equals("3")) {
				System.out.println("歡迎進入修改系統!!!");
				Thread.sleep(1000);

				System.out.println("\n請輸入修改第幾筆資料:");
				int unmber = scanner.nextInt();
				System.out.println("\n請輸入要修改的國道:");
				String uroad = scanner.next();
				System.out.println("\n請輸入要修改的服務區:");
				String urest = scanner.next();
				System.out.println("\n請輸入要修改的充電樁位子:");
				String uplace = scanner.next();
				System.out.println("\n請輸入要修改的充電樁數量:");
				int uamount = scanner.nextInt();
				System.out.println("\n請輸入要修改的規格:");
				String unorm = scanner.next();
				dao.updateChargin(unmber, uroad, urest, uplace, uamount, unorm);
				System.out.println("繼續使用請輸入1~7，離開系統請輸入8");
			} else if (input.equals("4")) {
				System.out.println("歡迎進入刪除系統！！");
				Thread.sleep(1000);

				System.out.println("請問要刪除第幾筆資料？");
				int dnumber = scanner.nextInt();
				dao.Delete(dnumber);
				System.out.println("繼續使用請輸入1~7，離開系統請輸入8");
			}
			// 查詢全部資料
			else if (input.equals("5")) {
				dao.exportFile();
				System.out.println("繼續使用請輸入1~7，離開系統請輸入8");

			} else if (input.equals("6")) {
				System.out.println("現在為您查詢全部資料！！！");
				Thread.sleep(1000);
				dao.showAll();
				System.out.println("繼續使用請輸入1~7，離開系統請輸入8");
			} else if (input.equals("7")) {
				System.out.println("檢視開放平台資烙來源");
				Thread.sleep(1000);
				dao.IORead();
				System.out.println("繼續使用請輸入1~7，離開系統請輸入8");
			} else if (input.equals("8")) {
				System.out.println("以離開系統");
				break;
			} else {
				System.out.println("輸入錯誤，請重新輸入");
			}
		}

		scanner.close();
	}

}
