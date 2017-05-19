package _interface;
//功能：用户购票时候的界面，主要有显示航班信息、显示用户操作信息、反馈用户购票信息
//作者：孙加辉，时间：2017/05/07
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import _user.Book_tickets;
import _user.ShowTicketsMessageInText;
import _flight.List;
import _manager.ModifyFlight;
import _manager.DataCheck;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Book_interface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;//序列号
	private static String userName;//存放传进来的用户名
	private JTextArea text1 = new JTextArea(10,63);
	private JTextArea text2 = new JTextArea(10,40);
	private JScrollPane textroll1 = new JScrollPane(text1);
	private JScrollPane textroll2 = new JScrollPane(text2);
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JPanel jp5 = new JPanel();
	private JLabel jl1 = new JLabel("航班号:");
	private JLabel jl2 = new JLabel("订票数:");
	private JTextField jt1 = new JTextField(20);
	private JTextField jt2 = new JTextField(20);
	private JButton jb1 = new JButton("我的钱包");
	private JButton jb2 = new JButton("购买");
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");//框架的左上角图标
	public Book_interface(String _name){//参数为登录时的用户名
		super("订票");
		super.setIconImage(iocn);//显示图标
		userName = _name;//将用户名传进来
		//主界面属性
		showlist();
		setSize(800,600);
		setLayout(new GridLayout(3,1));
		setLocation(400, 150);
    	setResizable(false);
    	setVisible(true);
		//添加部分控件
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
		//设置背景图片，并将其他控件设置为透明
		ImageIcon background = new ImageIcon("img//bg3.jpg");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);
		getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
		//将其他控件设置为透明
		text1.setOpaque(false);
		text2.setOpaque(false);
		textroll1.setOpaque(false);
		textroll2.setOpaque(false);
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		jp5.setOpaque(false);
		jl1.setOpaque(false);
		jl2.setOpaque(false);
		jt1.setOpaque(false);
		jt2.setOpaque(false);
		//设置按钮大小
		jb2.setPreferredSize(new Dimension(250, 50));
		jb1.setPreferredSize(new Dimension(250, 50));
		//设置按钮颜色和按钮内文字属性
		jb2.setBackground(new Color(155,155,155));
		jb2.setFont(new Font("幼圆",Font.BOLD,15));
		jb1.setBackground(new Color(155,255,155));
		jb1.setFont(new Font("幼圆",Font.BOLD,15));
		//设置标签字符
		jl1.setFont(new Font("幼圆",Font.BOLD,20));
		jl2.setFont(new Font("幼圆",Font.BOLD,20));
		//添加控件
		jp3.setLayout(new GridLayout(2,1));
		jp3.add(jp4);jp3.add(jp5);
		jp4.add(jl1);jp4.add(jt1);jp4.add(jl2);jp4.add(jt2);
		jp5.add(jb1);
		jp5.add(jb2);
		//添加监听器
		jb1.addActionListener(this);jb2.addActionListener(this);
		//在文本框中添加用户信息
		text2.setText("尊敬的用户"+userName+":\r\n");
		text2.append(ShowTicketsMessageInText.showMessage(userName)+"\r\n");
	}
	public void actionPerformed(ActionEvent e){
		//我的钱包功能
		if(e.getSource()==jb1){
			ChangeMoneyInterface temp = new ChangeMoneyInterface(userName);
			temp.setEnabled(true);
		}
		//退票功能
		else{
			//检查输入是否符合规范
			boolean jt1Check,jt2Check;
			jt1Check = DataCheck.check(jt1.getText());
			jt2Check = DataCheck.check(jt2.getText());
			if(!(jt1Check&&jt2Check)){
				text2.append("无输入或输入格式不正确\r\n");
				return;
			}
			//调用购票方法，返回购票状态，0为购票成功，1为航班号有误，2为票数不足，3是钱不够
			int isBook = Book_tickets.book(jt1.getText(),jt2.getText(),userName);
			//设置日期格式
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// new Date()为获取当前系统时间，并添加在用户操作信息中
			text2.append(df.format(new Date()));
			if(isBook==0)
				text2.append("：购票成功！！\r\n");
			else if(isBook == 1)
				text2.append("：航班号输入有误！！\r\n");
			else if(isBook == 2)
				text2.append("："+jt1.getText()+"号航班剩余票数不足"+jt2.getText()+"张!!\r\n");
			else if(isBook == 3)
				text2.append("：您的剩余金额不足!!\r\n");
			showlist();
			// new Date()为获取当前系统时间
			text2.append(df.format(new Date()));
			text2.append("\r\n"+ShowTicketsMessageInText.showMessage(userName)+"\r\n");
		}
	}
	//显示航班信息
	public void showlist(){
		text1.setText("航班号\t起点\t终点\t总票数\t卖出的票数\t票价\t起飞时间\t飞行时间\r\n");
		for(int i=1;i<=ModifyFlight.getMaxNum();i++){
			String[] temp = new String[8];
			List.list(i, temp);
			text1.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"\t"+temp[7]+"小时\r\n");
		}
	}
}
