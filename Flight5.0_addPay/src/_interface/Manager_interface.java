package _interface;
//���ܣ�����Ա��¼����
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import javax.swing.JOptionPane;
import _manager.ManagerInfo;

public class Manager_interface extends ManagerInfo{
	private static String name = " ";
	private static String password = " ";
	private static int time = 3 ;//�����������
	public static void managerLogin(){
		if(time>0){
			//ͨ���Ի���������Ϣ
			name = JOptionPane.showInputDialog("���������Ա�û���");
			if(name==null) 
				return;
			password = JOptionPane.showInputDialog("���������Ա����");
			if(password==null) 
				return;		
			if(check()){//������ͨ��������½�����
				Modify_interface _modify = new Modify_interface();
				_modify.setEnabled(true);
			}else{
				time--;//���������һ
				JOptionPane.showMessageDialog(null, "��������!!,��ʣ��"+time+"�λ���");
			}
		}else{//��������ù��򲻿����ٵ�¼
			JOptionPane.showMessageDialog(null, "��������Ѵ�����!!");
		}
	}
	public static boolean check(){
		if(name.equals("")||password.equals(null))
			return false;
		if(name.equals(systemN)&&password.equals(systemP))
			return true;
		else
			return false;
	}
}
