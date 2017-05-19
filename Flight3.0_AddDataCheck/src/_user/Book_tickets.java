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
	public static int book(String num,String amount,String name){//����ţ���Ʊ�����û���
		intID = GetID.get(name);
		//System.out.print(intID);
		strID = intID +"";
		int isBook = 2;//0״̬��ʾ��Ʊ�ɹ���1״̬��ʾ�������������2״̬��ʾƱ������
		int maxTic = 0;int soldTic = 0;//���Ʊ��������Ʊ��
		int buyTic =  Integer.parseInt(amount);//����Ʊ����int��
		int intNum =  Integer.parseInt(num);//ID��int��
		if(intNum > ModifyFlight.getMaxNum()){//������������ID�򷵻�
			isBook = 1;//1״̬��ʾ�������������
			return isBook;
		}
		try{
			/*�׶�1���޸ĺ�����Ϣ���ݿ�*******************/
			String checkTic = "select * from "+FLIGHT_TABLE;
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(checkTic);
			while(res.next()){
				if(res.getString("id").equals(num)){
					maxTic = Integer.parseInt(res.getString("totaltickets"));
					soldTic = Integer.parseInt(res.getString("soldtickets"));
					if(soldTic+buyTic<=maxTic){//������۵�Ʊ���ϱ��ι����Ʊ���������Ʊ������������Ʊ���ϱ��ι�Ʊ��
						isBook = 0;
						soldTic +=buyTic;
					}else{
						isBook = 1;//�����ź���1
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
		
		if(isBook == 0){//�����Ʊ�ɹ�
			try{
				/*�׶�2���鿴�û���Ϣ���Ƿ���ڣ����������½�֮*/
				String createTableUserFlight = "create table "+USER_FLIGHT_TABLE+"(userid varchar(3),flightid varchar(3),amount varchar(3))"
						+ "CHARACTER SET utf8 COLLATE utf8_general_ci";
				String checkTableUserFlight="show tables like \""+USER_FLIGHT_TABLE+"\"";  
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
				stmt = conn.createStatement();
				ResultSet resultSet=stmt.executeQuery(checkTableUserFlight); //����״̬
				if (!resultSet.next()) {   
					stmt.executeUpdate(createTableUserFlight);
		        }else{}
				stmt.close();
				conn.close();
			}catch(Exception e){
				//System.out.println(e.getMessage());
			}boolean find = false;
			try{
				/*�׶�3���ڱ��в鿴Ŀ¼��������ļ�¼*/
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
				stmt = conn.createStatement();
				String checkTic = "select * from "+USER_FLIGHT_TABLE;
				ResultSet res = stmt.executeQuery(checkTic);
				//res.last();
				while(res.next()){
					//�鿴�û��Ƿ��Ѿ��������Ʊ���������������޸�Ʊ��
					//System.out.println((res.getString("userid")+""+intID+""+res.getString("flightid")+""+num));
					if(res.getString("userid").equals(""+intID)&&res.getString("flightid").equals(num)){
						find = true;
						stmt = conn.createStatement();
						int bought = Integer.parseInt(res.getString("amount"));
						bought += buyTic;
						String setStr = "update "+USER_FLIGHT_TABLE+" set amount = "+"'"+bought+"' where userid ="+intID+" and flightid = "+num;
						stmt.executeUpdate(setStr);
					}
				}//���û�й�����������һ����¼
				stmt.close();
				conn.close();
			}catch(Exception e){System.out.println(e.toString());}
			/*�׶�4���ڱ��в鿴Ŀ¼��û��������¼*/
			//System.out.println(find);
			if(!find){
				try{
					DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
					conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
					stmt = conn.createStatement();
					String line= "'"+intID+"'"+","+"'"+num+"'"+","+"'"+amount+"'";
					String insertTableUserFlight = "insert into "+USER_FLIGHT_TABLE+" (userid,flightid,amount)"
		                        + "values( "+line+" )";
					stmt.executeUpdate(insertTableUserFlight);//ִ�в�������
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
