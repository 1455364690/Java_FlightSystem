package _interface;
//�������Ҫ����Ϊ��ʾһ������Ա������Ĵ���
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import _manager.ModifyFlight;
public class Modify_add_interface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton cancel = new JButton("ȡ��");
	private JButton add = new JButton("���");
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
	private JLabel addr1l = new JLabel("��ʼ��ַ  :");
	private JLabel addr2l = new JLabel("�յ��ַ  :");
	private JLabel totall = new JLabel("��Ʊ����  :");
	private JLabel soldl = new JLabel("����Ʊ��  :");
	private JLabel pricel = new JLabel("��Ʊ�۸�  :");
	private JLabel startl = new JLabel("���ʱ��-��:");
	private JLabel start2l = new JLabel("���ʱ��-��:");
	private JLabel start3l = new JLabel("���ʱ��-ʱ:");
	private JLabel start4l = new JLabel("���ʱ��-��:");
	private JLabel lastl = new JLabel("����ʱ��  :");
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
	private JLabel logl = new JLabel("����һ������");
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public Modify_add_interface(){
		super("����һ������");
		super.setIconImage(iocn);
		//��������������
		setSize(500,550);
		setLayout(new GridLayout(12,1));
		setLocation(400, 150);
    	setResizable(false);
    	setVisible(true);
		//���ñ���ͼƬ�����������ؼ�����Ϊ͸��
		ImageIcon background = new ImageIcon("img//bg.png");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
		//�������ؼ�����Ϊ͸��
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
		    	
		//���ð�ť��С
		add.setPreferredSize(new Dimension(200, 40));
		//���ð�ť��ɫ
		add.setBackground(new Color(0,155,0));
		add.setFont(new Font("��Բ",Font.BOLD,20));
		//��ǩ����
		logl.setFont(new Font("�����п�",Font.BOLD,40));
		//��ӿؼ�
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
		//��Ӽ�����
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
