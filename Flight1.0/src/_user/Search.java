package _user;

import java.io.BufferedReader;
import java.io.FileReader;

public class Search {
	private static String path = "files\\flights.txt";
	public static void search(String address1,String address2,String[] retStr,int num){
		String[] tempStr1 = new String[num];
		String[] tempStr2 = new String[num];
		String[] tempStr3= new String[num];
		int top1 = 0,top2 = 0, top3 = 0;
		try{
			BufferedReader file = new BufferedReader(new FileReader(path));
			String[] temp = new String[8];
			String line;
			while((line=file.readLine())!=null){
				if(line.equals("*")){//如果读到*，则保存本行及以下6行
					temp[0] = line;
					for(int i = 1;i <= 7;i++)
						temp[i] = file.readLine();
					if(temp[2].equals(address1)&&temp[3].equals(address2))//如果起点和终点都符合要求则保存
						tempStr1[top1++] = temp[1];
					
					else if(temp[2].equals(address1)&&!(temp[3].equals(address2))){//如果起点符合要求则保存临时变量
						tempStr2[top2++]=temp[1];
					}
					else if(temp[3].equals(address2)&&!(temp[2].equals(address1))){//如果终点符合要求则保存临时变量
						tempStr3[top3++]=temp[1];
					}
					}
				}
			file.close();
		}catch(Exception e){}
		int j = 1;
		//设置标志-标识符合查询要求
		retStr[0]="a";
		for(int i = 0;i<top1;i++)
			retStr[j++] = tempStr1[i];
		//设置标志-标识符合起点
		retStr[j++] = "b";
		for(int i = 0;i<top2;i++)
			retStr[j++] = tempStr2[i];
		//设置标志-标识符合终点
		retStr[j++] = "c";
		for(int i = 0;i<top3;i++)
			retStr[j++] = tempStr3[i];
			
	}
	
}
