package _user;
//��ʾ�û��Ĺ�Ʊ��Ϣ
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import java.sql.DriverManager;
import java.sql.ResultSet;
import _manager.ManagerInfo;
import _manager.ModifyFlight;
public class ShowTicketsMessageInText extends ManagerInfo{
	public static String showMessage(String name){
		String message = "���Ѿ�������������\r\n";
		String tempStr = "";//���ÿһ����¼
		int intID = GetID.get(name);//�õ��û���ID
		boolean find = false;
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement();
			String mesStr = "select * from "+USER_FLIGHT_TABLE;
			ResultSet res = stmt.executeQuery(mesStr);
			while(res.next()){
				for(int i = 1;i<=ModifyFlight.getMaxNum();i++){//�����к�����б���
					if((res.getString("userid")).equals(""+intID)&&(res.getString("flightid")).equals(""+i)&&
							!(res.getString("amount")).equals("0")){
						find = true;//��־�ҵ��˹����¼
						tempStr = res.getString("flightid")+"�ź���"+res.getString("amount")+"��Ʊ;\r\n";
						message = message + tempStr;//���ҵ��ļ�¼��ӵ�message��
					}
					
				}
			}
		}catch(Exception e){}
		if(!find)
			message = "��û�й����¼";
		
		return message;
	}
}
