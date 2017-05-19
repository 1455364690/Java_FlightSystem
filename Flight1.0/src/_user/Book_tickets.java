package _user;

import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import _manager.ModifyFlight;

public class Book_tickets {
	private static String path = "files\\users.flights.txt";
	private static String path2 = "files\\flights.txt";
	private static String path3 = "files\\temp.txt";
	private static int intID;
	private static String strID;
	public static int book(String num,String amount,String name){
		intID = GetID.get(name);
		//System.out.print(intID);
		strID = intID +"";
		int isBook = 2;
		int maxTic;int soldTic;//���Ʊ��������Ʊ��
		int buyTic =  Integer.parseInt(amount);
		int intNum =  Integer.parseInt(num);
		if(intNum > ModifyFlight.getMaxNum()){
			isBook = 1;
			return isBook;
		}
		try{//��path2���������path3����д
			BufferedReader in = new BufferedReader(new FileReader(path2));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path3)));
			//lineΪһ���е����ݣ���ʱ����ţ���ʱ��㣬��ʱ�յ㣬��ʱ���Ʊ������ʱ����Ʊ��
			String line,tempNum,temp1,temp2,tempMax,tempSol;
			while((line=in.readLine())!=null){
				if(line.equals("*")){
					tempNum = in.readLine();
					if(tempNum.equals(num)){
						//temp1��ʱ��㣬temp2��ʱ�յ㣬tempMax��ʱ���Ʊ����tempSol��ʱ����Ʊ��
						temp1 = in.readLine();temp2 = in.readLine();tempMax = in.readLine(); 
						tempSol = in.readLine();
						maxTic = Integer.parseInt(tempMax);
						soldTic = Integer.parseInt(tempSol);
						if((maxTic-soldTic) >= buyTic){
							soldTic += buyTic;
							isBook = 0;
						}
						out.write(line+"\r\n");
						out.write(tempNum+"\r\n");
						out.write(temp1+"\r\n");
						out.write(temp2+"\r\n");
						out.write(tempMax+"\r\n");
						out.write(soldTic+"\r\n");
					}//if(tempNum.equals(num))
					else{
						out.write(line+"\r\n");
						out.write(tempNum+"\r\n");
					}//else-end
				}//if(line.equals("*"))
				else
					out.write(line+"\r\n");
			}
			out.flush();
			in.close();
			out.close();
			
			//�׶�2
			////��path3���������path2����д
			BufferedReader in2 = new BufferedReader(new FileReader(path3));
			PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter(path2)));
			String line2;
			while((line2=in2.readLine())!=null){
					out2.write(line2+"\r\n");
			}
			out2.flush();
			in2.close();
			out2.close();
			//ɾ��path3
			File del = new File(path3);
			del.delete();
		}catch(Exception e){}
		//�׶�3
		if(isBook == 0){//�����Ʊ�ɹ�
			try{//��path���������path3����д
				boolean find = false;
				BufferedReader in = new BufferedReader(new FileReader(path));
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path3)));
				//lineΪһ���е����ݣ���ʱ�ѹ�Ʊ��
				String line,temp;
				int inttemp;//��tempת��int�ͱ�������
				while((line=in.readLine())!=null){
					if(line.equals("*"+strID+"#"+num)){
						find = true;//����ҵ��˾��ñ�־
						temp = in.readLine();//���ѹ���Ʊ����ȡ����
						inttemp = Integer.parseInt(temp);//ת����int�ͷ������
						inttemp += buyTic;//���Ϲ��������
						temp = inttemp+"";//ת����String��
						out.write(line+"\r\n");//д��
						out.write(temp+"\r\n");
					}
					else
						out.write(line+"\r\n");
				}
				if(!find){
					out.write("*"+strID+"#"+num+"\r\n");
					out.write(amount+"\r\n");
				}
				out.flush();
				in.close();
				out.close();
				//�׶�4
				////��path3���������path����д
				BufferedReader in2 = new BufferedReader(new FileReader(path3));
				PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter(path)));
				String line2;
				while((line2=in2.readLine())!=null){
						out2.write(line2+"\r\n");
				}
				out2.flush();
				in2.close();
				out2.close();
				//ɾ��path3
				File del = new File(path3);
				del.delete();
			}catch(Exception e){}
		}
		
		return isBook;
	}
}
