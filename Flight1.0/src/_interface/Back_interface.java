package _interface;
//���ܣ���ʾ������Ϣ�����û����붩Ʊ��Ϣ�������û�
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import _user.Back_tickets;
import _user.ShowTicketsMessageInText;
import _flight.List;
import _manager.ModifyFlight;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.Serializable;

public class Back_interface extends JFrame implements ActionListener,Serializable {
	private static String userName;
	private JTextArea text1 = new JTextArea(10,60);
	private JTextArea text2 = new JTextArea(10,40);
	private JScrollPane textroll1 = new JScrollPane(text1);
	private JScrollPane textroll2 = new JScrollPane(text2);
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JPanel jp5 = new JPanel();
	private JLabel jl1 = new JLabel("�����:");
	private JLabel jl2 = new JLabel("��Ʊ��:");
	private JTextField jt1 = new JTextField(20);
	private JTextField jt2 = new JTextField(20);
	private JButton jb1 = new JButton("ȡ��");
	private JButton jb2 = new JButton("��Ʊ");
	private JButton jb3 = new JButton("ˢ��");
	public Back_interface(String _name){
		super("��Ʊ");
		showlist();
		userName = _name;//���û�������������<
		setLayout(new GridLayout(3,1));
		add(jp1);add(jp2);add(jp3);
		jp1.add(textroll1);jp2.add(textroll2);
		//���õ�һ��������������
		textroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textroll1.setBounds(20,20,100,200);
		text1.setLineWrap(true); 
		text1.setEditable(false); 
		//���õڶ���������������
		textroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textroll2.setBounds(20,20,100,200);
		text2.setLineWrap(true); 
		text2.setEditable(false); 
		jp3.setLayout(new GridLayout(2,1));
		jp3.add(jp4);jp3.add(jp5);
		jp4.add(jl1);jp4.add(jt1);jp4.add(jl2);jp4.add(jt2);
		jp5.add(jb1);jp5.add(jb2);//jp5.add(jb3);
		jb1.addActionListener(this);jb2.addActionListener(this);jb3.addActionListener(this);
		setSize(700,600);
		setVisible(true);
		text2.setText(ShowTicketsMessageInText.showMessage(userName)+"\r\n");
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb1)
			dispose();
		else if(e.getSource()==jb3)
			showlist();
		else{
			int isBack = Back_tickets.back(jt1.getText(),jt2.getText(),userName);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			text2.append(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
			if(isBack==0)
				text2.append("����Ʊ�ɹ�����\r\n");
			else if(isBack == 1)
				text2.append("��������������󣡣�\r\n");
			else 
				text2.append("��"+jt1.getText()+"�ź������������Ʊ������"+jt2.getText()+"��!!\r\n");
			showlist();
			text2.append(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
			text2.append("\r\n"+ShowTicketsMessageInText.showMessage(userName)+"\r\n");
		}
	}
	public void showlist(){
		text1.setText("�����\t���\t�յ�\t��Ʊ��\t������Ʊ��\t���ʱ��\t����ʱ��\r\n");
		for(int i=1;i<=ModifyFlight.getMaxNum();i++){
			String[] temp = new String[7];
			List.list(i, temp);
			text1.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"Сʱ\r\n");
		}
	}
}