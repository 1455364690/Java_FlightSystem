package _user;

import java.sql.DriverManager;
import java.sql.ResultSet;

import _manager.ManagerInfo;

public class Search extends ManagerInfo{

	public static void search(String address1,String address2,String[] retStr,int num){
		String[] tempStr1 = new String[num];
		String[] tempStr2 = new String[num];
		String[] tempStr3= new String[num];
		int top1 = 0,top2 = 0, top3 = 0;
		//第1趟，查询起点和终点都符合要求的站
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement();
			String quaryAll = "select * from "+FLIGHT_TABLE;
			ResultSet res = stmt.executeQuery(quaryAll);
			while(res.next()){
				if((res.getString("address1")).equals(address1)&&(res.getString("address2")).equals(address2)){
					tempStr1[top1++] = res.getString("id");
				}else if((res.getString("address1")).equals(address1)){
					tempStr2[top2++] = res.getString("id");
				}else if((res.getString("address2")).equals(address2)){
					tempStr3[top3++] = res.getString("id");
				}
			}
		}catch(Exception e){}
		
		int j = 1;
		//设置标志-标识符合查询要求
		retStr[0]="a";
		for(int i = 0;i<top1;i++)
			retStr[j++] = tempStr1[i];
		//设置标志-标识符合起点
		retStr[j++] = "b";
		for(int i = 0;i<top2;i++)
			retStr[j++] = tempStr2[i];
		//设置标志-标识符合终点
		retStr[j++] = "c";
		for(int i = 0;i<top3;i++)
			retStr[j++] = tempStr3[i];
			
	}
	
}
