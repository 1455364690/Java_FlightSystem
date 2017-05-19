package _user;
import _interface.Register_interface;
import _interface.Book_interface;
import _interface.Back_interface;
import _interface.Modi_tick_interface;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import javax.swing.JOptionPane; 
public class Login {
	private static String userName;
	private static String userPW;
	private static String passWord;
	private static String path = "files//userNameAndPW.txt";
	private static int state = 3;
	public static void login(int _state){
		//��������Ի����û������û���
		state = _state;
		userName ="<"+JOptionPane.showInputDialog("�������û���");
		try{
			BufferedReader in = new BufferedReader(new FileReader(path));
			String line;//ɨ��ϵͳ�ļ������ɨ�赽���û������˳�ɨ��
			while((line=in.readLine())!=null)
				if(line.equals(userName)){
					userPW = in.readLine();//���û�����һ��"������"��ֵ
					break;
				}
			in.close();
			//���ɨ�赽�������check����
			if(!line.equals(null))
				check();
		}catch(Exception e){
			//���δɨ�赽����ʾ�û��Ƿ�ע��
			int flag;
			flag=JOptionPane.showConfirmDialog(null,"û�и��û������Ƿ�ע��", "ע�᣿",JOptionPane.YES_NO_OPTION);
			if(flag==0)//����û�ѡ��ע�������ע�᷽��
			{
				Register_interface	reg = new Register_interface();
			}
			//JOptionPane.showMessageDialog(null, "�û�����������");
			//JOptionPane.showConfirmDialog(null, e.toString());
		}
	}
	public static void check(){
		passWord =">"+JOptionPane.showInputDialog("����������");
		//�ж������Ƿ���ȷ
			if(userPW.equals(passWord))
			{//״̬0��ʾ��Ʊ��1��ʾ��Ʊ��2��ʾ�޸�
				if(state == 0){
					Book_interface book = new Book_interface(userName);
				}else if(state == 1){
					Back_interface back = new Back_interface(userName);
				}else if(state == 2){
					Modi_tick_interface modi = new Modi_tick_interface(userName);
				}
			}
			else
			{
				int flag;//�������������û�ѡ���Ƿ���������
				flag=JOptionPane.showConfirmDialog(null,"������������,�Ƿ���������", "�������룿",JOptionPane.YES_NO_OPTION);
				if(flag==0)
					check();
			}
		
	}
}
