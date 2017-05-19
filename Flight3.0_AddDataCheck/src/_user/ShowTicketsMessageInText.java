package _user;

import java.sql.DriverManager;
import java.sql.ResultSet;

import _manager.DB_info;
import _manager.ModifyFlight;
public class ShowTicketsMessageInText extends DB_info{
	public static String showMessage(String name){
		String message = "您已经购买的情况如下\r\n";
		String tempStr = "";
		int intID = GetID.get(name);//得到用户的ID
		boolean find = false;
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement();
			String mesStr = "select * from "+USER_FLIGHT_TABLE;
			ResultSet res = stmt.executeQuery(mesStr);
			while(res.next()){
				for(int i = 1;i<ModifyFlight.getMaxNum();i++){//对所有航班进行遍历
					if((res.getString("userid")).equals(""+intID)&&(res.getString("flightid")).equals(""+i)&&
							!(res.getString("amount")).equals("0")){
						find = true;
						tempStr = res.getString("flightid")+"号航班"+res.getString("amount")+"张票;\r\n";
						message = message + tempStr;
					}
					
				}
			}
		}catch(Exception e){
			//System.out.println("cccc");
		}
		if(!find)
			message = "您没有购买记录";
		
		return message;
	}
}
