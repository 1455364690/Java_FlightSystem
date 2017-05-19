package _user;

import java.io.*;

import _manager.ModifyFlight;

public class Back_tickets {
	private static String path = "files\\users.flights.txt";
	private static String path2 = "files\\flights.txt";
	private static String path3 = "files\\temp.txt";
	private static int intID;
	private static String strID;
	public static int back(String num,String amount,String name){
		int isBack = 2;//2表示票数错误，1表示航班号错误
		intID = GetID.get(name);
		strID = intID +"";
		int intAmount =  Integer.parseInt(amount);
		int intNum =  Integer.parseInt(num);
		if(intNum > ModifyFlight.getMaxNum()){
			isBack = 1;
			return isBack;
		}
		boolean find = false;
		//修改用户信息文件
		try{//从path里面读，向path3里面写
			BufferedReader in = new BufferedReader(new FileReader(path));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path3)));
			String line,tempNum;int intTemp;//temp存放已购票数，intTemp表示int型的已购票数
			while((line=in.readLine())!=null){
				if(line.equals("*"+strID+"#"+num)){
					find = true;
					isBack = 0;
					tempNum = in.readLine();
					intTemp = Integer.parseInt(tempNum);
					if(intTemp < intAmount){
						isBack = 2;
					}else{
						intTemp -= intAmount;
					}
					if(intTemp!=0){//如果退票完之后还有剩余信息则写回
						tempNum =intTemp+"";//将修改后的数据转化为String类型
						out.write(line+"\r\n");
						out.write(tempNum+"\r\n");
					}//否则删掉
					
				}
				else out.write(line+"\r\n");
			}
			out.flush();
			in.close();
			out.close();
			//阶段2
			////从path3里面读，向path2里面写
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
		
		
		//修改航班信息文件
		if(find){
			try{//从path2里面读，向path3里面写
				BufferedReader in = new BufferedReader(new FileReader(path2));
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path3)));
				//line为一行行的数据，临时航班号，临时起点，临时终点，临时最大票数，临时已售票数
				String line,tempNum,temp1,temp2,tempMax,tempSol;
				int intSol;
				while((line=in.readLine())!=null){
					if(line.equals("*")){
						tempNum = in.readLine();
						if(tempNum.equals(num)){
							temp1 = in.readLine();temp2 = in.readLine();tempMax = in.readLine(); 
							tempSol = in.readLine();
							intSol = Integer.parseInt(tempSol);
							intSol -= intAmount;
							out.write(line+"\r\n");
							out.write(tempNum+"\r\n");
							out.write(temp1+"\r\n");
							out.write(temp2+"\r\n");
							out.write(tempMax+"\r\n");
							out.write(intSol+"\r\n");
						}else{
							out.write(line+"\r\n");
							out.write(tempNum+"\r\n");
						}
					}
					else out.write(line+"\r\n");
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
		}
		return isBack;
	}
}
