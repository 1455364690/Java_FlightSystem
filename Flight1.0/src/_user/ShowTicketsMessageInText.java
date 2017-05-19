package _user;

import java.io.BufferedReader;
import java.io.FileReader;

import _manager.ModifyFlight;
public class ShowTicketsMessageInText {
	private static String path = "files\\users.flights.txt";
	public static String showMessage(String name){
		String message = "您已经购买的情况如下\r\n";
		int intID = GetID.get(name);//得到用户的ID
	//	String strID = intID+"";//转化成String类型
	//	int maxNum = ModifyFlight.getMaxNum();//得到最大航班数
		boolean find = false;
		try{
			BufferedReader in = new BufferedReader(new FileReader(path));
			//temp为票的数量，tempStr为一行
			String line,tempNum,tempStr="";
			//System.out.println(intID);
			while((line=in.readLine())!=null){
				tempNum = in.readLine();
			//	System.out.println(line);
				if(line.indexOf("*"+intID)!=-1){//如果包含"*"+intID说明用户已经买票了
					find = true;
					//System.out.println("1."+line);
					line = line.replace("*"+intID+"#", "");//获得航班号
					//System.out.println("2."+line);
					tempStr = line+"号航班"+tempNum+"张票;\r\n";
				}
				message = message + tempStr;
				
			}
			in.close();
		}catch(Exception e){
			//System.out.println("cccc");
		}
		if(!find)
			message = "您没有购买记录";
		
		return message;
	}
}
