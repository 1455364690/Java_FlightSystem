package _interface;
//该类的主要功能为显示一个管理员新添航班的窗口
//作者：孙加辉，时间：2017/05/07
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import _manager.ModifyFlight;
public class Modify_add_interface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton cancel = new JButton("取消");
	private JButton add = new JButton("添加");
	private JPanel addr1p = new JPanel();
	private JPanel addr2p = new JPanel();
	private JPanel totalp = new JPanel();
	private JPanel soldp = new JPanel();
	private JPanel pricep = new JPanel();
	private JPanel startp = new JPanel();
	private JPanel start2p = new JPanel();
	private JPanel start3p = new JPanel();
	private JPanel start4p = new JPanel();
	private JPanel lastp = new JPanel();
	private JPanel buttonp = new JPanel();
	private JLabel addr1l = new JLabel("起始地址  :");
	private JLabel addr2l = new JLabel("终点地址  :");
	private JLabel totall = new JLabel("机票总数  :");
	private JLabel soldl = new JLabel("已售票数  :");
	private JLabel pricel = new JLabel("机票价格  :");
	private JLabel startl = new JLabel("起飞时间-月:");
	private JLabel start2l = new JLabel("起飞时间-日:");
	private JLabel start3l = new JLabel("起飞时间-时:");
	private JLabel start4l = new JLabel("起飞时间-分:");
	private JLabel lastl = new JLabel("飞行时间  :");
	private JTextField addr1t = new JTextField(10);
	private JTextField addr2t = new JTextField(10);
	private JTextField totalt = new JTextField(10);
	private JTextField soldt = new JTextField(10);
	private JTextField pricet = new JTextField(10);
	private JTextField startt = new JTextField(10);
	private JTextField start2t = new JTextField(10);
	private JTextField start3t = new JTextField(10);
	private JTextField start4t = new JTextField(10);
	private JTextField lastt = new JTextField(10);
	private JPanel logp = new JPanel();
	private JLabel logl = new JLabel("增加一个航班");
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public Modify_add_interface(){
		super("增加一个航班");
		super.setIconImage(iocn);
		//设置主界面属性
		setSize(500,550);
		setLayout(new GridLayout(12,1));
		setLocation(400, 150);
    	setResizable(false);
    	setVisible(true);
		//设置背景图片，并将其他控件设置为透明
		ImageIcon background = new ImageIcon("img//bg.png");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
		//将其他控件设置为透明
		addr1p.setOpaque(false);
		addr2p.setOpaque(false);
		totalp.setOpaque(false);
		soldp.setOpaque(false);
		pricep.setOpaque(false);
		startp.setOpaque(false);
    	start2p.setOpaque(false);
    	start3p.setOpaque(false);
    	start4p.setOpaque(false);
    	lastp.setOpaque(false);
    	buttonp.setOpaque(false);
     	addr1l.setOpaque(false);
    	addr2l.setOpaque(false);
    	totall.setOpaque(false);
	   	pricel.setOpaque(false);
	   	soldl.setOpaque(false);
    	startl.setOpaque(false);
	   	start2l.setOpaque(false);
    	start3l.setOpaque(false);
    	start4l.setOpaque(false);
    	lastl.setOpaque(false);
    	addr1t.setOpaque(false);
    	addr2t.setOpaque(false);
    	totalt.setOpaque(false);
    	soldt.setOpaque(false);
    	pricet.setOpaque(false);
    	startt.setOpaque(false);
    	start2t.setOpaque(false);
    	start3t.setOpaque(false);
    	start4t.setOpaque(false);
    	lastt.setOpaque(false);
    	logp.setOpaque(false);
    	logl.setOpaque(false);	    	
		    	
		//设置按钮大小
		add.setPreferredSize(new Dimension(200, 40));
		//设置按钮颜色
		add.setBackground(new Color(0,155,0));
		add.setFont(new Font("幼圆",Font.BOLD,20));
		//标签属性
		logl.setFont(new Font("华文行楷",Font.BOLD,40));
		//添加控件
		add(logp);logp.add(logl);
		add(addr1p);add(addr2p);add(totalp);add(soldp);add(pricep);
		add(startp);add(start2p);add(start3p);add(start4p);add(lastp);add(buttonp);
		addr1p.add(addr1l);addr1p.add(addr1t);
		addr2p.add(addr2l);addr2p.add(addr2t);
		totalp.add(totall);totalp.add(totalt);
		soldp.add(soldl);soldp.add(soldt);
		pricep.add(pricel);pricep.add(pricet);
		startp.add(startl);startp.add(startt);
		start2p.add(start2l);start2p.add(start2t);
		start3p.add(start3l);start3p.add(start3t);
		start4p.add(start4l);start4p.add(start4t);
		lastp.add(lastl);lastp.add(lastt);
		//buttonp.add(cancel);
		buttonp.add(add);
		//添加监听器
		cancel.addActionListener(this);add.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==cancel)
			dispose();
		else{
			ModifyFlight.addFlight(addr1t.getText(), addr2t.getText(), totalt.getText(),
					soldt.getText(), pricet.getText(),startt.getText(), start2t.getText(), start3t.getText(), start4t.getText(), lastt.getText());
			dispose();
		}
	}
}
