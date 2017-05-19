package _interface;
//功能：我的钱包，显示用户钱包信息，用户充值和提款功能，显示用户操作信息
//作者：孙加辉，时间：2017/05/07
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import _user.GetMoney;
import _user.GetID;
import _user.ChangeMoney;
import _manager.DataCheck;
public class ChangeMoneyInterface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;//序列号
	private static String name;//存放用户名
	private static String id;//存放用户ID
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JLabel jl1 = new JLabel("充值金额:");
	private JLabel jl2 = new JLabel("提款金额:");
	private JTextArea text = new JTextArea(5,40);
	private JScrollPane textroll = new JScrollPane(text);
	private JTextField jt1 = new JTextField(10);
	private JTextField jt2 = new JTextField(10);
	private JButton jb1 = new JButton("充  值");
	private JButton jb2 = new JButton("提  款");
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public ChangeMoneyInterface(String _name){
		super("我的钱包");
		super.setIconImage(iocn);
		//将用户名传进来
		name = _name;
		//调用方法，利用用户名获取ID
		id = GetID.get(_name)+"";
		//设置界面属性
		setLocation(400, 150);
		setSize(500,400);
		setResizable(false);
		setVisible(true);
		//添加控件
		setLayout(new GridLayout(2,1));
		add(jp1);add(jp2);
		jp2.setLayout(new GridLayout(2,1));
		jp2.add(jp3);jp2.add(jp4);
		jp3.add(jl1);jp3.add(jt1);jp3.add(jb1);
		jp4.add(jl2);jp4.add(jt2);jp4.add(jb2);
		jp1.add(textroll);
		//设置操作信息展示滚动框的属性
		textroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textroll.setBounds(20,20,40,40);
		text.setLineWrap(true); 
		text.setEditable(false); 
		//设置背景图片，并将其他控件设置为透明
		ImageIcon background = new ImageIcon("img//bg.png");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
		//将其他控件设置为透明
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		jl1.setOpaque(false);
		jl2.setOpaque(false);
		//设置按钮大小
		jb1.setPreferredSize(new Dimension(100, 30));
		jb2.setPreferredSize(new Dimension(100, 30));
		//设置按钮颜色及文字属性
		jb1.setBackground(new Color(155,155,155));
		jb1.setFont(new Font("幼圆",Font.BOLD,15));
		jb2.setBackground(new Color(155,155,155));
		jb2.setFont(new Font("幼圆",Font.BOLD,15));
		//设置标签字符属性
		jl1.setFont(new Font("华文行楷",Font.BOLD,20));
		jl2.setFont(new Font("华文行楷",Font.BOLD,20));
		//添加监听器
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		//添加用户信息
		text.setText("尊敬的用户"+name+":\r\n");
		text.append("您的剩余金额为:"+GetMoney.getMoney(id)+"元!!\r\n");
	}
	public void actionPerformed(ActionEvent e){
		//存款功能
		if(e.getSource()==jb1){
			//调用用户数据的检查方法，检测用户输入的数据是否符合规范
			if(DataCheck.check(jt1.getText())){
				//设置日期格式
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// new Date()为获取当前系统时间，显示用户操作时间
				text.append(df.format(new Date())+"\r\n");
				//调用存钱的方法
				if(ChangeMoney.addMoney(Integer.parseInt(jt1.getText()), id)){
					//显示用户操作信息
					text.append("您存了"+jt1.getText()+"元,剩余金额为"+GetMoney.getMoney(id)+"元!!\r\n");
				}else{
					text.append("您钱包中的钱超过限额，充值失败!!!\r\n");
				}
				
				
			}
			else{
				text.append("输入有误\r\n");
			}
			//存钱操作完成后将文本框清空
			jt1.setText("");
			jt2.setText("");
		}
		//取钱功能
		else if(e.getSource()==jb2){
			//调用用户数据的检查方法，检测用户输入的数据是否符合规范
			if(DataCheck.check(jt2.getText())){
				//设置日期格式
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// new Date()为获取当前系统时间，在用户操作信息文本框添加
				text.append(df.format(new Date())+"\r\n");
				//调用取钱的方法
				if(ChangeMoney.delMoney(Integer.parseInt(jt2.getText()), id)){
				text.append("您取了"+jt2.getText()+"元,剩余金额为"+GetMoney.getMoney(id)+"元!!\r\n");
				}else{
					text.append("金额不足,提款失败!!\r\n");
				}
			}
			else{
				text.append("输入有误!!剩余金额为"+GetMoney.getMoney(id)+"元!!\r\n");
			}
			//取钱结束后将文本框清空
			jt1.setText("");
			jt2.setText("");
		}
	}
//	public static void main(String[] args){
//		ChangeMoneyInterface t = new ChangeMoneyInterface("s123123");
//	}
}
