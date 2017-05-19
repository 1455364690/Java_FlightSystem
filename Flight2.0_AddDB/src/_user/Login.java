package _user;
import _interface.Register_interface;
import _interface.Book_interface;
import _interface.Back_interface;
import _interface.Modi_tick_interface;

import java.sql.DriverManager;
import java.sql.*;
import javax.swing.JOptionPane; 
import _manager.DB_info;

public class Login extends DB_info{
	private static String userName;//�û�������û���
	private static String passWord;//�û����������
	private static String userPW;//�����ݿ��ж���������
	private static int state = 3;//״̬0��ʾ�û���Ʊ��״̬1��ʾ��Ʊ��2��ʾ�޸�Ʊ
	public static void login(int _state){
		//��������Ի����û������û���
		String queryStr = "select * from "+USER_TABLE;
		state = _state;
		userName =JOptionPane.showInputDialog("�������û���");
		boolean findName = false;
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(queryStr);
			while(res.next()){
			//	System.out.println(res.getString("name"));
				//System.out.println(userName);
				if(res.getString("name").equals(userName)){
					findName = true;
					userPW = res.getString("password");
					check();
					break;
				}
			}
			//System.out.println(findName);
			if(!findName){
				//���δɨ�赽����ʾ�û��Ƿ�ע��
				int flag;
				flag=JOptionPane.showConfirmDialog(null,"û�и��û������Ƿ�ע��", "ע�᣿",JOptionPane.YES_NO_OPTION);
				if(flag==0)//����û�ѡ��ע�������ע�᷽��
				{
					Register_interface	reg = new Register_interface();
				}
			}
			stmt.close();
			conn.close();
		}catch(Exception e){}
	}
	public static void check(){
		passWord =JOptionPane.showInputDialog("����������");
		//�ж������Ƿ���ȷ
			if(userPW.equals(passWord))
			{//״̬0��ʾ��Ʊ��1��ʾ��Ʊ��2��ʾ�޸�
				if(state == 0){
					Book_interface book = new Book_interface(userName);
				}else if(state == 1){
					Back_interface back = new Back_interface(userName);
				}else if(state == 2){
					Modi_tick_interface modi = new Modi_tick_interface(userName);
				}
			}else
			{
				int flag;//�������������û�ѡ���Ƿ���������
				flag=JOptionPane.showConfirmDialog(null,"������������,�Ƿ���������", "�������룿",JOptionPane.YES_NO_OPTION);
				if(flag==0)
					check();
			}
	}
}
