package _user;
//����һ���û�ID�������û���Ǯ����Ϣ
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import java.sql.DriverManager;
import java.sql.ResultSet;
import _manager.ManagerInfo;
public class GetMoney extends ManagerInfo{
	public static String getMoney(String num){
		String money = "";
		try{
			String queryStr = "select * from "+USER_TABLE;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(queryStr);
			while(res.next()){
				if(res.getString("id").equals(num)){
					money = res.getString("money");
				}
			}
			stmt.close();
			conn.close();
		}catch(Exception e){}
		
		
		return money;
	}
//	public static void main(String[] args){
//		System.out.println(getMoney("1"));
//	}
}
