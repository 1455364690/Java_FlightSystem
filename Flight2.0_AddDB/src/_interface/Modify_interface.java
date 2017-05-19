package _interface;
//���ܣ�ʵ����ɾ�ĵĴ��ڣ���ʾ������Ϣ
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import _flight.List;
import _manager.ModifyFlight;
public class Modify_interface extends JFrame implements ActionListener{
	private JTextArea text = new JTextArea(10,60);
	private JScrollPane textroll = new JScrollPane(text);
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private Button _add = new Button("����һ������");
	private Button _del = new Button("ɾ��һ������");
	private Button _modi= new Button("�޸�һ������");
	private Button _flush = new Button("ˢ���б�");
	public Modify_interface(){
		super("�޸Ķ�Ʊ��Ϣ");
		setLayout(new GridLayout(2,1));
		add(jp1);add(jp2);
		jp1.add(textroll);//������������������
//		textroll.setVerticalScrollBarPolicy( 
//				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		textroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//���ù�����һֱ��
		textroll.setBounds(20,20,100,200);//���ù�������С

		text.setLineWrap(true); //�������Զ�����
		text.setEditable(false);//�ɱ༭
		
		showlist();
			
		//��Ӱ�ť
		jp2.add(_add);
		jp2.add(_del);
		jp2.add(_modi);
		jp2.add(_flush);
		//��ӹ���
		_add.addActionListener(this);
		_del.addActionListener(this);
		_modi.addActionListener(this);
		_flush.addActionListener(this);
		setSize(700,400);
		setVisible(true);
		this.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						dispose();
					}
				});
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==_add){
			Modify_add_interface add = new Modify_add_interface();
			showlist();
		}else if(e.getSource()==_del){
			String temp1 = JOptionPane.showInputDialog("������Ҫɾ���ĺ������");
			ModifyFlight.delFlight(Integer.parseInt(temp1));
			showlist();
		}
		else if(e.getSource()==_modi){
			Modify_modi_interface mo = new Modify_modi_interface();
			showlist();
		}else if(e.getSource()==_flush){
			showlist();
		}
	}
	public void showlist(){
		text.setText("�����\t���\t�յ�\t��Ʊ��\t������Ʊ��\t���ʱ��\t����ʱ��\r\n");
		
		for(int i=1;i<=ModifyFlight.getMaxNum();i++){
			String[] temp = new String[7];
			List.list(i, temp);
			text.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"Сʱ\r\n");
		}
	}
}
