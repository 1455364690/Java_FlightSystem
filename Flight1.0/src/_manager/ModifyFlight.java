package _manager;

import javax.swing.JOptionPane;
import java.io.*;
//���ܣ�����Ա��ɾ�ĵĹ��ܣ���õ������к��������
public class ModifyFlight {
//	private static int number;
//	private static String address1;
//	private static String address2;
//	private static int total_tickets;
//	private static int sold_tickets;
//	private static String date;
//	private static String time;
	private static String path = "files\\flights.txt";
	private static String path2 = "files\\temp.txt";
	private static String path3 = "files\\flightIdMAX.txt";
	private static String path4 = "files\\userIdMAX.txt";
	public static void delFlight(int num){
		String tempNum;
		int MaxNum;
		boolean find = false;//����Ƿ��ҵ�Ҫɾ�������
		//��Դ�ļ��޸ĺ������ʱ�ļ���
		try{
			BufferedReader file = new BufferedReader(new FileReader(path));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("1.txt")));
			String line;
			while((line=file.readLine())!=null){
				if(!find&&line.equals("*")){//���û�ҵ�����Ѱ��֮
					tempNum = file.readLine();
					if(Integer.parseInt(tempNum) == num){	
						String newline=line.replace('*','#');//���ҵ���*�ĳ�#
						line = newline;
						find = true;
					}
					out.write(line+"\r\n");
					out.write(tempNum+"\r\n");//��������Ϣ���ֲ���
				}//if-end
				else if(find){//����Ѿ��ҵ���
					if(line.equals("*")){
						tempNum = file.readLine();
						out.write(line+"\r\n");
						int outNum = Integer.parseInt(tempNum)-1;//����������ż�һ
						out.write(outNum+"\r\n");
					}
					else
						out.write(line+"\r\n");//�������ֲ���
				}
				else
					out.write(line+"\r\n");
			}//while-end
			out.flush();
			if(find) JOptionPane.showMessageDialog(null,"ɾ���ɹ�");
			else JOptionPane.showMessageDialog(null,"ɾ��ʧ��");
			//if(line.equals(null))
				//JOptionPane.showMessageDialog(null,"û�д˺���");
			file.close();
			out.close();
		}catch(Exception e){
		}
		//����ʱ�ļ����浽Դ�ļ���
		try{
			BufferedReader in = new BufferedReader(new FileReader(path2));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			String line;
			while((line=in.readLine())!=null){
				if(line.equals("#")){
					for(int i = 0;i<=6;i++){
						line=in.readLine();
					}
				}
				else
					out.write(line+"\r\n");
			}//while-end
			out.flush();
			out.close();
			in.close();
			File del = new File(path2);
			del.delete();
		}catch(Exception e){
		}
		//�޸�ID
		if(find){
			MaxNum = getMaxNum();
			try{
				PrintWriter idput = new PrintWriter(new FileWriter(path3));
				idput.print(MaxNum);
				idput.close();
			}catch(FileNotFoundException fnfe){
				System.out.println("fnfe2");
			}catch(IOException ioe){
				System.out.println("io2");
			}
		}
		
	}
	public static void addFlight(String _address1,String _address2,
								String _total_tickets,String _sold_tickets,
								String _date,String _time){
		int maxNum = getMaxNum();
		try{
			RandomAccessFile randomFile = new RandomAccessFile(path, "rw");
			 randomFile.seek(randomFile.length());
			 randomFile.writeBytes("\r\n"+"*");
			 randomFile.seek(randomFile.length());
			 randomFile.writeBytes("\r\n"+(maxNum+1)+"\r\n");
			 randomFile.seek(randomFile.length());
			 randomFile.write(_address1.getBytes());
			 randomFile.writeBytes("\r\n");
			 randomFile.seek(randomFile.length());
			 randomFile.write(_address2.getBytes());
			 randomFile.seek(randomFile.length());
			 randomFile.writeBytes("\r\n"+_total_tickets);
			 randomFile.seek(randomFile.length());
			 randomFile.writeBytes("\r\n"+_sold_tickets);
			 randomFile.seek(randomFile.length());
			 randomFile.writeBytes("\r\n"+_date);
			 randomFile.seek(randomFile.length());
			 randomFile.writeBytes("\r\n"+_time);
			 JOptionPane.showMessageDialog(null,"��ӳɹ�");
			 randomFile.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		//�޸�id��ʹԭid+1;
		try{
			PrintWriter idput = new PrintWriter(new FileWriter(path3));
			idput.print(maxNum+2);
			idput.close();
		}catch(FileNotFoundException fnfe){
			System.out.println("fnfe2");
		}catch(IOException ioe){
			System.out.println("io2");
		}
		
	}
	public static void modiFlight(String num,String _address1,String _address2,
								  String _total_tickets,String _sold_tickets,
							   	  String _date,String _time)
	{
		boolean flag = false;
		try{
			//��path�ж�����д�뵽path2��
			BufferedReader in = new BufferedReader(new FileReader(path));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path2)));
			String line;
			String[] temp = new String[8];
			while((line=in.readLine())!=null){
				if(line.equals("*")){//�������*���򱣴汾�м�����6��
					temp[0] = line;
					for(int i = 1;i <= 7;i++)
						temp[i] = in.readLine();
					if(temp[1].equals(num)){//��������ĺ���ź���Ҫ�޸ĵĺ������ͬ���򱣴���Ҫ�޸ĵ�����
						flag = true ;
						out.write("*"+"\r\n");
						out.write(num+"\r\n");
						out.write(_address1+"\r\n");
						out.write(_address2+"\r\n");
						out.write(_total_tickets+"\r\n");
						out.write(_sold_tickets+"\r\n");
						out.write(_date+"\r\n");
						out.write(_time+"\r\n");
					}else{//�������Ų�һ�£���д��ԭ����
						for(int i = 0;i <= 7;i++)
							out.write(temp[i]+"\r\n");
					}
				}else//�������*��ֱ��д��
				    out.write(line+"\r\n");
				
				
			}//while-end
			//�������ڷ�����Ϣ
			if(flag) JOptionPane.showMessageDialog(null,"�޸ĳɹ�");
			else JOptionPane.showMessageDialog(null,"û�д˺��࣬�޸�ʧ��");
			out.flush();
			out.close();
			in.close();
			
		}catch(Exception e){
		}
		try{
			//��path2�ж�����д�뵽path��
			BufferedReader in = new BufferedReader(new FileReader(path2));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			String line;
			while((line=in.readLine())!=null){
				out.write(line+"\r\n");
			}//while-end
			out.flush();
			out.close();
			in.close();
			//ɾ����ʱ�ļ�
			File del = new File(path2);
			del.delete();
		}catch(Exception e){
		}
	}
	public static int getMaxNum(){
		int Maxnum = 0;
		try{
			BufferedReader idget = new BufferedReader(new FileReader(path3));
			Maxnum = Integer.parseInt(idget.readLine())-1;
			idget.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"û�к���");
		}
		return Maxnum;
	}
	public static int getMaxID(){
		int Maxnum = 0;
		try{
			BufferedReader idget = new BufferedReader(new FileReader(path4));
			Maxnum = Integer.parseInt(idget.readLine())-1;
			idget.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"û�и��û�");
		}
		return Maxnum;
	}
}
