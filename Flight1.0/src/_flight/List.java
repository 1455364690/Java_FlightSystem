package _flight;
import java.io.*;
import javax.swing.JOptionPane;  
public class List {
	private static String path = "files\\flights.txt";
	public static void list(int num,String[] li){
		String tempNum;
		try{
			BufferedReader file = new BufferedReader(new FileReader(path));
			String line;
			while((line=file.readLine())!=null){
				if(line.equals("*")){
					tempNum = file.readLine();
					if(Integer.parseInt(tempNum) == num){
						li[0]=tempNum;
						li[1]=file.readLine();
						li[2]=(file.readLine());
						li[3]=file.readLine();
						li[4]=file.readLine();
						li[5]=file.readLine();
						li[6]=file.readLine();
						break;
					}
				}//if-end
			}//while-end
			if(line.equals(null))
				//JOptionPane.showMessageDialog(null,"没有此航班");
			file.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"没有此航班");
		}
	}
}
