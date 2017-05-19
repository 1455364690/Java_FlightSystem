package _interface;

import java.awt.*;
import java.awt.event.*;
import _user.Login;
import javax.swing.*;
public class Index extends JFrame implements ActionListener{
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JPanel jp5 = new JPanel();
	private JPanel jp6 = new JPanel();
	private JButton modify = new JButton("�޸ĺ�����Ϣ");
	private JButton query = new JButton("��ѯ������Ϣ");
	private JButton book_tickets = new JButton("       ��Ʊ          ");
	private JButton back_tickets = new JButton("       ��Ʊ          ");
	private JButton modify_tickets = new JButton("�޸Ķ�Ʊ��Ϣ");
	private JButton register = new JButton("       ע��          ");
	public Index(){
		super("��ҳ");
		setLayout(new GridLayout(6,1));
		add(jp1);
		add(jp2);
		add(jp3);
		add(jp4);
		add(jp5);
		add(jp6);
		jp1.add(modify);jp2.add(query);
		jp3.add(book_tickets);jp4.add(back_tickets);
		jp5.add(modify_tickets);jp6.add(register);
		modify.addActionListener(this);
		query.addActionListener(this);
		book_tickets.addActionListener(this);
		back_tickets.addActionListener(this);
		modify_tickets.addActionListener(this);
		register.addActionListener(this);
		//pack();
		setSize(500,400);
		setVisible(true);
		//�رմ����¼�
		this.addWindowListener
        (new WindowAdapter() 
        {public void windowClosing(WindowEvent e) {
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
			Login.login(0);
		}
		else if(e.getSource()==back_tickets){
			Login.login(1);
		}
		else if(e.getSource()==modify_tickets){
			Login.login(2);
		}
		else if(e.getSource()==register){
			Register_interface	reg = new Register_interface();
		}
	}
}

