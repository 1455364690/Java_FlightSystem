package _manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
//功能：管理员增删改的功能，获得当期已有航班的数量
public class ModifyFlight extends ManagerInfo{

	public static void delFlight(int num){
		//String tempNum;
		//boolean find = false;//检查是否找到要删除的序号
		//System.out.println(getMaxNum());
		//System.out.println(num);
		if(num>getMaxNum()){//如果输入数超过航班数则提示有误
			JOptionPane.showMessageDialog(null, "航班号输入有误");
		}
		else{
			try{		
				String delFlight = "delete from "+FLIGHT_TABLE+" where id="+num;
				String queryStr = "select * from "+FLIGHT_TABLE;
				/*step 1 删除航班信息****************/
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
				stmt = conn.createStatement();
				stmt.executeUpdate(delFlight);
				stmt.close();
				conn.close();
				/*step 2修改其他航班的ID号，比删除航班号大的航班号自减1****************/
				Connection conn = null;
				Statement stmt = null;
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
				stmt = conn.createStatement();
				int deledNum = getMaxNum()+1;//最大航班号
				String[] mds = new String[deledNum-num];
				//保存指令
				for(int i = num+1;i<deledNum+1;i++){
				mds[deledNum-i] = "update "+FLIGHT_TABLE+" set id='"+(i-1)+"' where id = '"+i+"'";
				}
				//执行指令
			for(int i=deledNum-num-1;i>=0;i--)
			 {
				stmt.executeUpdate(mds[i]);
			 }
				stmt.close();
				conn.close();
			}catch(Exception e){
			}
		}
		
}
	public static void addFlight(String _address1,String _address2,
								String _total_tickets,String _sold_tickets,
								String _date,String _time){
		int flightNum;
		String checkTableflight="show tables like \""+FLIGHT_TABLE+"\"";  
		String createTableflight = "create table "+FLIGHT_TABLE+"(id varchar(3),address1 varchar(10),"
				+ "address2 varchar(10),totaltickets varchar(4),soldtickets varchar(4),date varchar(20),time varchar(5))"
				+ "CHARACTER SET utf8 COLLATE utf8_general_ci";
		try{
			/*step 1 检测数据库航班表是否存在，如果不存在则创建之****************/
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement();
			ResultSet resultSet=stmt.executeQuery(checkTableflight); //连接状态
			if (!resultSet.next()) {   
				stmt.executeUpdate(createTableflight);
	        }
			stmt.close();
			conn.close();
			/*step 2 获取数据库中已有的数据数量****************/
			flightNum = getMaxNum();
			/*step  3   插入数据****************/
			String line= (flightNum+1)+","+"'"+_address1+"'"+","+"'"+_address2+"'"+","+"'"+_total_tickets+"'"
					+","+"'"+_sold_tickets+"'"+","+"'"+_date+"'"+","+"'"+_time+"'";
			String insertTableFlight= "insert into "+FLIGHT_TABLE+"(id,address1,address2,totaltickets,soldtickets,date,time)"
                    + "values("+line+")";
			
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement();
			stmt.executeUpdate(insertTableFlight);//执行插入的语句
			stmt.close();
			conn.close();
		}catch(Exception e){
			//System.out.println("Wrong");
		}
	}
	public static void modiFlight(String num,String _address1,String _address2,
								  String _total_tickets,String _sold_tickets,
							   	  String _date,String _time)
	{
		//boolean flag = false;
		if(Integer.parseInt(num)>getMaxNum()){//如果输入数超过航班数则提示有误
			JOptionPane.showMessageDialog(null, "航班号输入有误");
		}
		else{
			try{
				//String queryStr = "select * from "+FLIGHT_TABLE;
				/*step 1*/
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
				stmt = conn.createStatement();
				String md = "update "+FLIGHT_TABLE+" set id="+"'"+num+"'"
							+",address1="+"'"+_address1+"'"
							+",address2="+"'"+_address2+"'"
							+",totaltickets="+"'"+_total_tickets+"'"
							+",soldtickets="+"'"+_sold_tickets+"'"
							+",date="+"'"+_date+"'"
							+",time="+"'"+_time+"'"+" where id = "+num;
				//System.out.println(md);
				stmt.executeUpdate(md);
				stmt.close();
				conn.close();
			}catch(Exception e){
			//	System.out.println(e.toString());
			}
		}
		
		
	}
	//求用户数
	public static int getMaxID(){
		int Maxnum = 0;
		try{
			String countTableUser = "select * from "+USER_TABLE;
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); ;
			ResultSet rset = stmt.executeQuery(countTableUser);
			rset.last();
			Maxnum = rset.getRow();
			stmt.close();
			conn.close();
		}catch(Exception e){}
		return Maxnum;
	}
	//求航班数
	public static int getMaxNum(){
		int Maxnum = 0;
		try{
			String countTableFlight = "select * from "+FLIGHT_TABLE;
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); ;
			ResultSet rset = stmt.executeQuery(countTableFlight);
			rset.last();
			Maxnum = rset.getRow();
			stmt.close();
			conn.close();
		}catch(Exception e){}
		return Maxnum;
	}
	//public static void main(String[] args){
		//addFlight("南京","南京","500","0","2017","2");
		//addFlight("南京","南京","500","0","2017","2");
		//modiFlight("1","南京","北京","500","200","2017","2");
		//delFlight(100);
		//System.out.println(getMaxNum());
	//}
}
