package _user;
//ʵ���û���Ʊ���ܣ��޸����ݿ��ж�Ӧ��Ϣ
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import _manager.ModifyFlight;
import java.sql.DriverManager;
import java.sql.ResultSet;
import _manager.ManagerInfo;

public class Back_tickets extends ManagerInfo{
	private static int intID;
	private static String strID;
	public static int back(String num,String amount,String name){
		int isBack = 2;//2��ʾƱ������1��ʾ����Ŵ���
		//�ѹ���Ʊ��
		int boughtTic = 0;
		//�û�Id
		intID = GetID.get(name);
		//�û�ID
		strID = intID +"";
		//�˻����
		//int intMoney = Integer.parseInt(GetMoney.getMoney(strID));
		//Ҫ�˵�Ǯ
		int afterMoney = 0;
		//��Ʊ��
		int intAmount =  Integer.parseInt(amount);
		//�����
		int intNum =  Integer.parseInt(num);
		if(intNum > ModifyFlight.getMaxNum()){
			isBack = 1;
			return isBack;
		}
		boolean find = false;
		//��ѯ�Ƿ���ڸ��û���Ϣ
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement();
			String checkTic = "select * from "+ USER_FLIGHT_TABLE;
			ResultSet res = stmt.executeQuery(checkTic);
			while(res.next()){
				if((res.getString("userid")).equals(strID)&&(res.getString("flightid")).equals(num)){
					find = true;//��������򷵻�
					boughtTic = Integer.parseInt(res.getString("amount"));
					//��������ƱС����Ʊ��
					if(boughtTic < intAmount)
						isBack = 2;
					else{
						//�޸��ѹ�Ʊ��
						isBack = 0;
						boughtTic -= intAmount;
					}
				}
			}
			stmt.close();
			conn.close();
		}catch(Exception e){}
		//��������û���Ϣ���ҹ�Ʊ����С����Ʊ��
		if(find&&(isBack == 0)){
			//�޸��û���Ϣ���е��ѹ�Ʊ��
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
				stmt = conn.createStatement();
				String modiStr = "update "+USER_FLIGHT_TABLE+" set amount = '"+boughtTic+"'"
						+ " where userid = "+strID+" and flightid = "+num;
				stmt.executeUpdate(modiStr);
				stmt.close();
				conn.close();
			}catch(Exception e){}
		}
		//�޸�ϵͳ������Ϣ
		//System.out.println(isBack);
		if(find&&isBack == 0){
			//���������Ʊ��
			int changeAmount = 0;
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
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
			//�޸ĺ����е�����Ʊ��
			try{
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
				stmt = conn.createStatement();
				String queryStr = "update "+FLIGHT_TABLE+" set soldtickets = '"+changeAmount+"'"
						+ "where id = "+num;
				stmt.executeUpdate(queryStr);
				stmt.close();
				conn.close();
			}catch(Exception e){
				//System.out.println("Wrong1");
			}
			//�޸��û���Ǯ
			ChangeMoney.addMoney(afterMoney,strID);
		}
		
		return isBack;
	}
//	public static void main(String[] args){
//		back("1","1","s123123");
//	}
}
