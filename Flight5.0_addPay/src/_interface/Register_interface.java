package _interface;
//��ʾ�û�ע�����
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import java.awt.*;
import java.awt.event.*;
import _user.Register;
import javax.swing.*; 
public class Register_interface extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton back =  new JButton("����");
	private JButton reg = new JButton("ע��");
	private JPanel logp = new JPanel();
	private JPanel namep = new JPanel();
	private JPanel namep1 = new JPanel();
	private JPanel namep2 = new JPanel();
	private JPanel pwp = new JPanel();
	private JPanel pwp1 = new JPanel();
	private JPanel pwp2 = new JPanel();
	private JPanel pw1p = new JPanel();
	private JPanel buttonp = new JPanel();
	private JLabel logl = new JLabel("ע  ��");
	private JLabel namel = new JLabel("�û���:");
	private JLabel pwl = new JLabel("��   ��:");
	private JLabel pw1l = new JLabel("��   ��:");
	private JLabel tips = new JLabel("�û�����6-10λ������ĸ��ɣ��ұ�������ĸ��ͷ");
	private JLabel tips2 = new JLabel("������8-13λ������ĸ���");
	private JTextField namet = new JTextField(20);
	private JPasswordField pwt = new JPasswordField(20);
	private JPasswordField pw1t = new JPasswordField(20);
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public Register_interface(){
		super("ע���˻�");
		super.setIconImage(iocn);
		//��������
		setSize(400,400);
		setLayout(new GridLayout(5,1));
		setLocation(400, 150);
    	setResizable(false);
    	setVisible(true);
		//���ñ���ͼƬ�����������ؼ�����Ϊ͸��
		ImageIcon background = new ImageIcon("img//bg.png");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);
        getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
        //�������ؼ�����Ϊ͸��
        logp.setOpaque(false);
        logl.setOpaque(false);
        namep.setOpaque(false);
        namep1.setOpaque(false);
        namep2.setOpaque(false);
        pwp.setOpaque(false);
    	pwp1.setOpaque(false);
    	pwp2.setOpaque(false);
    	pw1p.setOpaque(false);
    	buttonp.setOpaque(false);
    	namel.setOpaque(false);
    	pwl.setOpaque(false);
    	pw1l.setOpaque(false);
    	tips.setOpaque(false);
    	tips2.setOpaque(false);
    	namet.setOpaque(false);
    	pwt.setOpaque(false);
    	pw1t.setOpaque(false);
    	 //���ð�ť��С
    	reg.setPreferredSize(new Dimension(250, 50));
        //���ð�ť��ɫ
    	reg.setBackground(new Color(155,155,155));
    	reg.setFont(new Font("��Բ",Font.BOLD,15));
    	//���ñ�ǩ�ַ�
    	namel.setFont(new Font("��Բ",Font.BOLD,20));
    	pwl.setFont(new Font("��Բ",Font.BOLD,20));
    	pw1l.setFont(new Font("��Բ",Font.BOLD,20));
		logl.setFont(new Font("�����п�",Font.BOLD,50));
		//��ӿؼ�
		add(logp);add(namep);add(pwp);add(pw1p);add(buttonp);
		logp.add(logl);
		namep.setLayout(new GridLayout(2,1));
		namep.add(namep1);namep.add(namep2);
		namep1.add(namel);namep1.add(namet);namep2.add(tips);
		pwp.setLayout(new GridLayout(2,1));
		pwp.add(pwp1);pwp.add(pwp2);
		pwp1.add(pwl);pwp1.add(pwt);pwp2.add(tips2);
		pw1p.add(pw1l);pw1p.add(pw1t);
		//buttonp.add(back);
		buttonp.add(reg);
		back.addActionListener(this);
		reg.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==back){
			dispose();
		}
		else if(e.getSource()==reg){
			//����ע�᷽��
			Register.setValue(namet.getText(), String.valueOf(pwt.getPassword()), String.valueOf(pw1t.getPassword()));
			Register.register();
			if(Register.getState()){
				JOptionPane.showMessageDialog(null, "ע��ɹ�");
				dispose();
			}
			else{
				//JOptionPane.showMessageDialog(null, "ע��ʧ��");
			}
		}
	}
}
