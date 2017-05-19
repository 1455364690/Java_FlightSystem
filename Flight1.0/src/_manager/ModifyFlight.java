package _manager;

import javax.swing.JOptionPane;
import java.io.*;
//功能：管理员增删改的功能，获得当期已有航班的数量
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
		boolean find = false;//检查是否找到要删除的序号
		//将源文件修改后放在临时文件中
		try{
			BufferedReader file = new BufferedReader(new FileReader(path));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("1.txt")));
			String line;
			while((line=file.readLine())!=null){
				if(!find&&line.equals("*")){//如果没找到，则寻找之
					tempNum = file.readLine();
					if(Integer.parseInt(tempNum) == num){	
						String newline=line.replace('*','#');//将找到的*改成#
						line = newline;
						find = true;
					}
					out.write(line+"\r\n");
					out.write(tempNum+"\r\n");//其他的信息保持不变
				}//if-end
				else if(find){//如果已经找到了
					if(line.equals("*")){
						tempNum = file.readLine();
						out.write(line+"\r\n");
						int outNum = Integer.parseInt(tempNum)-1;//则将其后面的序号减一
						out.write(outNum+"\r\n");
					}
					else
						out.write(line+"\r\n");//其他保持不变
				}
				else
					out.write(line+"\r\n");
			}//while-end
			out.flush();
			if(find) JOptionPane.showMessageDialog(null,"删除成功");
			else JOptionPane.showMessageDialog(null,"删除失败");
			//if(line.equals(null))
				//JOptionPane.showMessageDialog(null,"没有此航班");
			file.close();
			out.close();
		}catch(Exception e){
		}
		//将临时文件保存到源文件中
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
		//修改ID
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
			 JOptionPane.showMessageDialog(null,"添加成功");
			 randomFile.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		//修改id，使原id+1;
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
			//从path中读出，写入到path2中
			BufferedReader in = new BufferedReader(new FileReader(path));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path2)));
			String line;
			String[] temp = new String[8];
			while((line=in.readLine())!=null){
				if(line.equals("*")){//如果读到*，则保存本行及以下6行
					temp[0] = line;
					for(int i = 1;i <= 7;i++)
						temp[i] = in.readLine();
					if(temp[1].equals(num)){//如果读到的航班号和需要修改的航班号相同，则保存需要修改的数据
						flag = true ;
						out.write("*"+"\r\n");
						out.write(num+"\r\n");
						out.write(_address1+"\r\n");
						out.write(_address2+"\r\n");
						out.write(_total_tickets+"\r\n");
						out.write(_sold_tickets+"\r\n");
						out.write(_date+"\r\n");
						out.write(_time+"\r\n");
					}else{//如果航班号不一致，则写回原数据
						for(int i = 0;i <= 7;i++)
							out.write(temp[i]+"\r\n");
					}
				}else//如果不是*则直接写回
				    out.write(line+"\r\n");
				
				
			}//while-end
			//弹出窗口反馈信息
			if(flag) JOptionPane.showMessageDialog(null,"修改成功");
			else JOptionPane.showMessageDialog(null,"没有此航班，修改失败");
			out.flush();
			out.close();
			in.close();
			
		}catch(Exception e){
		}
		try{
			//从path2中读出，写入到path中
			BufferedReader in = new BufferedReader(new FileReader(path2));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			String line;
			while((line=in.readLine())!=null){
				out.write(line+"\r\n");
			}//while-end
			out.flush();
			out.close();
			in.close();
			//删除临时文件
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
			JOptionPane.showMessageDialog(null,"没有航班");
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
			JOptionPane.showMessageDialog(null,"没有该用户");
		}
		return Maxnum;
	}
}
