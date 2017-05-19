package _interface;
//���ܣ���ʾ������Ϣ�����û����붩Ʊ��Ϣ�������û�
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import _user.Back_tickets;
import _user.Book_tickets;
import _user.ShowTicketsMessageInText;
import _flight.List;
import _manager.ModifyFlight;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Modi_tick_interface extends JFrame implements ActionListener{
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
	private JLabel jl1 = new JLabel("�޸ĵĺ����:");
	private JLabel jl2 = new JLabel("Ҫ�ĳɵĺ����:");
	private JLabel jl3 = new JLabel("��Ʊ��:");
	private JTextField jt1 = new JTextField(5);
	private JTextField jt2 = new JTextField(5);
	private JTextField jt3 = new JTextField(5);
	private JButton jb1 = new JButton("ȡ��");
	private JButton jb2 = new JButton("�޸�");
	public Modi_tick_interface(String _name){
		super("�޸Ķ�Ʊ��Ϣ");
		showlist();
		userName = _name;//���û�������������<
		ShowTicketsMessageInText.showMessage(userName);
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
		jp4.add(jl1);jp4.add(jt1);jp4.add(jl2);jp4.add(jt2);jp4.add(jl3);jp4.add(jt3);
		jp5.add(jb1);jp5.add(jb2);
		jb1.addActionListener(this);jb2.addActionListener(this);
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
		else{
			int isBack1 = Back_tickets.back(jt1.getText(),jt3.getText(),userName);
			int isBook1 = Book_tickets.book(jt2.getText(),jt3.getText(),userName);	
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			text2.append(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
			if(isBack1==0&&isBook1==0)//�����Ʊ����Ʊ���ɹ�����ʾ�û��޸ĳɹ�
				text2.append("���޸ĳɹ�����\r\n");
			else if(isBack1==0&&isBook1==1){//�����Ʊ�ɹ�����Ʊ����Ŵ�������ʾ�û������Ұ��˵��������
				text2.append("��Ҫ�ĳɵĺ�����������󣡣�\r\n");
				Book_tickets.book(jt1.getText(),jt3.getText(),userName);	
			}
			else if(isBack1==0&&isBook1==2){//�����Ʊ�ɹ�����Ʊ��ΪƱ�������ʧ������ʾ�û��Ұ�Ʊ�����
				text2.append("��"+jt2.getText()+"�ź���Ʊ������"+jt3.getText()+"��!!\r\n");
				Book_tickets.book(jt1.getText(),jt3.getText(),userName);
			}
			else if(isBack1==1&&isBook1==0){//�����Ʊ�ɹ�����Ʊ��Ϊ����Ŵ����ʧ������ʾ�û�����Ʊ
				text2.append("Ҫ�ĵĺ������������!!\r\n");
				Back_tickets.back(jt2.getText(),jt3.getText(),userName);
			}else if(isBack1==2&&isBook1==0){//�����Ʊ�ɹ�����Ʊ��ΪƱ�������ʧ������ʾ�û�����Ʊ
				text2.append("��"+jt1.getText()+"�ź������������Ʊ������"+jt3.getText()+"��!!\r\n");
				Back_tickets.back(jt2.getText(),jt3.getText(),userName);
			}else if(isBack1==1&&isBook1==1){//���������������붼��
				text2.append("�����������!!!!\r\n");
			}else if(isBack1==1&&isBook1==2){//�����1���󣬺���2ϵͳƱ������
				text2.append("��Ҫ�޸ĵĺ������������!!"+jt2.getText()+"�ź���Ʊ������"+jt3.getText()+"��!!\r\n");
			}else if(isBack1==2&&isBook1==1){//����1�ѹ���Ʊ�����㣬�����2����
				text2.append("��"+jt1.getText()+"�ź������������Ʊ������"+jt3.getText()+"��!!Ҫ�ĳɵĺ������������!!\r\n");
			}else if(isBack1==2&&isBook1==2){//����1�ѹ���Ʊ�����㣬����2ϵͳƱ������
				text2.append("��"+jt1.getText()+"�ź������������Ʊ������"+jt3.getText()+"��!!"+jt2.getText()+"�ź���Ʊ������"+jt3.getText()+"��\r\n");
			}
				
		}
		showlist();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		text2.append(df.format(new Date()));
		text2.append("\r\n"+ShowTicketsMessageInText.showMessage(userName)+"\r\n");
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
