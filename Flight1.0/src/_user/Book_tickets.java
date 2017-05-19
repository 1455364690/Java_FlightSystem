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
		int maxTic;int soldTic;//最大票数，已售票数
		int buyTic =  Integer.parseInt(amount);
		int intNum =  Integer.parseInt(num);
		if(intNum > ModifyFlight.getMaxNum()){
			isBook = 1;
			return isBook;
		}
		try{//从path2里面读，向path3里面写
			BufferedReader in = new BufferedReader(new FileReader(path2));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path3)));
			//line为一行行的数据，临时航班号，临时起点，临时终点，临时最大票数，临时已售票数
			String line,tempNum,temp1,temp2,tempMax,tempSol;
			while((line=in.readLine())!=null){
				if(line.equals("*")){
					tempNum = in.readLine();
					if(tempNum.equals(num)){
						//temp1临时起点，temp2临时终点，tempMax临时最大票数，tempSol临时已售票数
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
			
			//阶段2
			////从path3里面读，向path2里面写
			BufferedReader in2 = new BufferedReader(new FileReader(path3));
			PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter(path2)));
			String line2;
			while((line2=in2.readLine())!=null){
					out2.write(line2+"\r\n");
			}
			out2.flush();
			in2.close();
			out2.close();
			//删除path3
			File del = new File(path3);
			del.delete();
		}catch(Exception e){}
		//阶段3
		if(isBook == 0){//如果购票成功
			try{//从path里面读，向path3里面写
				boolean find = false;
				BufferedReader in = new BufferedReader(new FileReader(path));
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path3)));
				//line为一行行的数据，临时已购票数
				String line,temp;
				int inttemp;//将temp转成int型保存起来
				while((line=in.readLine())!=null){
					if(line.equals("*"+strID+"#"+num)){
						find = true;//如果找到了就置标志
						temp = in.readLine();//将已购的票数读取出来
						inttemp = Integer.parseInt(temp);//转化成int型方便操作
						inttemp += buyTic;//加上购买的数量
						temp = inttemp+"";//转化成String型
						out.write(line+"\r\n");//写回
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
				//阶段4
				////从path3里面读，向path里面写
				BufferedReader in2 = new BufferedReader(new FileReader(path3));
				PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter(path)));
				String line2;
				while((line2=in2.readLine())!=null){
						out2.write(line2+"\r\n");
				}
				out2.flush();
				in2.close();
				out2.close();
				//删除path3
				File del = new File(path3);
				del.delete();
			}catch(Exception e){}
		}
		
		return isBook;
	}
}
