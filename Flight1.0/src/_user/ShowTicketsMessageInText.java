package _user;

import java.io.BufferedReader;
import java.io.FileReader;

import _manager.ModifyFlight;
public class ShowTicketsMessageInText {
	private static String path = "files\\users.flights.txt";
	public static String showMessage(String name){
		String message = "���Ѿ�������������\r\n";
		int intID = GetID.get(name);//�õ��û���ID
	//	String strID = intID+"";//ת����String����
	//	int maxNum = ModifyFlight.getMaxNum();//�õ���󺽰���
		boolean find = false;
		try{
			BufferedReader in = new BufferedReader(new FileReader(path));
			//tempΪƱ��������tempStrΪһ��
			String line,tempNum,tempStr="";
			//System.out.println(intID);
			while((line=in.readLine())!=null){
				tempNum = in.readLine();
			//	System.out.println(line);
				if(line.indexOf("*"+intID)!=-1){//�������"*"+intID˵���û��Ѿ���Ʊ��
					find = true;
					//System.out.println("1."+line);
					line = line.replace("*"+intID+"#", "");//��ú����
					//System.out.println("2."+line);
					tempStr = line+"�ź���"+tempNum+"��Ʊ;\r\n";
				}
				message = message + tempStr;
				
			}
			in.close();
		}catch(Exception e){
			//System.out.println("cccc");
		}
		if(!find)
			message = "��û�й����¼";
		
		return message;
	}
}
