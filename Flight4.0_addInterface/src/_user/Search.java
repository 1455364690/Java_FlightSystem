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
		//��1�ˣ���ѯ�����յ㶼����Ҫ���վ
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
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
		//���ñ�־-��ʶ���ϲ�ѯҪ��
		retStr[0]="a";
		for(int i = 0;i<top1;i++)
			retStr[j++] = tempStr1[i];
		//���ñ�־-��ʶ�������
		retStr[j++] = "b";
		for(int i = 0;i<top2;i++)
			retStr[j++] = tempStr2[i];
		//���ñ�־-��ʶ�����յ�
		retStr[j++] = "c";
		for(int i = 0;i<top3;i++)
			retStr[j++] = tempStr3[i];
			
	}
	
}
