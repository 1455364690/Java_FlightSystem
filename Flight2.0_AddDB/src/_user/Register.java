package _user;
//���ߣ���ӻԣ�ʱ�䣺2017/05/01
//���ܣ�ʵ��ע�Ṧ�ܣ�����û������ע����Ϣ�Ƿ�׼ȷ
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import _manager.DB_info;
import _manager.ModifyFlight;

import javax.swing.JOptionPane;

public class Register extends DB_info{
	private static boolean state = false;//����ע��״̬���ɹ����
	private static String name ;//�����û���
	private static String pw;//��������
	private static String pw1;//�����ٴ����������
//	private final static String DB_DRIVER = "com.mysql.jdbc.Driver";//jdbc

	public static void setValue(String _name,String _pw,String _pw1){
		name = _name;
		pw = _pw;
		pw1 = _pw1;
	}
	public static void register(){
		int flag = check();//flagΪ ����״̬��0��ʾ������1��ʾ�û���������Ҫ��2��ʾ���벻����Ҫ��3��ʾ�������벻һ�£�//״̬4��ʾ�û����ѱ�ע��
		int userNumInDB = 0;
	//	System.out.println(flag);
		if(flag == 0){
			state =true;
			String checkTableNamePW="show tables like \""+USER_TABLE+"\"";  
			String createTableNamePW = "create table "+USER_TABLE+"(id varchar(3),name varchar(10),password varchar(10))"
					+ "CHARACTER SET utf8 COLLATE utf8_general_ci";
			//String countTableNamePW = "select * from "+USER_TABLE;
			try{
				/*step 1 ������ݿ��û���������Ƿ���ڣ�����������򴴽�֮****************/
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//��������
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
				stmt = conn.createStatement();
				ResultSet resultSet=stmt.executeQuery(checkTableNamePW); //����״̬
				if (!resultSet.next()) {   
					stmt.executeUpdate(createTableNamePW);
		        }
				stmt.close();
				conn.close();
				/*step 2 ��ȡ���ݿ������е���������****************/
				userNumInDB = ModifyFlight.getMaxID();
				/*step  3   ��������****************/
				String line= (userNumInDB+1)+","+"'"+name+"'"+","+"'"+pw+"'";
				String insertTableNamePW = "insert into "+USER_TABLE+"(id,name,password)"
                        + "values("+line+")";
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
				stmt = conn.createStatement();
				stmt.executeUpdate(insertTableNamePW);//ִ�в�������
				stmt.close();
				conn.close();
			}catch(Exception e){
				System.out.println("Wrong");
			}
}		
		else if(flag == 1){
			JOptionPane.showMessageDialog(null, "�û���������Ҫ��");
			state = false;
		}
		else if(flag == 2){
			JOptionPane.showMessageDialog(null, "���벻����Ҫ��");
			state = false;
		}
		else if(flag == 3){
			JOptionPane.showMessageDialog(null, "�������벻һ��");
			state = false;
		}
		else if(flag == 4){
			JOptionPane.showMessageDialog(null, "���û����ѱ�ע��");
			state = false;
		}
		else{
			JOptionPane.showMessageDialog(null, "״̬����");
			state = false;
		}
	}
	private static int check(){

		int isRight = 0;
		//����������ʽ
		Pattern name_pattern = Pattern.compile("^[A-Za-z][0-9A-Za-z]{6,10}$");
		Pattern pw_pattern = Pattern.compile("^[0-9a-zA-Z]{8,13}$");
		Matcher m1=name_pattern.matcher(name);
		Matcher m2=pw_pattern.matcher(pw);
		//���м��
		boolean t1 = m1.matches();
		boolean t2 = m2.matches();
		boolean t3 = pw.equals(pw1);
		boolean t4 = !check2();
		//t4ΪFALSE��ʾ�û����Ѵ��ڣ�t4ΪTRUE��ʾ�û���������
		//System.out.println(check2());
		if(!t1) isRight = 1;
		else if(t1&&!t2) isRight = 2;
		else if(t1&&t2&&!t3) isRight = 3;
		else if(!t4) isRight = 4;
		else if(t1&&t2&&t3&&t4) isRight = 0;
		return isRight;
	}
	private static boolean check2(){
		boolean find = false;
		/*�����ݿ�鿴�Ƿ����ݿ����Ƿ��Ѿ����û���Ϣ****************/
		try{
			String checkName = "select * from "+USER_TABLE+" where name="+"'"+name+"'";
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//��������
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); ;
			ResultSet rset = stmt.executeQuery(checkName);
			rset.next();
			int count = 0;
			count = rset.getInt(1);
			if(count>0){
				find = true;
			}
			stmt.close();
			conn.close();
		}catch(Exception e){
			find = false;
		}
		//System.out.println(find);
		return find;
	}
	public static boolean getState(){
		return state;
	}
//	public static void main(String[] args){
//		register();
//	}
}
