package _interface;

import javax.swing.JOptionPane;

public class Manager_interface {
	private static final String systemN="root";
	private static final String systemP="root";
	private static String name;
	private static String password;
	private static int time = 3 ;//�����������
//	public static void setVal(String _name,String _password){
//		name = _name;
//		password = _password;
//	}
	public static void managerLogin(){
		if(time>0){
			//ͨ���Ի���������Ϣ
			name = JOptionPane.showInputDialog("���������Ա�û���");
			password = JOptionPane.showInputDialog("���������Ա����");
			if(check()){//������ͨ��������½�����
				Modify_interface _modify = new Modify_interface();
			}else{
				time--;//���������һ
				JOptionPane.showMessageDialog(null, "��������!!,��ʣ��"+time+"�λ���");
			}
		}else{//��������ù��򲻿����ٵ�¼
			JOptionPane.showMessageDialog(null, "��������Ѵ�����!!");
		}
	}
	public static boolean check(){
		if(name.equals(systemN)&&password.equals(systemP))
			return true;
		else
			return false;
	}
}
