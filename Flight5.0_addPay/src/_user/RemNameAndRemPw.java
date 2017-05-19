package _user;
//实现记住用户名和密码的功能
//作者：孙加辉，时间：20170507
public class RemNameAndRemPw {
	public static String name = "";
	public static String password = "";
	public static boolean remName = false;
	public static boolean remPw = false;
	public static void set(boolean rem1,boolean rem2,String _name,String _password){
		remName = rem1;
		remPw = rem2;
		//传进参数
		if(rem1){
			name = _name;
			if(rem2)
				password = _password;
		}
			
	}
	//传出参数
	public static void get(String[] res){
		if(remName)
			res[0] = name;
		else
			res[0] = "";
		if(remPw)
			res[1] = password;
		else
			res[1] = "";
	}
}
