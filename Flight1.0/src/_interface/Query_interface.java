package _interface;
//���ܣ���ʾ������Ϣ�������������Ϣ��ѯ�������Ϣ�Ĵ���
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import _flight.List;
import _manager.ModifyFlight;
import _user.Search;
public class Query_interface extends JFrame implements ActionListener{
	private JTextArea text = new JTextArea(10,60);
	private JScrollPane textroll = new JScrollPane(text);
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp2_1 = new JPanel();
	private JPanel jp2_2 = new JPanel();
	private JLabel jl1 = new JLabel("��ʼ��ַ:");
	private JLabel jl2 = new JLabel("�յ��ַ:");
	private JTextField jt1 = new JTextField(20);
	private JTextField jt2 = new JTextField(20);
	private JButton jb1 = new JButton("ȡ��");
	private JButton jb2 = new JButton("��ѯ");
	public Query_interface(){
		super("��ѯ��Ʊ��Ϣ");
		setLayout(new GridLayout(2,1));
		add(jp1);add(jp2);
		jp1.add(textroll);
//		textroll.setVerticalScrollBarPolicy( 
//				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		textroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textroll.setBounds(20,20,100,200);

		text.setLineWrap(true); 
		text.setEditable(false); 
		showlist();
		jp2.add(jp2_1);jp2.add(jp2_2);
		jp2_1.add(jl1);jp2_1.add(jt1);jp2_1.add(jl2);jp2_1.add(jt2);
		jp2_2.add(jb1);jp2_2.add(jb2);
		jb1.addActionListener(this);jb2.addActionListener(this);
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
		if(e.getSource()==jb1)
			dispose();
		else{
			String[] num = new String[ModifyFlight.getMaxNum()+3];//��ŷ�����Ϣ
			String[] temp = new String[7];//�����ʱ������Ϣ
			Search.search(jt1.getText(), jt2.getText(),num,ModifyFlight.getMaxNum()+3);
			text.setText("�����\t���\t�յ�\t��Ʊ��\t������Ʊ��\t���ʱ��\t����ʱ��\r\n");
			int i = 0;
			while(num[i]!=null){//���������һֱѭ��
				if(num[i].equals("a")){
					text.append("����Ҫ�����:\r\n");
					i++;
				}
				else if(num[i].equals("b")){
					text.append("������Ҫ�����:\r\n");
					i++;
				}
				else if(num[i].equals("c")){
					text.append("�յ����Ҫ�����:\r\n");
					i++;
				}
				else{//���������Ϣ
					List.list(Integer.parseInt(num[i]), temp);
					i++;
					text.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"Сʱ\r\n");
				}
			}//while-end
		}//else-end
	}
	//��ʾ������Ϣ
	public void showlist(){
		text.setText("�����\t���\t�յ�\t��Ʊ��\t������Ʊ��\t���ʱ��\t����ʱ��\r\n");
		
		for(int i=1;i<=ModifyFlight.getMaxNum();i++){
			String[] temp = new String[7];
			List.list(i, temp);
			text.append(temp[0]+"\t"+temp[1]+"\t"+temp[2]+"\t"+temp[3]+"\t"+temp[4]+"\t"+temp[5]+"\t"+temp[6]+"Сʱ\r\n");
		}
	}
}

