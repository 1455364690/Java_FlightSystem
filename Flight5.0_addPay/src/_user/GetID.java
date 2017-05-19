package _user;
//作者：孙加辉，时间2017/05/01
//功能：输入一个用户名，返回用户ID
import java.sql.DriverManager;
import java.sql.ResultSet;
import _manager.ManagerInfo;

public class GetID extends ManagerInfo{
	public static int get(String userName){
		String temp="";
		String queryStr = "select * from "+USER_TABLE;
		boolean findName = false;
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(queryStr);
			while(res.next()){
				if(res.getString("name").equals(userName)){
					findName = true;
					temp = res.getString("id");
					break;
				}
			}
			if(!findName)
				return -1;
			stmt.close();
			conn.close();
		}catch(Exception e){
			return -1;
		}//返回ID
		return (Integer.parseInt(temp));
	}
//	public static void main(String[] args){
//		System.out.println(get("s123123123"));
//	}
}
