package _user;
//实现用户退票功能，修改数据库中对应信息
//作者：孙加辉，时间：2017/05/07
import _manager.ModifyFlight;
import java.sql.DriverManager;
import java.sql.ResultSet;
import _manager.ManagerInfo;

public class Back_tickets extends ManagerInfo{
	private static int intID;
	private static String strID;
	public static int back(String num,String amount,String name){
		int isBack = 2;//2表示票数错误，1表示航班号错误
		//已购的票数
		int boughtTic = 0;
		//用户Id
		intID = GetID.get(name);
		//用户ID
		strID = intID +"";
		//账户余额
		//int intMoney = Integer.parseInt(GetMoney.getMoney(strID));
		//要退的钱
		int afterMoney = 0;
		//退票数
		int intAmount =  Integer.parseInt(amount);
		//航班号
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
					//如果已买的票小于退票数
					if(boughtTic < intAmount)
						isBack = 2;
					else{
						//修改已购票数
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
			//修改用户信息表中的已购票数
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
		//System.out.println(isBack);
		if(find&&isBack == 0){
			//航班的已售票数
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
						afterMoney = intAmount*Integer.parseInt(res.getString("price"));
					}
				}
				stmt.close();
				conn.close();
			}catch(Exception e){
				//System.out.println("Wrong2");
			}
			//修改航班中的已售票数
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
			//修改用户的钱
			ChangeMoney.addMoney(afterMoney,strID);
		}
		
		return isBack;
	}
//	public static void main(String[] args){
//		back("1","1","s123123");
//	}
}
