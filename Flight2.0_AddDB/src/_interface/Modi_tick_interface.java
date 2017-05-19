package _interface;
//功能：显示航班信息，由用户输入订票信息，反馈用户
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import _user.Back_tickets;
import _user.Book_tickets;
import _user.ShowTicketsMessageInText;
import _flight.List;
import _manager.ModifyFlight;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Modi_tick_interface extends JFrame implements ActionListener{
	private static String userName;
	private JTextArea text1 = new JTextArea(10,60);
	private JTextArea text2 = new JTextArea(10,40);
	private JScrollPane textroll1 = new JScrollPane(text1);
	private JScrollPane textroll2 = new JScrollPane(text2);
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JPanel jp5 = new JPanel();
	private JLabel jl1 = new JLabel("修改的航班号:");
	private JLabel jl2 = new JLabel("要改成的航班号:");
	private JLabel jl3 = new JLabel("改票数:");
	private JTextField jt1 = new JTextField(5);
	private JTextField jt2 = new JTextField(5);
	private JTextField jt3 = new JTextField(5);
	private JButton jb1 = new JButton("取消");
	private JButton jb2 = new JButton("修改");
	public Modi_tick_interface(String _name){
		super("修改订票信息");
		showlist();
		userName = _name;//将用户名传进来，带<
		ShowTicketsMessageInText.showMessage(userName);
		setLayout(new GridLayout(3,1));
		add(jp1);add(jp2);add(jp3);
		jp1.add(textroll1);jp2.add(textroll2);
		//设置第一个滚动条的属性
		textroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textroll1.setBounds(20,20,100,200);
		text1.setLineWrap(true); 
		text1.setEditable(false); 
		//设置第二个滚动条的属性
		textroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textroll2.setBounds(20,20,100,200);
		text2.setLineWrap(true); 
		text2.setEditable(false); 
		jp3.setLayout(new GridLayout(2,1));
		jp3.add(jp4);jp3.add(jp5);
		jp4.add(jl1);jp4.add(jt1);jp4.add(jl2);jp4.add(jt2);jp4.add(jl3);jp4.add(jt3);
		jp5.add(jb1);jp5.add(jb2);
		jb1.addActionListener(this);jb2.addActionListener(this);
		setSize(700,600);
		setVisible(true);
		text2.setText(ShowTicketsMessageInText.showMessage(userName)+"\r\n");
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb1)
			dispose();
		else{
			int isBack1 = Back_tickets.back(jt1.getText(),jt3.getText(),userName);
			int isBook1 = Book_tickets.book(jt2.getText(),jt3.getText(),userName);	
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			text2.append(df.format(new Date()));// new Date()为获取当前系统时间
			if(isBack1==0&&isBook1==0)//如果退票和买票都成功则提示用户修改成功
				text2.append("：修改成功！！\r\n");
			else if(isBack1==0&&isBook1==1){//如果退票成功而买票航班号错误则提示用户错误且把退掉的买回来
				text2.append("：要改成的航班号输入有误！！\r\n");
				Book_tickets.book(jt1.getText(),jt3.getText(),userName);	
			}
			else if(isBack1==0&&isBook1==2){//如果退票成功而买票因为票数不足而失败则提示用户且把票买回来
				text2.append("："+jt2.getText()+"号航班票数不足"+jt3.getText()+"张!!\r\n");
				Book_tickets.book(jt1.getText(),jt3.getText(),userName);
			}
			else if(isBack1==1&&isBook1==0){//如果买票成功而退票因为航班号错误而失败则提示用户且退票
				text2.append("要改的航班号输入有误!!\r\n");
				Back_tickets.back(jt2.getText(),jt3.getText(),userName);
			}else if(isBack1==2&&isBook1==0){//如果买票成功而退票因为票数错误而失败则提示用户且退票
				text2.append("："+jt1.getText()+"号航班您所购买的票数不足"+jt3.getText()+"张!!\r\n");
				Back_tickets.back(jt2.getText(),jt3.getText(),userName);
			}else if(isBack1==1&&isBook1==1){//如果两个航班号输入都错
				text2.append("航班输入错误!!!!\r\n");
			}else if(isBack1==1&&isBook1==2){//航班号1错误，航班2系统票数不足
				text2.append("：要修改的航班号输入有误!!"+jt2.getText()+"号航班票数不足"+jt3.getText()+"张!!\r\n");
			}else if(isBack1==2&&isBook1==1){//航班1已购的票数不足，航班号2错误
				text2.append("："+jt1.getText()+"号航班您所购买的票数不足"+jt3.getText()+"张!!要改成的航班号输入有误!!\r\n");
			}else if(isBack1==2&&isBook1==2){//航班1已购的票数不足，航班2系统票数不足
				text2.append("："+jt1.getText()+"号航班您所购买的票数不足"+jt3.getText()+"张!!"+jt2.getText()+"号航班票数不足"+jt3.getText()+"张\r\n");
			}
				
		}
		showlist();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		text2.append(df.format(new Date()));
		text2.append("\r\n"+ShowTicketsMessageInText.showMessage(userName)+"\r\n");
	}
	public void showlist(){
		text1.setText("航班号\t起点\t终点\t总票数\t卖出的票数\t起飞时间\t飞行时间\r\n");
		for(int i=1;i<=ModifyFlight.getMaxNum();i++){
			String[] temp = new String[7];
			List.list(i, temp);
			text1.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"小时\r\n");
		}
	}
}
