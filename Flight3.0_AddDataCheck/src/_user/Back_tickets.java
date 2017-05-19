package _user;
import _manager.ModifyFlight;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import _manager.DB_info;

public class Back_tickets extends DB_info{
	private static int intID;
	private static String strID;
	public static int back(String num,String amount,String name){
		int isBack = 2;//2表示票数错误，1表示航班号错误
		int boughtTic = 0;
		intID = GetID.get(name);
		strID = intID +"";
		int intAmount =  Integer.parseInt(amount);
		int intNum =  Integer.parseInt(num);
		if(intNum > ModifyFlight.getMaxNum()){
			isBack = 1;
			return isBack;
		}
		boolean find = false;
		//查询是否存在该用户信息
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement();
			String checkTic = "select * from "+ USER_FLIGHT_TABLE;
			ResultSet res = stmt.executeQuery(checkTic);
			while(res.next()){
				if((res.getString("userid")).equals(strID)&&(res.getString("flightid")).equals(num)){
					find = true;//如果存在则返回
					boughtTic = Integer.parseInt(res.getString("amount"));
					if(boughtTic < intAmount)
						isBack = 2;
					else{
						isBack = 0;
						boughtTic -= intAmount;
					}
				}
			}
			stmt.close();
			conn.close();
		}catch(Exception e){}
		//如果存在用户信息，且购票数不小于退票数
		if(find&&(isBack == 0)){
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
				stmt = conn.createStatement();
				String modiStr = "update "+USER_FLIGHT_TABLE+" set amount = '"+boughtTic+"'"
						+ " where userid = "+strID+" and flightid = "+num;
				stmt.executeUpdate(modiStr);
				stmt.close();
				conn.close();
			}catch(Exception e){}
		}
		//修改系统航班信息
		//System.out.println(boughtTic);
		if(find){
			int changeAmount = 0;
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
				stmt = conn.createStatement();
				String queryStr = "select * from "+FLIGHT_TABLE;
				ResultSet res = stmt.executeQuery(queryStr);
				while(res.next()){
					if((res.getString("id")).equals(num)){
						changeAmount = Integer.parseInt(res.getString("soldtickets")) - intAmount;
					}
				}
				stmt.close();
				conn.close();
			}catch(Exception e){
				//System.out.println("Wrong2");
			}
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
				stmt = conn.createStatement();
				String queryStr = "update "+FLIGHT_TABLE+" set soldtickets = '"+changeAmount+"'"
						+ "where id = "+num;
				stmt.executeUpdate(queryStr);
				stmt.close();
				conn.close();
			}catch(Exception e){
				//System.out.println("Wrong1");
			}
		}
		
	//	System.out.println("ok");
		return isBack;
	}
//	public static void main(String[] args){
//		back("1","1","s123123");
//	}
}
