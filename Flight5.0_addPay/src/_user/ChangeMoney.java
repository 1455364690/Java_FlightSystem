package _user;
//实现用户的钱包修改功能，实现用户的充值、退款信息，修改数据库信息
//作者：孙加辉，时间：2017/05/07
import java.sql.DriverManager;
import _manager.ManagerInfo;
public class ChangeMoney extends ManagerInfo{
	
	//存钱
	public static boolean addMoney(int money,String strID){
		boolean isAdd = false;
		int afterMoney = 0;
		int intMoney = Integer.parseInt(GetMoney.getMoney(strID));
		afterMoney = intMoney + money;
		if(afterMoney<=MAXMONEY){
			isAdd = true;
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
				stmt = conn.createStatement();
				String setStr = "update "+USER_TABLE+" set money = "+"'"+afterMoney+"' where id = "+strID;
				stmt.executeUpdate(setStr);
				stmt.close();
				conn.close();
			}catch(Exception e){
				//System.out.println("Wrong");
			}
		}
		return isAdd;
		
	}
	//取钱
	public static boolean delMoney(int money,String strID){
		int afterMoney = 0;
		int intMoney = Integer.parseInt(GetMoney.getMoney(strID));
		
		if(intMoney < money)
			return false;
		afterMoney = intMoney - money;
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement();
			String setStr = "update "+USER_TABLE+" set money = "+"'"+afterMoney+"' where id = "+strID;
			stmt.executeUpdate(setStr);
			stmt.close();
			conn.close();
		}catch(Exception e){
			//System.out.println("Wrong");
		}
		return true;
	}
}
