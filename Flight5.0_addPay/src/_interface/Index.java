package _interface;
//功能：主界面，
//作者：孙加辉，时间：2017/05/07
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Index extends JFrame implements ActionListener,Runnable{
	private static final long serialVersionUID = 1L;
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JPanel jp5 = new JPanel();
	private JPanel jp6 = new JPanel();
	private JButton modify = new JButton("修改航班信息");
	private JButton query = new JButton("查询航班信息");
	private JButton book_tickets = new JButton("订   票 ");
	private JButton back_tickets = new JButton("退   票");
	private JButton modify_tickets = new JButton("修改订票信息");
	private JButton register = new JButton("注   册");
	private JTextPane text2 = new JTextPane();
	private JTextPane text1 = new JTextPane();
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public Index(){
		super("首页");
		super.setIconImage(iocn);
		setLocation(400, 150);
		setSize(500,400);
		this.setResizable(false);
		setLayout(new GridLayout(3,1));
		//设置背景图片，并将其他控件设置为透明
		ImageIcon background = new ImageIcon("img//bg.png");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
        setVisible(true);
        //将其他控件设置为透明
        jp1.setOpaque(false);
        jp2.setOpaque(false);
        jp3.setOpaque(false);
        jp4.setOpaque(false);
        jp5.setOpaque(false);
        jp6.setOpaque(false);
        modify.setPreferredSize(new Dimension(150, 30));
        query.setPreferredSize(new Dimension(150, 30));
        book_tickets.setPreferredSize(new Dimension(150, 30));
        back_tickets.setPreferredSize(new Dimension(150, 30));
        modify_tickets.setPreferredSize(new Dimension(150, 30));
        register.setPreferredSize(new Dimension(150, 30));
        //滚动字幕
        jp1.add(text1);
        text1.setFont(new Font("华文行楷",Font.PLAIN,60));
        text1.setOpaque(false);
        text1.setEditable(false);
        //超链接文本窗口
        jp2.add(text2);
        text2.setEditable(false);
        text2.setPreferredSize(new Dimension(450,100));
        text2.setOpaque(false);
        text2.setFont(new Font("楷体",Font.BOLD,20));
        //text2.setText("<html><A href='http://www.baidu.com'>test</A></html>");
        //添加控件
        add(jp1);
		add(jp2);
		add(jp3);
		jp3.setLayout(new GridLayout(2,1));
		jp3.add(jp5);
		jp3.add(jp6);
		jp5.add(modify);jp5.add(query);
		jp5.add(book_tickets);jp6.add(back_tickets);
		jp6.add(modify_tickets);jp6.add(register);
		//添加监听器
		modify.addActionListener(this);
		query.addActionListener(this);
		book_tickets.addActionListener(this);
		back_tickets.addActionListener(this);
		modify_tickets.addActionListener(this);
		register.addActionListener(this);
		//设置按钮属性
		modify.setFont(new Font("幼圆",Font.BOLD,15));
		query.setFont(new Font("幼圆",Font.BOLD,15));
		book_tickets.setFont(new Font("幼圆",Font.BOLD,15));
		back_tickets.setFont(new Font("幼圆",Font.BOLD,15));
		modify_tickets.setFont(new Font("幼圆",Font.BOLD,15));
		register.setFont(new Font("幼圆",Font.BOLD,15));
		modify.setBackground(new Color(155,155,255));
		query.setBackground(new Color(155,255,155));
		book_tickets.setBackground(new Color(255,155,155));
		back_tickets.setBackground(new Color(255,155,255));
		modify_tickets.setBackground(new Color(155,255,255));
		register.setBackground(new Color(255,255,155));
		//滚动字幕，新建线程
		Thread mw = new Thread(this);
		mw.start();
	}
	public void actionPerformed(ActionEvent e){
		//管理员功能
		if(e.getSource()==modify){
			Manager_interface.managerLogin();
		}
		//查询功能
		else if(e.getSource()==query){
			Query_interface test2 = new Query_interface();
			test2.setEnabled(true);
		}
		//订票功能
		else if(e.getSource()==book_tickets){
			Login_interface login = new Login_interface(0);//0表示订票
			login.setEnabled(true);
		}
		//退票功能
		else if(e.getSource()==back_tickets){
			Login_interface login = new Login_interface(1);//1为退票
			login.setEnabled(true);
		}
		//调票功能
		else if(e.getSource()==modify_tickets){
			Login_interface login = new Login_interface(2);//2为换票
			login.setEnabled(true);
		}
		//注册功能
		else if(e.getSource()==register){
			Register_interface	reg = new Register_interface();
			reg.setEnabled(true);
		}
	}
	//滚动字幕和滚动新闻
	public void run(){
		//滚动字幕的内容
		text1.setText("                              ABCD航空公司欢迎您!!");
		//循环播放新闻的内容
		String[] str1 = {".  这  是  一  条 新 闻     \r\n",
						".  ABCD航 空 公 司 开 业 啦 \r\n",
						".  只 要 持 有 学 生 证\r\n",
						".  今 天 即 可 免费 乘 坐\r\n",
						".  对 ! 没 错 ！\r\n",
						".  这 不 是 演 习 ！\r\n",
						".  这 不 是 演 习  ！\r\n",};
		//设置字幕滚动快慢
		int m = 0;
		for(int k = 0;k<100;k++){
			for(int j = 0;j<33;j++){
				try{
					Thread.sleep(200);
				}catch(Exception e){}
				text1.setText(text1.getText() + "    ");
			}
			text2.setText(text2.getText()+(m+1)+str1[m]);
			if(m>=3)
				text2.setText(text2.getText().replace(((m-2)+str1[m-3]),""));
			if(m<=2)
				text2.setText(text2.getText().replace(((m+5)+str1[m+4]),""));
			m++;
			m=m%7;
			text1.setText("                                                                  ABCD航空公司欢迎您!!");
		}
		//线程结束
		Thread.interrupted();
	}
	
}
