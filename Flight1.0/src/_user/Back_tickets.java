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
		int isBack = 2;//2��ʾƱ������1��ʾ����Ŵ���
		intID = GetID.get(name);
		strID = intID +"";
		int intAmount =  Integer.parseInt(amount);
		int intNum =  Integer.parseInt(num);
		if(intNum > ModifyFlight.getMaxNum()){
			isBack = 1;
			return isBack;
		}
		boolean find = false;
		//�޸��û���Ϣ�ļ�
		try{//��path���������path3����д
			BufferedReader in = new BufferedReader(new FileReader(path));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path3)));
			String line,tempNum;int intTemp;//temp����ѹ�Ʊ����intTemp��ʾint�͵��ѹ�Ʊ��
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
					if(intTemp!=0){//�����Ʊ��֮����ʣ����Ϣ��д��
						tempNum =intTemp+"";//���޸ĺ������ת��ΪString����
						out.write(line+"\r\n");
						out.write(tempNum+"\r\n");
					}//����ɾ��
					
				}
				else out.write(line+"\r\n");
			}
			out.flush();
			in.close();
			out.close();
			//�׶�2
			////��path3���������path2����д
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
		
		
		//�޸ĺ�����Ϣ�ļ�
		if(find){
			try{//��path2���������path3����д
				BufferedReader in = new BufferedReader(new FileReader(path2));
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path3)));
				//lineΪһ���е����ݣ���ʱ����ţ���ʱ��㣬��ʱ�յ㣬��ʱ���Ʊ������ʱ����Ʊ��
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
		}
		return isBack;
	}
}
