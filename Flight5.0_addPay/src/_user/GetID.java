package _user;
//���ߣ���ӻԣ�ʱ��2017/05/01
//���ܣ�����һ���û����������û�ID
import java.sql.DriverManager;
import java.sql.ResultSet;
import _manager.ManagerInfo;

public class GetID extends ManagerInfo{
	public static int get(String userName){
		String temp="";
		String queryStr = "select * from "+USER_TABLE;
		boolean findName = false;
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
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
		}//����ID
		return (Integer.parseInt(temp));
	}
//	public static void main(String[] args){
//		System.out.println(get("s123123123"));
//	}
}
