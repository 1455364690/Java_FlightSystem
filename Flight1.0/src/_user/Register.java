package _user;


import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;
import javax.swing.JOptionPane;
public class Register {
	private static boolean state = false;//����ע��״̬���ɹ����
	private static String name;
	private static String pw;
	private static String pw1;
	private static int id;//�����û�id
	private static String path = "files//userIdMAX.txt";
	private static String path2 = "files//userNameAndPw.txt";
	//����ֵ
	public static void setValue(String _name,String _pw,String _pw1){
		name=_name;
		pw=_pw;
		pw1=_pw1;
	}
	public static void register(){
		int flag = check();
		if(flag==0){//check��״̬
			//��ȡid
			try{
				BufferedReader idget = new BufferedReader(new FileReader(path));
				//DataInputStream idget = new DataInputStream(new FileInputStream("idMAX.txt"));
				id = Integer.parseInt(idget.readLine());
				System.out.println(id);
				idget.close();
			}catch(FileNotFoundException fnfe){
				System.out.println("fnfe1");
			}catch(IOException ioe){
				System.out.println("ioe1");
			}
			//д���û��������id
			try{
				RandomAccessFile randomFile = new RandomAccessFile(path2, "rw");
				 randomFile.seek(randomFile.length());
				 randomFile.writeBytes("\r\n<"+name);
				 randomFile.seek(randomFile.length());
				 randomFile.writeBytes("\r\n>"+pw);
				 randomFile.seek(randomFile.length());
				 randomFile.writeBytes("\r\n"+id);
				 randomFile.close();
				 id++;
				JOptionPane.showMessageDialog(null,"ע��ɹ�");
				state = true;
			}catch(IOException e){
				e.printStackTrace();
			}
			//�޸�id��ʹԭid+1;
			try{
				PrintWriter idput = new PrintWriter(new FileWriter(path));
				//DataOutputStream idput = new DataOutputStream(new FileOutputStream("idMAX.txt"));
				idput.print(id);
				idput.close();
			}catch(FileNotFoundException fnfe){
				System.out.println("fnfe2");
			}catch(IOException ioe){
				System.out.println("io2");
			}//״̬1��ʾ�û���������Ҫ��
		}else if(flag==1){
			JOptionPane.showMessageDialog(null, "�û���������Ҫ��");
			state = false;
		}//״̬2��ʾ���벻����Ҫ��
		else if(flag==2){
			JOptionPane.showMessageDialog(null, "���벻����Ҫ��");
			state = false;
		}//״̬3��ʾ�������벻һ��
		else if(flag==3){
			JOptionPane.showMessageDialog(null, "�������벻һ��");
			state = false;
		}//״̬4��ʾ�û����ѱ�ע��
		else if(flag==4){
			JOptionPane.showMessageDialog(null, "���û����ѱ�ע��");
			state = false;
		}
		
	}
	//��Register�з�������״̬
	private static int check(){
		int isRight = 0;
		//����������ʽ
		Pattern name_pattern = Pattern.compile("^[A-Za-z][0-9A-Za-z]{6,10}$");
		Pattern pw_pattern = Pattern.compile("^[0-9a-zA-Z]{8,13}$");
		Matcher m1=name_pattern.matcher(name);
		Matcher m2=pw_pattern.matcher(pw);
		//���м��
		boolean t1 = m1.matches();
		boolean t2 = m2.matches();
		boolean t3 = pw.equals(pw1);
		boolean t4 = !check2();
		//t4ΪFALSE��ʾ�û����Ѵ��ڣ�t4ΪTRUE��ʾ�û���������
		//System.out.println(check2());
		if(!t1) isRight = 1;
		else if(t1&&!t2) isRight = 2;
		else if(t1&&t2&&!t3) isRight = 3;
		else if(!t4) isRight = 4;
		else if(t1&&t2&&t3&&t4) isRight = 0;
		return isRight;
	}
	//����û����Ƿ��Ѵ���
	private static boolean check2(){
		boolean isExist = false;//false��ʾ�����ڣ�TRUE��ʾ����
		try{
			BufferedReader in = new BufferedReader(new FileReader(path2));
			String line;//ɨ��ϵͳ�ļ������ɨ�赽���û������˳�ɨ��
			while((line=in.readLine())!=null){
				if(line.equals("<"+name)){//���ɨ�赽�˸��û������˳�
					break;
				}
			}
				in.close();
				//���lineΪ�ձ�ʾɨ�赽�˽�β�����Ը��û�����δע��
				if(line.equals(null))
					isExist = false;
				else
					isExist = true;
		}catch(Exception e){
			isExist = false;
		}
		
		return isExist;
	}
	//��Register_interface�з���ע��״̬
	public static boolean getState(){
		return state;
	}
}
