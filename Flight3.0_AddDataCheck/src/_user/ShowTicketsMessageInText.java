package _user;

import java.sql.DriverManager;
import java.sql.ResultSet;

import _manager.DB_info;
import _manager.ModifyFlight;
public class ShowTicketsMessageInText extends DB_info{
	public static String showMessage(String name){
		String message = "���Ѿ�������������\r\n";
		String tempStr = "";
		int intID = GetID.get(name);//�õ��û���ID
		boolean find = false;
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement();
			String mesStr = "select * from "+USER_FLIGHT_TABLE;
			ResultSet res = stmt.executeQuery(mesStr);
			while(res.next()){
				for(int i = 1;i<ModifyFlight.getMaxNum();i++){//�����к�����б���
					if((res.getString("userid")).equals(""+intID)&&(res.getString("flightid")).equals(""+i)&&
							!(res.getString("amount")).equals("0")){
						find = true;
						tempStr = res.getString("flightid")+"�ź���"+res.getString("amount")+"��Ʊ;\r\n";
						message = message + tempStr;
					}
					
				}
			}
		}catch(Exception e){
			//System.out.println("cccc");
		}
		if(!find)
			message = "��û�й����¼";
		
		return message;
	}
}
