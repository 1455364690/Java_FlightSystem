package _interface;
//�������Ҫ����Ϊ��ʾһ��������Ĵ���
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import _manager.ModifyFlight;
public class Modify_modi_interface extends JFrame implements ActionListener{
	private JButton cancel = new JButton("ȡ��");
	private JButton add = new JButton("�޸�");
	private JPanel nump = new JPanel();
	private JPanel addr1p = new JPanel();
	private JPanel addr2p = new JPanel();
	private JPanel totalp = new JPanel();
	private JPanel soldp = new JPanel();
	private JPanel startp = new JPanel();
	private JPanel lastp = new JPanel();
	private JPanel buttonp = new JPanel();
	private JLabel numl = new JLabel("�������:");
	private JLabel addr1l = new JLabel("��ʼ��ַ:");
	private JLabel addr2l = new JLabel("�յ��ַ:");
	private JLabel totall = new JLabel("��Ʊ����:");
	private JLabel soldl = new JLabel("����Ʊ��:");
	private JLabel startl = new JLabel("���ʱ��:");
	private JLabel lastl = new JLabel("����ʱ��:");
	private JTextField numt = new JTextField(10);
	private JTextField addr1t = new JTextField(10);
	private JTextField addr2t = new JTextField(10);
	private JTextField totalt = new JTextField(10);
	private JTextField soldt = new JTextField(10);
	private JTextField startt = new JTextField(10);
	private JTextField lastt = new JTextField(10);
	private JPanel logp = new JPanel();
	private JLabel logl = new JLabel("�޸�һ������");
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public Modify_modi_interface(){
		super("�޸�һ������");
		super.setIconImage(iocn);
		setSize(500,400);
		setLayout(new GridLayout(9,1));
		
		//���ñ���ͼƬ�����������ؼ�����Ϊ͸��
		ImageIcon background = new ImageIcon("img//bg.png");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
//�������ؼ�����Ϊ͸��
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
//���þ��Դ�С��λ��
    	setLocation(400, 150);
    	setResizable(false);
    	 //���ð�ť��С
    	add.setPreferredSize(new Dimension(200, 40));
        //���ð�ť��͸��
    	add.setBackground(new Color(0,255,0,50));
    	add.setFont(new Font("��Բ",Font.BOLD,20));
    	logl.setFont(new Font("�����п�",Font.BOLD,40));
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