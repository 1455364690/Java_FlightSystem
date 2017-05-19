package _user;
import _manager.ModifyFlight;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import _manager.DB_info;
public class Book_tickets extends DB_info{
	private static int intID;
	private static String strID;
	public static int book(String num,String amount,String name){//航班号，购票数，用户名
		intID = GetID.get(name);
		//System.out.print(intID);
		strID = intID +"";
		int isBook = 2;//0状态表示购票成功，1状态表示航班号输入有误，2状态表示票数有误
		int maxTic = 0;int soldTic = 0;//最大票数，已售票数
		int buyTic =  Integer.parseInt(amount);//购买票数的int型
		int intNum =  Integer.parseInt(num);//ID的int型
		if(intNum > ModifyFlight.getMaxNum()){//如果超过了最大ID则返回
			isBook = 1;//1状态表示航班号输入有误
			return isBook;
		}
		try{
			/*阶段1，修改航班信息数据库*******************/
			String checkTic = "select * from "+FLIGHT_TABLE;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(checkTic);
			while(res.next()){
				if(res.getString("id").equals(num)){
					maxTic = Integer.parseInt(res.getString("totaltickets"));
					soldTic = Integer.parseInt(res.getString("soldtickets"));
					if(soldTic+buyTic<=maxTic){//如果已售的票加上本次购买的票不大于最高票数，则在已售票加上本次购票数
						isBook = 0;
						soldTic +=buyTic;
					}else{
						isBook = 1;//否则信号置1
					} 
				}
			}
			String setStr = "update "+FLIGHT_TABLE+" set soldtickets ="+"'"+soldTic+"' where id ="+num;
			stmt.executeUpdate(setStr);
			//System.out.println(isBook+","+maxTic+","+soldTic+","+amount+num);
			stmt.close();
			conn.close();
		}catch(Exception e){
			//System.out.println(e.getMessage());
		}
		
		if(isBook == 0){//如果购票成功
			try{
				/*阶段2，查看用户信息表是否存在，不存在则新建之*/
				String createTableUserFlight = "create table "+USER_FLIGHT_TABLE+"(userid varchar(3),flightid varchar(3),amount varchar(3))"
						+ "CHARACTER SET utf8 COLLATE utf8_general_ci";
				String checkTableUserFlight="show tables like \""+USER_FLIGHT_TABLE+"\"";  
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
				stmt = conn.createStatement();
				ResultSet resultSet=stmt.executeQuery(checkTableUserFlight); //连接状态
				if (!resultSet.next()) {   
					stmt.executeUpdate(createTableUserFlight);
		        }else{}
				stmt.close();
				conn.close();
			}catch(Exception e){
				//System.out.println(e.getMessage());
			}boolean find = false;
			try{
				/*阶段3，在表中查看目录，有则更改记录*/
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
				stmt = conn.createStatement();
				String checkTic = "select * from "+USER_FLIGHT_TABLE;
				ResultSet res = stmt.executeQuery(checkTic);
				//res.last();
				while(res.next()){
					//查看用户是否已经购买过此票，如果购买过，则修改票数
					//System.out.println((res.getString("userid")+""+intID+""+res.getString("flightid")+""+num));
					if(res.getString("userid").equals(""+intID)&&res.getString("flightid").equals(num)){
						find = true;
						stmt = conn.createStatement();
						int bought = Integer.parseInt(res.getString("amount"));
						bought += buyTic;
						String setStr = "update "+USER_FLIGHT_TABLE+" set amount = "+"'"+bought+"' where userid ="+intID+" and flightid = "+num;
						stmt.executeUpdate(setStr);
					}
				}//如果没有购买过，则插入一条记录
				stmt.close();
				conn.close();
			}catch(Exception e){System.out.println(e.toString());}
			/*阶段4，在表中查看目录，没有则插入记录*/
			//System.out.println(find);
			if(!find){
				try{
					DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
					conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
					stmt = conn.createStatement();
					String line= "'"+intID+"'"+","+"'"+num+"'"+","+"'"+amount+"'";
					String insertTableUserFlight = "insert into "+USER_FLIGHT_TABLE+" (userid,flightid,amount)"
		                        + "values( "+line+" )";
					stmt.executeUpdate(insertTableUserFlight);//执行插入的语句
					stmt.close();
					conn.close();
				}catch(Exception e){}
			}
		}
		return isBook;
	}
	public static void main(String[] args){
		book("1","2","s123123");
	}
}
