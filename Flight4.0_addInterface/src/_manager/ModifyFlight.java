package _manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
//���ܣ�����Ա��ɾ�ĵĹ��ܣ���õ������к��������
public class ModifyFlight extends ManagerInfo{

	public static void delFlight(int num){
		//String tempNum;
		//boolean find = false;//����Ƿ��ҵ�Ҫɾ�������
		//System.out.println(getMaxNum());
		//System.out.println(num);
		if(num>getMaxNum()){//�����������������������ʾ����
			JOptionPane.showMessageDialog(null, "�������������");
		}
		else{
			try{		
				String delFlight = "delete from "+FLIGHT_TABLE+" where id="+num;
				String queryStr = "select * from "+FLIGHT_TABLE;
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
								String _total_tickets,String _sold_tickets,
								String _date,String _time){
		int flightNum;
		String checkTableflight="show tables like \""+FLIGHT_TABLE+"\"";  
		String createTableflight = "create table "+FLIGHT_TABLE+"(id varchar(3),address1 varchar(10),"
				+ "address2 varchar(10),totaltickets varchar(4),soldtickets varchar(4),date varchar(20),time varchar(5))"
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
					+","+"'"+_sold_tickets+"'"+","+"'"+_date+"'"+","+"'"+_time+"'";
			String insertTableFlight= "insert into "+FLIGHT_TABLE+"(id,address1,address2,totaltickets,soldtickets,date,time)"
                    + "values("+line+")";
			
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement();
			stmt.executeUpdate(insertTableFlight);//ִ�в�������
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
		if(Integer.parseInt(num)>getMaxNum()){//�����������������������ʾ����
			JOptionPane.showMessageDialog(null, "�������������");
		}
		else{
			try{
				//String queryStr = "select * from "+FLIGHT_TABLE;
				/*step 1*/
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
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
		}catch(Exception e){}
		return Maxnum;
	}
	//public static void main(String[] args){
		//addFlight("�Ͼ�","�Ͼ�","500","0","2017","2");
		//addFlight("�Ͼ�","�Ͼ�","500","0","2017","2");
		//modiFlight("1","�Ͼ�","����","500","200","2017","2");
		//delFlight(100);
		//System.out.println(getMaxNum());
	//}
}
