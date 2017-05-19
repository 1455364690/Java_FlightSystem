package _user;
//根据用户输入的起始地址，返回相应的信息和推荐的信息
//作者：孙加辉，时间：2017/05/07
import java.sql.DriverManager;
import java.sql.ResultSet;
import _manager.ManagerInfo;
public class Search extends ManagerInfo{
	public static int search(String address1,String address2,String[] retStr,int num,String[] addr1,String[] addr2){
		//存放起点和终点都符合要求的航班的ID
		String[] tempStr1 = new String[num];
		//存放起点符合要求的航班的ID
		String[] tempStr2 = new String[num];
		//存放终点符合要求的航班的ID
		String[] tempStr3= new String[num];
		String[] tempStr2_2 = new String[num];
		String[] tempStr3_2= new String[num];
		//两者之差为转机相隔时间
		long[] addr1Mins = new long[num];
		long[] addr2Mins = new long[num];
		int  top1 = 0,top2 = 0, top3 = 0;
		//推荐的数量
		int count = 0;
		//第1趟，查询起点和终点都符合要求的站
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement();
			String quaryAll = "select * from "+FLIGHT_TABLE;
			ResultSet res = stmt.executeQuery(quaryAll);
			while(res.next()){
				//起点和终点都符合要求的航班
				if((res.getString("address1")).equals(address1)&&(res.getString("address2")).equals(address2)){
					tempStr1[top1++] = res.getString("id");
				}
				//起点符合要求的航班
				else if((res.getString("address1")).equals(address1)){
					tempStr2[top2] = res.getString("id");
					tempStr2_2[top2] = res.getString("address2");
					addr1Mins[top2] = Integer.parseInt(res.getString("month"))*30*24*60
							+Integer.parseInt(res.getString("day"))*24*60
							+Integer.parseInt(res.getString("hour"))*60
							+Integer.parseInt(res.getString("minute"))
							+Integer.parseInt(res.getString("time"))*60;
					top2++;
				}
				///终点符合要求的航班
				else if((res.getString("address2")).equals(address2)){
					tempStr3[top3] = res.getString("id");
					tempStr3_2[top3] = res.getString("address1");
					addr2Mins[top3] = Integer.parseInt(res.getString("month"))*30*24*60
							+Integer.parseInt(res.getString("day"))*24*60
							+Integer.parseInt(res.getString("hour"))*60
							+Integer.parseInt(res.getString("minute"));
					top3++;
				}
			}
			stmt.close();
			conn.close();
		}catch(Exception e){}
		
		//推荐的航班，即起点符号要求的终点与终点符合要求的起点相同
		for(int a = 0;a<top2;a++){
			for(int b = 0;b<top3;b++){
				if(tempStr2_2[a].equals(tempStr3_2[b])){
					//如果两个航班相隔的时间符号要求
					if((addr2Mins[b]-addr1Mins[a]>=MINWAITTIME)&&(addr2Mins[b]-addr1Mins[a]<=MAXWAITTIME)){
						addr1[count] = tempStr2[a];
						addr2[count] = tempStr3[b];
						count++;
					}
				}
			}
		}
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
		//推荐的航班数
		return count;
	}
	
}
