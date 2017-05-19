package _user;


import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;
import javax.swing.JOptionPane;
public class Register {
	private static boolean state = false;//返回注册状态，成功与否
	private static String name;
	private static String pw;
	private static String pw1;
	private static int id;//保存用户id
	private static String path = "files//userIdMAX.txt";
	private static String path2 = "files//userNameAndPw.txt";
	//赋初值
	public static void setValue(String _name,String _pw,String _pw1){
		name=_name;
		pw=_pw;
		pw1=_pw1;
	}
	public static void register(){
		int flag = check();
		if(flag==0){//check的状态
			//读取id
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
			//写入用户名密码和id
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
				JOptionPane.showMessageDialog(null,"注册成功");
				state = true;
			}catch(IOException e){
				e.printStackTrace();
			}
			//修改id，使原id+1;
			try{
				PrintWriter idput = new PrintWriter(new FileWriter(path));
				//DataOutputStream idput = new DataOutputStream(new FileOutputStream("idMAX.txt"));
				idput.print(id);
				idput.close();
			}catch(FileNotFoundException fnfe){
				System.out.println("fnfe2");
			}catch(IOException ioe){
				System.out.println("io2");
			}//状态1表示用户名不符合要求
		}else if(flag==1){
			JOptionPane.showMessageDialog(null, "用户名不符合要求");
			state = false;
		}//状态2表示密码不符合要求
		else if(flag==2){
			JOptionPane.showMessageDialog(null, "密码不符合要求");
			state = false;
		}//状态3表示两次密码不一致
		else if(flag==3){
			JOptionPane.showMessageDialog(null, "两次密码不一致");
			state = false;
		}//状态4表示用户名已被注册
		else if(flag==4){
			JOptionPane.showMessageDialog(null, "该用户名已被注册");
			state = false;
		}
		
	}
	//向Register中返回正则状态
	private static int check(){
		int isRight = 0;
		//定义正则表达式
		Pattern name_pattern = Pattern.compile("^[A-Za-z][0-9A-Za-z]{6,10}$");
		Pattern pw_pattern = Pattern.compile("^[0-9a-zA-Z]{8,13}$");
		Matcher m1=name_pattern.matcher(name);
		Matcher m2=pw_pattern.matcher(pw);
		//进行检测
		boolean t1 = m1.matches();
		boolean t2 = m2.matches();
		boolean t3 = pw.equals(pw1);
		boolean t4 = !check2();
		//t4为FALSE表示用户名已存在，t4为TRUE表示用户名不存在
		//System.out.println(check2());
		if(!t1) isRight = 1;
		else if(t1&&!t2) isRight = 2;
		else if(t1&&t2&&!t3) isRight = 3;
		else if(!t4) isRight = 4;
		else if(t1&&t2&&t3&&t4) isRight = 0;
		return isRight;
	}
	//检查用户名是否已存在
	private static boolean check2(){
		boolean isExist = false;//false表示不存在，TRUE表示存在
		try{
			BufferedReader in = new BufferedReader(new FileReader(path2));
			String line;//扫描系统文件，如果扫描到该用户名则退出扫描
			while((line=in.readLine())!=null){
				if(line.equals("<"+name)){//如果扫描到了该用户名则退出
					break;
				}
			}
				in.close();
				//如果line为空表示扫描到了结尾，所以该用户名还未注册
				if(line.equals(null))
					isExist = false;
				else
					isExist = true;
		}catch(Exception e){
			isExist = false;
		}
		
		return isExist;
	}
	//向Register_interface中返回注册状态
	public static boolean getState(){
		return state;
	}
}
