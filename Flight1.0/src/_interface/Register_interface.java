package _interface;
import java.awt.*;
import java.awt.event.*;
import _user.Register;
import javax.swing.*; 
public class Register_interface extends Frame implements ActionListener{
	private Button back =  new Button("����");
	private Button reg = new Button("ע��");
	private Panel namep = new Panel();
	private Panel namep1 = new Panel();
	private Panel namep2 = new Panel();
	private Panel pwp = new Panel();
	private Panel pwp1 = new Panel();
	private Panel pwp2 = new Panel();
	private Panel pw1p = new Panel();
	private Panel buttonp = new Panel();
	private Label namel = new Label("�û���:");
	private Label pwl = new Label("����:");
	private Label pw1l = new Label("�ٴ�:");
	private Label tips = new Label("�û�����6-10λ������ĸ��ɣ��ұ�������ĸ��ͷ");
	private Label tips2 = new Label("������8-13λ������ĸ���");
	private JTextField namet = new JTextField(20);
	private JPasswordField pwt = new JPasswordField(20);
	private JPasswordField pw1t = new JPasswordField(20);
	public Register_interface(){
		super("ע���˻�");
		setSize(400,400);
		setLayout(new GridLayout(4,1));
		add(namep);add(pwp);add(pw1p);add(buttonp);
		namep.setLayout(new GridLayout(2,1));
		namep.add(namep1);namep.add(namep2);
		namep1.add(namel);namep1.add(namet);namep2.add(tips);
		pwp.setLayout(new GridLayout(2,1));
		pwp.add(pwp1);pwp.add(pwp2);
		pwp1.add(pwl);pwp1.add(pwt);pwp2.add(tips2);
		pw1p.add(pw1l);pw1p.add(pw1t);
		buttonp.add(back);buttonp.add(reg);
		back.addActionListener(this);
		reg.addActionListener(this);
		setVisible(true);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==back){
			dispose();
		}
		else if(e.getSource()==reg){
			Register.setValue(namet.getText(), String.valueOf(pwt.getPassword()), String.valueOf(pw1t.getPassword()));
			Register.register();
			if(Register.getState())
				dispose();
			else{
				//JOptionPane.showMessageDialog(null, "ע��ʧ��");
			}
		}
	}
}
