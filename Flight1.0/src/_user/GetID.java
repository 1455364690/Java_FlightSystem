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
			String line;//ɨ��ϵͳ�ļ������ɨ�赽���û������˳�ɨ��
			while((line=in.readLine())!=null)
				//if(line.equals("<"+userName)){
				if(line.equals(userName)){
					temp = in.readLine();//���û�����һ��"������"��ֵ
					temp = in.readLine();//��id��ֵ��temp;
					break;
				}
			if(line.equals(null))
				temp="-1";
			in.close();
		}catch(Exception e){
			return -1;
		}//����ID
		return (Integer.parseInt(temp));
	}
}
