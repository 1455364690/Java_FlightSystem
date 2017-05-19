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
	private static String userName;//用户输入的用户名
	private static String passWord;//用户输入的密码
	private static String userPW;//从数据库中读出的密码
	private static int state = 3;//状态0表示用户订票，状态1表示退票，2表示修改票
	public static void login(int _state){
		//弹出输入对话框，用户输入用户名
		String queryStr = "select * from "+USER_TABLE;
		state = _state;
		userName =JOptionPane.showInputDialog("请输入用户名");
		boolean findName = false;
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
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
				//如果未扫描到则提示用户是否注册
				int flag;
				flag=JOptionPane.showConfirmDialog(null,"没有该用户名，是否注册", "注册？",JOptionPane.YES_NO_OPTION);
				if(flag==0)//如果用户选择注册则调用注册方法
				{
					Register_interface	reg = new Register_interface();
				}
			}
			stmt.close();
			conn.close();
		}catch(Exception e){}
	}
	public static void check(){
		passWord =JOptionPane.showInputDialog("请输入密码");
		//判断密码是否正确
			if(userPW.equals(passWord))
			{//状态0表示订票，1表示退票，2表示修改
				if(state == 0){
					Book_interface book = new Book_interface(userName);
				}else if(state == 1){
					Back_interface back = new Back_interface(userName);
				}else if(state == 2){
					Modi_tick_interface modi = new Modi_tick_interface(userName);
				}
			}else
			{
				int flag;//如果密码错误，则用户选择是否重新输入
				flag=JOptionPane.showConfirmDialog(null,"密码输入有误,是否重新输入", "重新输入？",JOptionPane.YES_NO_OPTION);
				if(flag==0)
					check();
			}
	}
}
