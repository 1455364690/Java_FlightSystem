package _user;
//输入一个用户ID，返回用户的钱包信息
//作者：孙加辉，时间：2017/05/07
import java.sql.DriverManager;
import java.sql.ResultSet;
import _manager.ManagerInfo;
public class GetMoney extends ManagerInfo{
	public static String getMoney(String num){
		String money = "";
		try{
			String queryStr = "select * from "+USER_TABLE;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
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
