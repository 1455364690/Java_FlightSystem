package _interface;
//�������Ҫ����Ϊ��ʾһ��������Ĵ���
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import _manager.ModifyFlight;
public class Modify_modi_interface extends JFrame implements ActionListener{
	private Button cancel = new Button("ȡ��");
	private Button add = new Button("�޸�");
	private Panel nump = new Panel();
	private Panel addr1p = new Panel();
	private Panel addr2p = new Panel();
	private Panel totalp = new Panel();
	private Panel soldp = new Panel();
	private Panel startp = new Panel();
	private Panel lastp = new Panel();
	private Panel buttonp = new Panel();
	private Label numl = new Label("�������:");
	private Label addr1l = new Label("��ʼ��ַ:");
	private Label addr2l = new Label("�յ��ַ:");
	private Label totall = new Label("��Ʊ����:");
	private Label soldl = new Label("����Ʊ��:");
	private Label startl = new Label("���ʱ��:");
	private Label lastl = new Label("����ʱ��:");
	private TextField numt = new TextField(10);
	private TextField addr1t = new TextField(10);
	private TextField addr2t = new TextField(10);
	private TextField totalt = new TextField(10);
	private TextField soldt = new TextField(10);
	private TextField startt = new TextField(10);
	private TextField lastt = new TextField(10);
	public Modify_modi_interface(){
		super("�޸�һ������");
		setSize(500,400);
		setLayout(new GridLayout(8,1));
		add(nump);add(addr1p);add(addr2p);add(totalp);add(soldp);add(startp);add(lastp);add(buttonp);
		nump.add(numl);nump.add(numt);
		addr1p.add(addr1l);addr1p.add(addr1t);
		addr2p.add(addr2l);addr2p.add(addr2t);
		totalp.add(totall);totalp.add(totalt);
		soldp.add(soldl);soldp.add(soldt);
		startp.add(startl);startp.add(startt);
		lastp.add(lastl);lastp.add(lastt);
		buttonp.add(cancel);buttonp.add(add);
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