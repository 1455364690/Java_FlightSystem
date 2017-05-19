package _interface;
//功能：显示航班信息，根据输入的信息查询航班的信息的窗口
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import _flight.List;
import _manager.ModifyFlight;
import _user.Search;
public class Query_interface extends JFrame implements ActionListener{
	private JTextArea text = new JTextArea(10,60);
	private JScrollPane textroll = new JScrollPane(text);
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp2_1 = new JPanel();
	private JPanel jp2_2 = new JPanel();
	private JLabel jl1 = new JLabel("起始地址:");
	private JLabel jl2 = new JLabel("终点地址:");
	private JTextField jt1 = new JTextField(20);
	private JTextField jt2 = new JTextField(20);
	private JButton jb1 = new JButton("取消");
	private JButton jb2 = new JButton("查询");
	public Query_interface(){
		super("查询订票信息");
		setLayout(new GridLayout(2,1));
		add(jp1);add(jp2);
		jp1.add(textroll);
//		textroll.setVerticalScrollBarPolicy( 
//				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		textroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textroll.setBounds(20,20,100,200);

		text.setLineWrap(true); 
		text.setEditable(false); 
		showlist();
		jp2.add(jp2_1);jp2.add(jp2_2);
		jp2_1.add(jl1);jp2_1.add(jt1);jp2_1.add(jl2);jp2_1.add(jt2);
		jp2_2.add(jb1);jp2_2.add(jb2);
		jb1.addActionListener(this);jb2.addActionListener(this);
		setSize(700,400);
		setVisible(true);
		this.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						dispose();
					}
				});
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb1)
			dispose();
		else{
			String[] num = new String[ModifyFlight.getMaxNum()+3];//存放返回信息
			String[] temp = new String[7];//存放临时航班信息
			Search.search(jt1.getText(), jt2.getText(),num,ModifyFlight.getMaxNum()+3);
			text.setText("航班号\t起点\t终点\t总票数\t卖出的票数\t起飞时间\t飞行时间\r\n");
			int i = 0;
			while(num[i]!=null){//如果不空则一直循环
				if(num[i].equals("a")){
					text.append("符合要求的有:\r\n");
					i++;
				}
				else if(num[i].equals("b")){
					text.append("起点符合要求的有:\r\n");
					i++;
				}
				else if(num[i].equals("c")){
					text.append("终点符合要求的有:\r\n");
					i++;
				}
				else{//输出航班信息
					List.list(Integer.parseInt(num[i]), temp);
					i++;
					text.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"小时\r\n");
				}
			}//while-end
		}//else-end
	}
	//显示航班信息
	public void showlist(){
		text.setText("航班号\t起点\t终点\t总票数\t卖出的票数\t起飞时间\t飞行时间\r\n");
		
		for(int i=1;i<=ModifyFlight.getMaxNum();i++){
			String[] temp = new String[7];
			List.list(i, temp);
			text.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"小时\r\n");
		}
	}
}

