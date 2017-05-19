package _interface;

import java.awt.*;
import java.awt.event.*;
import _user.Login;
import javax.swing.*;
import _user.RemNameAndRemPw;
public class Login_interface extends JFrame implements ActionListener{
	private int state = 3;
	private boolean[] check = {false,false};
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JPanel jp5 = new JPanel();
	private JLabel jl0 = new JLabel("��¼");
	private JLabel jl1 = new JLabel("�û���:");
	private JLabel jl2 = new JLabel("��  ��:");
	private JTextField namet = new JTextField(20);
	private JPasswordField pwt = new JPasswordField(20);
	//CheckboxGroup cbg = new CheckboxGroup();
	private JCheckBox remName = new JCheckBox("��ס�û���");
	private JCheckBox remPw = new JCheckBox("��ס����");
	private JButton login = new JButton("��   ¼");
	//private JButton cancel = new JButton("ȡ��");
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public Login_interface(int state){
		super("��¼");
		super.setIconImage(iocn);
		this.state = state;
		setSize(400,400);
		setLocation(400, 150);
		setResizable(false);
		setVisible(true);
		setLayout(new GridLayout(5,1));
		jl0.setFont(new Font("�����п�",Font.BOLD,50));
		jl1.setFont(new Font("��Բ",Font.BOLD,20));
		jl2.setFont(new Font("��Բ",Font.BOLD,20));
		//���ñ���ͼƬ�����������ؼ�����Ϊ͸��
		ImageIcon background = new ImageIcon("img//bg.png");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
		jp1.setOpaque(false);
        jp2.setOpaque(false);
        jp3.setOpaque(false);
        jp4.setOpaque(false);
        jp5.setOpaque(false);
        jl0.setOpaque(false);
        jl1.setOpaque(false);
        jl2.setOpaque(false);
        remName.setOpaque(false);
        remPw.setOpaque(false);
       // login.setContentAreaFilled(false);
        //cancel.setContentAreaFilled(false);
        //���ð�ť��С
        login.setPreferredSize(new Dimension(200, 40));
        //���ð�ť��͸��
        login.setBackground(new Color(155,155,155,50));
        login.setFont(new Font("��Բ",Font.BOLD,20));
		add(jp1);add(jp2);add(jp3);add(jp4);add(jp5);
		jp1.add(jl0);
		jp2.add(jl1);jp2.add(namet);
		jp3.add(jl2);jp3.add(pwt);
		//����ס���û�����������ı�����
		String[] rem = new String[2];
		RemNameAndRemPw.get(rem);
		namet.setText(rem[0]);
		pwt.setText(rem[1]);
		//jp4.setBorder(BorderFactory.createTitledBorder(""));
		jp4.add(remName);jp4.add(remPw);
		jp5.add(login);//jp5.add(cancel);
		login.addActionListener(this);
		
		ItemListener itemListener = new ItemListener() {
            JCheckBox jCheckBox;
 
            public void itemStateChanged(ItemEvent e) {
                jCheckBox = (JCheckBox) e.getSource();
 
                if (jCheckBox.isSelected()) {
                	if(jCheckBox.getText().equals("��ס�û���"))
                		check[0] = true;
                	else{
                		remName.setSelected(true);
                		check[0] = true;
                		check[1] = true;
                	}
                } else {
                	if(jCheckBox.getText().equals("��ס����")){
                		check[0] = false;
                	}	
                	else
                	{
                		check[1] = false;
                	}
                }
 
            }
        };
		remName.addItemListener(itemListener);
		remPw.addItemListener(itemListener);
		remPw.setSelected(true);
	}
	public void actionPerformed(ActionEvent e){
		RemNameAndRemPw.set(check[0],check[1], namet.getText(), String.valueOf(pwt.getPassword()));
		if(e.getSource()==login){
			//System.out.println(state);
			boolean flag =Login.login(state,namet.getText(),String.valueOf(pwt.getPassword()));
			//System.out.println(flag);
			if(flag){
				dispose();
			};
		}
	}
}
