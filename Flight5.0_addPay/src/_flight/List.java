package _flight;
import java.sql.DriverManager;
import java.sql.ResultSet; 
import _manager.ManagerInfo;
//根据输入的航班号，将该航班的航班信息存储在String数组中
//作者：孙加辉，时间：2017/05/07
public class List extends ManagerInfo{
	public static void list(int num,String[] li){//航班号，空数组
		try{
			String queryStr = "select * from "+FLIGHT_TABLE;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(queryStr);//查询数据库中所有元素
			while(res.next()){
				if(res.getString("id").equals(""+num)){//如果找到了，则将其记录都存放在数组中
					li[0] = res.getString("id");
					li[1] = res.getString("address1");
					li[2] = res.getString("address2");
					li[3] = res.getString("totaltickets");
					li[4] = res.getString("soldtickets");
					li[5] = res.getString("price")+"元";
					li[6] = res.getString("month")+"月"+res.getString("day")+"日"+res.getString("hour")+":"+res.getString("minute");
					li[7] = "   "+res.getString("time");
				}
			}
		}catch(Exception e){
			//JOptionPane.showMessageDialog(null,"没有此航班");
		}
	}
}
