package _user;
//ʵ���û��ĵ�¼���ܣ���ѯ���ݿ���Ϣ
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import _interface.Book_interface;
import _interface.Back_interface;
import _interface.Modi_tick_interface;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.JOptionPane; 
import _manager.ManagerInfo;

public class Login extends ManagerInfo{
	private static String userName;//�û�������û���
	private static String passWord;//�û����������
	private static String userPW;//�����ݿ��ж���������
	private static int state = 3;//״̬0��ʾ�û���Ʊ��״̬1��ʾ��Ʊ��2��ʾ�޸�Ʊ
	private static boolean isRight = true;
	public static boolean login(int _state,String _name,String _pw){
		//��������Ի����û������û���
		String queryStr = "select * from "+USER_TABLE;
		state = _state;
		userName = _name;
		passWord = _pw;
		boolean findName = false;
		try{//�����Ƿ��ж�Ӧ���û���������ҵ�����������ȡ��
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(queryStr);
			while(res.next()){
				if(res.getString("name").equals(userName)){
					findName = true;
					userPW = res.getString("password");
					//���÷���
					check();
					break;
				}
			}
			if(!findName){
				//���δɨ�赽����ʾ�û��Ƿ�ע��
				JOptionPane.showMessageDialog(null, "�����û����Ƿ��������!");
			}
			stmt.close();
			conn.close();
		}catch(Exception e){
			System.out.println("Wrong");
		}
		return isRight;
	}
	public static void check(){
		//�ж������Ƿ���ȷ
			if(userPW.equals(passWord))
			{//״̬0��ʾ��Ʊ��1��ʾ��Ʊ��2��ʾ�޸�
				if(state == 0){
					Book_interface book = new Book_interface(userName);
					book.setEnabled(true);
				}else if(state == 1){
					Back_interface back = new Back_interface(userName);
					back.setEnabled(true);
				}else if(state == 2){
					Modi_tick_interface modi = new Modi_tick_interface(userName);
					modi.setEnabled(true);
				}
			}else
			{
				JOptionPane.showMessageDialog(null, "�������");
				isRight = false;
			}
	}
}
