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
				if(line.equals("*")){//�������*���򱣴汾�м�����6��
					temp[0] = line;
					for(int i = 1;i <= 7;i++)
						temp[i] = file.readLine();
					if(temp[2].equals(address1)&&temp[3].equals(address2))//��������յ㶼����Ҫ���򱣴�
						tempStr1[top1++] = temp[1];
					
					else if(temp[2].equals(address1)&&!(temp[3].equals(address2))){//���������Ҫ���򱣴���ʱ����
						tempStr2[top2++]=temp[1];
					}
					else if(temp[3].equals(address2)&&!(temp[2].equals(address1))){//����յ����Ҫ���򱣴���ʱ����
						tempStr3[top3++]=temp[1];
					}
					}
				}
			file.close();
		}catch(Exception e){}
		int j = 1;
		//���ñ�־-��ʶ���ϲ�ѯҪ��
		retStr[0]="a";
		for(int i = 0;i<top1;i++)
			retStr[j++] = tempStr1[i];
		//���ñ�־-��ʶ�������
		retStr[j++] = "b";
		for(int i = 0;i<top2;i++)
			retStr[j++] = tempStr2[i];
		//���ñ�־-��ʶ�����յ�
		retStr[j++] = "c";
		for(int i = 0;i<top3;i++)
			retStr[j++] = tempStr3[i];
			
	}
	
}
