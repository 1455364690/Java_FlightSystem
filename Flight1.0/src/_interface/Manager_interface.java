package _interface;

import javax.swing.JOptionPane;

public class Manager_interface {
	private static final String systemN="root";
	private static final String systemP="root";
	private static String name;
	private static String password;
	private static int time = 3 ;//输入次数上限
//	public static void setVal(String _name,String _password){
//		name = _name;
//		password = _password;
//	}
	public static void managerLogin(){
		if(time>0){
			//通过对话框输入信息
			name = JOptionPane.showInputDialog("请输入管理员用户名");
			password = JOptionPane.showInputDialog("请输入管理员密码");
			if(check()){//如果检测通过则调用新建对象
				Modify_interface _modify = new Modify_interface();
			}else{
				time--;//否则次数减一
				JOptionPane.showMessageDialog(null, "输入有误!!,还剩下"+time+"次机会");
			}
		}else{//如果次数用光则不可以再登录
			JOptionPane.showMessageDialog(null, "输入次数已达上限!!");
		}
	}
	public static boolean check(){
		if(name.equals(systemN)&&password.equals(systemP))
			return true;
		else
			return false;
	}
}
