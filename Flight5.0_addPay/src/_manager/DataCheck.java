package _manager;
//对用户输入的数据进行检测，符合规范则返回TRUE，否则返回FALSE
//作者：孙加辉，时间：2017/05/07
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
			if( Long.parseLong(str)>999999)
				return false;
			else
				return true;
		}
	}
//	public static void main(String[] args){
//		System.out.println(check(""));
//	}
}
