package _user;

public class RemNameAndRemPw {
	public static String name = "";
	public static String password = "";
	public static boolean remName = false;
	public static boolean remPw = false;
	public static void set(boolean rem1,boolean rem2,String _name,String _password){
		remName = rem1;
		remPw = rem2;
		if(rem1){
			name = _name;
			if(rem2)
				password = _password;
		}
			
	}
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
