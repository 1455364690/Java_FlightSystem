package _manager;
//���û���������ݽ��м�⣬���Ϲ淶�򷵻�TRUE�����򷵻�FALSE
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
public class DataCheck {
	public static boolean check(String str){
		boolean isNull = false;
		String str1=str.replaceAll("[0-9]","");//��������ֵ��С�����������ַ�
		String str2=str.replaceAll("0","");//����û������ֵȫ��0
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
