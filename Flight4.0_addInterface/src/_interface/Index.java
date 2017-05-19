package _interface;

import java.awt.*;
import java.awt.event.*;
import _user.Login;
import javax.swing.*;
import javax.swing.text.Caret;
import javax.swing.text.StyledDocument;

import java.util.Timer; 
import java.util.TimerTask;
public class Index extends JFrame implements ActionListener{
	
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
        modify.setContentAreaFilled(false);
        query.setContentAreaFilled(false);
        book_tickets.setContentAreaFilled(false);
        back_tickets.setContentAreaFilled(false);
        modify_tickets.setContentAreaFilled(false);
        register.setContentAreaFilled(false);
        //滚动字幕
        jp1.add(text1);
        text1.setFont(new Font("华文行楷",Font.PLAIN,60));
        text1.setOpaque(false);
        text1.setEditable(false);
        //超链接文本窗口
        jp2.add(text2);
        
        //text2.setContentType("text/html");
        text2.setEditable(false);
        text2.setPreferredSize(new Dimension(450,100));
        text2.setOpaque(false);
        text2.setFont(new Font("楷体",Font.BOLD,20));
        //text2.setText("<html><A href='http://www.baidu.com'>test</A></html>");
        
        add(jp1);
		add(jp2);
		add(jp3);
		jp3.setLayout(new GridLayout(2,1));
		jp3.add(jp5);
		jp3.add(jp6);
		modify.setSize(50, 50);
		jp5.setLayout(new GridLayout(1,3));
		jp6.setLayout(new GridLayout(1,3));
		jp5.add(modify);jp5.add(query);
		jp5.add(book_tickets);jp6.add(back_tickets);
		jp6.add(modify_tickets);jp6.add(register);
		modify.addActionListener(this);
		query.addActionListener(this);
		book_tickets.addActionListener(this);
		back_tickets.addActionListener(this);
		modify_tickets.addActionListener(this);
		register.addActionListener(this);
		
		modify.setFont(new Font("幼圆",Font.BOLD,15));
		query.setFont(new Font("幼圆",Font.BOLD,15));
		book_tickets.setFont(new Font("幼圆",Font.BOLD,15));
		back_tickets.setFont(new Font("幼圆",Font.BOLD,15));
		modify_tickets.setFont(new Font("幼圆",Font.BOLD,15));
		register.setFont(new Font("幼圆",Font.BOLD,15));
		
		moveword();
		//关闭窗口事件
		this.addWindowListener
        (new WindowAdapter() 
        {public void windowClosing(WindowEvent e) {
        	Thread.interrupted();
              System.exit(0);
            }
         });
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==modify){
			Manager_interface.managerLogin();
		}
		else if(e.getSource()==query){
			Query_interface test2 = new Query_interface();
		}
		else if(e.getSource()==book_tickets){
			Login_interface login = new Login_interface(0);//0表示订票
		}
		else if(e.getSource()==back_tickets){
			Login_interface login = new Login_interface(1);//1为退票
		}
		else if(e.getSource()==modify_tickets){
			Login_interface login = new Login_interface(2);//2为换票
		}
		else if(e.getSource()==register){
			Register_interface	reg = new Register_interface();
		}
	}
	public void moveword(){
		text1.setText("                              ABCD航班欢迎您!!");
		String[] str1 = {".  这  是  一  条 新 闻     \r\n",
						".ABCD 航  空  公 \r\n",
						".ABCD司 正  式  开\r\n",
						".ABCD  啦  ！\r\n",
						".ABCD 啦  ！\r\n",
						".ABCD  业  啦  ！\r\n",
						".ABCD   业  啦  ！\r\n",};
		int m = 0;
		for(int k = 0;k<100;k++){
			for(int j = 0;j<30;j++){
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
			m++;System.out.println(m);
			m=m%7;
			text1.setText("                                                           ABCD航班欢迎您!!");
		}
		Thread.interrupted();
	}
	
}
