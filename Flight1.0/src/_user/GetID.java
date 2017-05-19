package _user;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JOptionPane;

public class GetID {
	private static String path = "files//userNameAndPW.txt";
	public static int get(String userName){
		String temp="";
		try{
			BufferedReader in = new BufferedReader(new FileReader(path));
			String line;//扫描系统文件，如果扫描到该用户名则退出扫描
			while((line=in.readLine())!=null)
				//if(line.equals("<"+userName)){
				if(line.equals(userName)){
					temp = in.readLine();//把用户名下一行"即密码"赋值
					temp = in.readLine();//把id赋值给temp;
					break;
				}
			if(line.equals(null))
				temp="-1";
			in.close();
		}catch(Exception e){
			return -1;
		}//返回ID
		return (Integer.parseInt(temp));
	}
}
