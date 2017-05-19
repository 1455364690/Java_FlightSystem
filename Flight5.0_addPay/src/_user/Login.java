package _user;
//实现用户的登录功能，查询数据库信息
//作者：孙加辉，时间：2017/05/07
import _interface.Book_interface;
import _interface.Back_interface;
import _interface.Modi_tick_interface;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.JOptionPane; 
import _manager.ManagerInfo;

public class Login extends ManagerInfo{
	private static String userName;//用户输入的用户名
	private static String passWord;//用户输入的密码
	private static String userPW;//从数据库中读出的密码
	private static int state = 3;//状态0表示用户订票，状态1表示退票，2表示修改票
	private static boolean isRight = true;
	public static boolean login(int _state,String _name,String _pw){
		//弹出输入对话框，用户输入用户名
		String queryStr = "select * from "+USER_TABLE;
		state = _state;
		userName = _name;
		passWord = _pw;
		boolean findName = false;
		try{//查找是否有对应的用户名，如果找到，则将其密码取出
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());//加载驱动
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);//建立连接
			stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(queryStr);
			while(res.next()){
				if(res.getString("name").equals(userName)){
					findName = true;
					userPW = res.getString("password");
					//调用方法
					check();
					break;
				}
			}
			if(!findName){
				//如果未扫描到则提示用户是否注册
				JOptionPane.showMessageDialog(null, "请检查用户名是否输入错误!");
			}
			stmt.close();
			conn.close();
		}catch(Exception e){
			System.out.println("Wrong");
		}
		return isRight;
	}
	public static void check(){
		//判断密码是否正确
			if(userPW.equals(passWord))
			{//状态0表示订票，1表示退票，2表示修改
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
				JOptionPane.showMessageDialog(null, "密码错误");
				isRight = false;
			}
	}
}
