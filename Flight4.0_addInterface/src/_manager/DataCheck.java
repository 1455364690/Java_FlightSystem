package _manager;

public class DataCheck {
	public static boolean check(String str){
		boolean isNull = false;
		String str1=str.replaceAll("[0-9]","");//如果输入的值是小数或者其他字符
		String str2=str.replaceAll("0","");//如果用户输入的值全是0
		if(str.equals(null)){
			isNull = true;
		}
		if(!(str1.equals(""))||str2.equals("")||isNull){
			return false;
		}else{
			return true;
		}
	}
//	public static void main(String[] args){
//		System.out.println(check(""));
//	}
}
