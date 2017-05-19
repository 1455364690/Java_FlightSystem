package _flight;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;  
import _manager.DB_info;
public class List extends DB_info{
	public static void list(int num,String[] li){
		//System.out.println(num);
		try{
			String queryStr = "select * from "+FLIGHT_TABLE;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement();
			
			ResultSet res = stmt.executeQuery(queryStr);
			while(res.next()){
				if(res.getString("id").equals(""+num)){
					li[0] = res.getString("id");
					li[1] = res.getString("address1");
					li[2] = res.getString("address2");
					li[3] = res.getString("totaltickets");
					li[4] = res.getString("soldtickets");
					li[5] = res.getString("date");
					li[6] = res.getString("time");
				}
			}
		}catch(Exception e){
			//System.out.println("Wrong");
			JOptionPane.showMessageDialog(null,"û�д˺���");
		}
	}
//	public static void main(String[] args){
//		String[] a = new String[7];
//		list(1,a);
//		//for(int i = 0;i<7;i++)
//			//System.out.println(a[i]);
//	}
}
