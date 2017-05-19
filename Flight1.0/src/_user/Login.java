package _user;
import _interface.Register_interface;
import _interface.Book_interface;
import _interface.Back_interface;
import _interface.Modi_tick_interface;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import javax.swing.JOptionPane; 
public class Login {
	private static String userName;
	private static String userPW;
	private static String passWord;
	private static String path = "files//userNameAndPW.txt";
	private static int state = 3;
	public static void login(int _state){
		//弹出输入对话框，用户输入用户名
		state = _state;
		userName ="<"+JOptionPane.showInputDialog("请输入用户名");
		try{
			BufferedReader in = new BufferedReader(new FileReader(path));
			String line;//扫描系统文件，如果扫描到该用户名则退出扫描
			while((line=in.readLine())!=null)
				if(line.equals(userName)){
					userPW = in.readLine();//把用户名下一行"即密码"赋值
					break;
				}
			in.close();
			//如果扫描到了则调用check函数
			if(!line.equals(null))
				check();
		}catch(Exception e){
			//如果未扫描到则提示用户是否注册
			int flag;
			flag=JOptionPane.showConfirmDialog(null,"没有该用户名，是否注册", "注册？",JOptionPane.YES_NO_OPTION);
			if(flag==0)//如果用户选择注册则调用注册方法
			{
				Register_interface	reg = new Register_interface();
			}
			//JOptionPane.showMessageDialog(null, "用户名输入有误");
			//JOptionPane.showConfirmDialog(null, e.toString());
		}
	}
	public static void check(){
		passWord =">"+JOptionPane.showInputDialog("请输入密码");
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
			}
			else
			{
				int flag;//如果密码错误，则用户选择是否重新输入
				flag=JOptionPane.showConfirmDialog(null,"密码输入有误,是否重新输入", "重新输入？",JOptionPane.YES_NO_OPTION);
				if(flag==0)
					check();
			}
		
	}
}
