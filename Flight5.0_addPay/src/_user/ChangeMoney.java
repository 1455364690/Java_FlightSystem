package _user;
//ʵ���û���Ǯ���޸Ĺ��ܣ�ʵ���û��ĳ�ֵ���˿���Ϣ���޸����ݿ���Ϣ
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import java.sql.DriverManager;
import _manager.ManagerInfo;
public class ChangeMoney extends ManagerInfo{
	
	//��Ǯ
	public static boolean addMoney(int money,String strID){
		boolean isAdd = false;
		int afterMoney = 0;
		int intMoney = Integer.parseInt(GetMoney.getMoney(strID));
		afterMoney = intMoney + money;
		if(afterMoney<=MAXMONEY){
			isAdd = true;
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
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
	//ȡǮ
	public static boolean delMoney(int money,String strID){
		int afterMoney = 0;
		int intMoney = Integer.parseInt(GetMoney.getMoney(strID));
		
		if(intMoney < money)
			return false;
		afterMoney = intMoney - money;
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
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
