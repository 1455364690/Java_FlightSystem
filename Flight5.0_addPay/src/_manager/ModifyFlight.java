package _manager;
//ʵ�ֺ������ɾ�Ĳ�Ĺ���
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
//���ܣ�����Ա��ɾ�ĵĹ��ܣ���õ������к��������
public class ModifyFlight extends ManagerInfo{

	public static void delFlight(int num){
		if(num>getMaxNum()){//�����������������������ʾ����
			JOptionPane.showMessageDialog(null, "�������������");
		}
		else{
			try{		
				String delFlight = "delete from "+FLIGHT_TABLE+" where id="+num;
				/*step 1 ɾ��������Ϣ****************/
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
				stmt = conn.createStatement();
				stmt.executeUpdate(delFlight);
				stmt.close();
				conn.close();
				/*step 2�޸����������ID�ţ���ɾ������Ŵ�ĺ�����Լ�1****************/
				Connection conn = null;
				Statement stmt = null;
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
				stmt = conn.createStatement();
				int deledNum = getMaxNum()+1;//��󺽰��
				String[] mds = new String[deledNum-num];
				//����ָ��
				for(int i = num+1;i<deledNum+1;i++){
				mds[deledNum-i] = "update "+FLIGHT_TABLE+" set id='"+(i-1)+"' where id = '"+i+"'";
				}
				//ִ��ָ��
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
								String _total_tickets,String _sold_tickets,String _price,
								String _month,String _day,String _hour,String _minute,String _time){
		int flightNum;//���еĺ�����
		String checkTableflight="show tables like \""+FLIGHT_TABLE+"\"";  
		String createTableflight = "create table "+FLIGHT_TABLE+"(id varchar(3),address1 varchar(10),"
				+ "address2 varchar(10),totaltickets varchar(4),soldtickets varchar(4),price varchar(5),month varchar(2),day varchar(2),hour varchar(2),minute varchar(2),time varchar(5))"
				+ "CHARACTER SET utf8 COLLATE utf8_general_ci";
		try{
			/*step 1 ������ݿ⺽����Ƿ���ڣ�����������򴴽�֮****************/
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement();
			ResultSet resultSet=stmt.executeQuery(checkTableflight); //����״̬
			if (!resultSet.next()) {   
				stmt.executeUpdate(createTableflight);
	        }
			stmt.close();
			conn.close();
			/*step 2 ��ȡ���ݿ������е���������****************/
			flightNum = getMaxNum();
			/*step  3   ��������****************/
			String line= (flightNum+1)+","+"'"+_address1+"'"+","+"'"+_address2+"'"+","+"'"+_total_tickets+"'"
					+","+"'"+_sold_tickets+"'"+","+"'"+_price+"'"+","+"'"+_month+"'"+","+"'"+_day+"'"+","+"'"+_hour+"'"+","+"'"+_minute+"'"+","+"'"+_time+"'";
			String insertTableFlight= "insert into "+FLIGHT_TABLE+"(id,address1,address2,totaltickets,soldtickets,price,month,day,hour,minute,time)"
                    + "values("+line+")";
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement();
			stmt.executeUpdate(insertTableFlight);//ִ�в�������
			
			stmt.close();
			conn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public static void modiFlight(String num,String _address1,String _address2,
								  String _total_tickets,String _sold_tickets,String _price,
								  String _month,String _day,String _hour,String _minute,String _time)
	{
		if(num == null||num.equals("")){//�����������������������ʾ����
			JOptionPane.showMessageDialog(null, "�������������");
		}else if(Integer.parseInt(num)>getMaxNum()){//�����������������������ʾ����
			JOptionPane.showMessageDialog(null, "�������������");
		}else{
			/*step 1 �������Ϊ�գ�ȡ����Ӧ��ԭ���ݿ��е�����**********/
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
				stmt = conn.createStatement();
				String quaryAll = "select * from "+FLIGHT_TABLE;
				ResultSet res = stmt.executeQuery(quaryAll);
				while(res.next()){
					if(res.getString("id").equals(num)){
						//�����������Ϊ�յ���������ݿ��Ӧ�����ݲ���
						if(_address1==null||_address1.equals(""))
							_address1 = res.getString("address1");
						if(_address2==null||_address2.equals(""))
							_address2 = res.getString("address2");
						if(_total_tickets==null||_total_tickets.equals(""))
							_total_tickets = res.getString("totaltickets");
						if(_sold_tickets==null||_sold_tickets.equals(""))
							_sold_tickets = res.getString("soldtickets");
						if(_price==null||_price.equals(""))
							_price = res.getString("price");
						if(_month==null||_month.equals(""))
							_month = res.getString("month");
						if(_day==null||_day.equals(""))
							_day = res.getString("day");
						if(_hour==null||_hour.equals(""))
							_hour = res.getString("hour");
						if(_minute==null||_minute.equals(""))
							_minute = res.getString("minute");
						if(_time==null||_time.equals(""))
							_time = res.getString("time");
					}
					
				}
				stmt.close();
				conn.close();
			}catch(Exception e){
			}
			/*step 2 д�����ݿ�**********/
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
				stmt = conn.createStatement();
				String md = "update "+FLIGHT_TABLE+" set id="+"'"+num+"'"
							+",address1="+"'"+_address1+"'"
							+",address2="+"'"+_address2+"'"
							+",totaltickets="+"'"+_total_tickets+"'"
							+",soldtickets="+"'"+_sold_tickets+"'"
							+",price="+"'"+_price+"'"
							+",month="+"'"+_month+"'"
							+",day="+"'"+_day+"'"
							+",hour="+"'"+_hour+"'"
							+",minute="+"'"+_minute+"'"
							+",time="+"'"+_time+"'"+" where id = "+num;
				stmt.executeUpdate(md);
				stmt.close();
				conn.close();
			}catch(Exception e){
				//System.out.println(e.toString());
			}
		}
		
		
	}
	//���û���
	public static int getMaxID(){
		int Maxnum = 0;
		try{
			String countTableUser = "select * from "+USER_TABLE;
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); ;
			ResultSet rset = stmt.executeQuery(countTableUser);
			rset.last();
			Maxnum = rset.getRow();
			stmt.close();
			conn.close();
		}catch(Exception e){}
		return Maxnum;
	}
	//�󺽰���
	public static int getMaxNum(){
		int Maxnum = 0;
		try{
			String countTableFlight = "select * from "+FLIGHT_TABLE;
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); ;
			ResultSet rset = stmt.executeQuery(countTableFlight);
			rset.last();
			Maxnum = rset.getRow();
			stmt.close();
			conn.close();
		}catch(Exception e){
			//System.out.println("Wrong");
		}
		return Maxnum;
	}
	//public static void main(String[] args){
		//modiFlight("2","�Ͼ�","����","500","1","200","06","06","06","06","06");
		//modiFlight("2",null,null,null,null,null,null,null,null,null,null);
		//addFlight("�Ͼ�","�Ͼ�","500","0","2017","2");
		//modiFlight("1","�Ͼ�","����","500","200","2017","2");
		//delFlight(100);
		//System.out.println(getMaxNum());
	//}
}
