package _interface;
//该类的主要功能为显示一个新添航班的窗口
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import _manager.ModifyFlight;
public class Modify_modi_interface extends JFrame implements ActionListener{
	private JButton cancel = new JButton("取消");
	private JButton add = new JButton("修改");
	private JPanel nump = new JPanel();
	private JPanel addr1p = new JPanel();
	private JPanel addr2p = new JPanel();
	private JPanel totalp = new JPanel();
	private JPanel soldp = new JPanel();
	private JPanel startp = new JPanel();
	private JPanel lastp = new JPanel();
	private JPanel buttonp = new JPanel();
	private JLabel numl = new JLabel("航班序号:");
	private JLabel addr1l = new JLabel("起始地址:");
	private JLabel addr2l = new JLabel("终点地址:");
	private JLabel totall = new JLabel("机票总数:");
	private JLabel soldl = new JLabel("已售票数:");
	private JLabel startl = new JLabel("起飞时间:");
	private JLabel lastl = new JLabel("飞行时间:");
	private JTextField numt = new JTextField(10);
	private JTextField addr1t = new JTextField(10);
	private JTextField addr2t = new JTextField(10);
	private JTextField totalt = new JTextField(10);
	private JTextField soldt = new JTextField(10);
	private JTextField startt = new JTextField(10);
	private JTextField lastt = new JTextField(10);
	private JPanel logp = new JPanel();
	private JLabel logl = new JLabel("修改一个航班");
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public Modify_modi_interface(){
		super("修改一个航班");
		super.setIconImage(iocn);
		setSize(500,400);
		setLayout(new GridLayout(9,1));
		
		//设置背景图片，并将其他控件设置为透明
		ImageIcon background = new ImageIcon("img//bg.png");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
//将其他控件设置为透明
        nump.setOpaque(false);
    	addr1p.setOpaque(false);
    	addr2p.setOpaque(false);
    	totalp.setOpaque(false);
    	soldp.setOpaque(false);
    	startp.setOpaque(false);
    	lastp.setOpaque(false);
    	buttonp.setOpaque(false);
    	numl.setOpaque(false);
    	addr1l.setOpaque(false);
    	addr2l.setOpaque(false);
    	totall.setOpaque(false);
    	soldl.setOpaque(false);
    	startl.setOpaque(false);
    	lastl.setOpaque(false);
    	numt.setOpaque(false);
    	addr1t.setOpaque(false);
    	addr2t.setOpaque(false);
    	totalt.setOpaque(false);
    	soldt.setOpaque(false);
    	startt.setOpaque(false);
    	lastt.setOpaque(false);
    	logp.setOpaque(false);
    	logl.setOpaque(false);
//设置绝对大小和位置
    	setLocation(400, 150);
    	setResizable(false);
    	 //设置按钮大小
    	add.setPreferredSize(new Dimension(200, 40));
        //设置按钮半透明
    	add.setBackground(new Color(0,255,0,50));
    	add.setFont(new Font("幼圆",Font.BOLD,20));
    	logl.setFont(new Font("华文行楷",Font.BOLD,40));
		add(logp);logp.add(logl);
		
		
		add(nump);add(addr1p);add(addr2p);add(totalp);add(soldp);add(startp);add(lastp);add(buttonp);
		nump.add(numl);nump.add(numt);
		addr1p.add(addr1l);addr1p.add(addr1t);
		addr2p.add(addr2l);addr2p.add(addr2t);
		totalp.add(totall);totalp.add(totalt);
		soldp.add(soldl);soldp.add(soldt);
		startp.add(startl);startp.add(startt);
		lastp.add(lastl);lastp.add(lastt);
		//buttonp.add(cancel);
		buttonp.add(add);
		cancel.addActionListener(this);add.addActionListener(this);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==cancel)
			dispose();
		else{
			ModifyFlight.modiFlight(numt.getText(),addr1t.getText(), addr2t.getText(), totalt.getText(),
					soldt.getText(), startt.getText(), lastt.getText());
			dispose();
		}
	}
}