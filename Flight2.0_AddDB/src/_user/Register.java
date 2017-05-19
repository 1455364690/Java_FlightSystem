package _user;
//作者：孙加辉，时间：2017/05/01
//功能：实现注册功能；检测用户输入的注册信息是否准确
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import _manager.DB_info;
import _manager.ModifyFlight;

import javax.swing.JOptionPane;

public class Register extends DB_info{
	private static boolean state = false;//返回注册状态，成功与否
	private static String name ;//保存用户名
	private static String pw;//保存密码
	private static String pw1;//保存再次输入的密码
//	private final static String DB_DRIVER = "com.mysql.jdbc.Driver";//jdbc

	public static void setValue(String _name,String _pw,String _pw1){
		name = _name;
		pw = _pw;
		pw1 = _pw1;
	}
	public static void register(){
		int flag = check();//flag为 正则状态，0表示正常，1表示用户名不符合要求，2表示密码不符合要求，3表示两次密码不一致，//状态4表示用户名已被注册
		int userNumInDB = 0;
	//	System.out.println(flag);
		if(flag == 0){
			state =true;
			String checkTableNamePW="show tables like \""+USER_TABLE+"\"";  
			String createTableNamePW = "create table "+USER_TABLE+"(id varchar(3),name varchar(10),password varchar(10))"
					+ "CHARACTER SET utf8 COLLATE utf8_general_ci";
			//String countTableNamePW = "select * from "+USER_TABLE;
			try{
				/*step 1 检测数据库用户名密码表是否存在，如果不存在则创建之****************/
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
				stmt = conn.createStatement();
				ResultSet resultSet=stmt.executeQuery(checkTableNamePW); //连接状态
				if (!resultSet.next()) {   
					stmt.executeUpdate(createTableNamePW);
		        }
				stmt.close();
				conn.close();
				/*step 2 获取数据库中已有的数据数量****************/
				userNumInDB = ModifyFlight.getMaxID();
				/*step  3   插入数据****************/
				String line= (userNumInDB+1)+","+"'"+name+"'"+","+"'"+pw+"'";
				String insertTableNamePW = "insert into "+USER_TABLE+"(id,name,password)"
                        + "values("+line+")";
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
				stmt = conn.createStatement();
				stmt.executeUpdate(insertTableNamePW);//执行插入的语句
				stmt.close();
				conn.close();
			}catch(Exception e){
				System.out.println("Wrong");
			}
}		
		else if(flag == 1){
			JOptionPane.showMessageDialog(null, "用户名不符合要求");
			state = false;
		}
		else if(flag == 2){
			JOptionPane.showMessageDialog(null, "密码不符合要求");
			state = false;
		}
		else if(flag == 3){
			JOptionPane.showMessageDialog(null, "两次密码不一致");
			state = false;
		}
		else if(flag == 4){
			JOptionPane.showMessageDialog(null, "该用户名已被注册");
			state = false;
		}
		else{
			JOptionPane.showMessageDialog(null, "状态不对");
			state = false;
		}
	}
	private static int check(){

		int isRight = 0;
		//定义正则表达式
		Pattern name_pattern = Pattern.compile("^[A-Za-z][0-9A-Za-z]{6,10}$");
		Pattern pw_pattern = Pattern.compile("^[0-9a-zA-Z]{8,13}$");
		Matcher m1=name_pattern.matcher(name);
		Matcher m2=pw_pattern.matcher(pw);
		//进行检测
		boolean t1 = m1.matches();
		boolean t2 = m2.matches();
		boolean t3 = pw.equals(pw1);
		boolean t4 = !check2();
		//t4为FALSE表示用户名已存在，t4为TRUE表示用户名不存在
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
		/*打开数据库查看是否数据库中是否已经有用户信息****************/
		try{
			String checkName = "select * from "+USER_TABLE+" where name="+"'"+name+"'";
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
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
