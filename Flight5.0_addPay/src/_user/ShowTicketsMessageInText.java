package _user;
//显示用户的购票信息
//作者：孙加辉，时间：2017/05/07
import java.sql.DriverManager;
import java.sql.ResultSet;
import _manager.ManagerInfo;
import _manager.ModifyFlight;
public class ShowTicketsMessageInText extends ManagerInfo{
	public static String showMessage(String name){
		String message = "您已经购买的情况如下\r\n";
		String tempStr = "";//存放每一条记录
		int intID = GetID.get(name);//得到用户的ID
		boolean find = false;
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement();
			String mesStr = "select * from "+USER_FLIGHT_TABLE;
			ResultSet res = stmt.executeQuery(mesStr);
			while(res.next()){
				for(int i = 1;i<=ModifyFlight.getMaxNum();i++){//对所有航班进行遍历
					if((res.getString("userid")).equals(""+intID)&&(res.getString("flightid")).equals(""+i)&&
							!(res.getString("amount")).equals("0")){
						find = true;//标志找到了购买记录
						tempStr = res.getString("flightid")+"号航班"+res.getString("amount")+"张票;\r\n";
						message = message + tempStr;//将找到的记录添加到message中
					}
					
				}
			}
		}catch(Exception e){}
		if(!find)
			message = "您没有购买记录";
		
		return message;
	}
}
