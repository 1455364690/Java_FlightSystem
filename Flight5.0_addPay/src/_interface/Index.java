package _interface;
//���ܣ������棬
//���ߣ���ӻԣ�ʱ�䣺2017/05/07
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Index extends JFrame implements ActionListener,Runnable{
	private static final long serialVersionUID = 1L;
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JPanel jp5 = new JPanel();
	private JPanel jp6 = new JPanel();
	private JButton modify = new JButton("�޸ĺ�����Ϣ");
	private JButton query = new JButton("��ѯ������Ϣ");
	private JButton book_tickets = new JButton("��   Ʊ ");
	private JButton back_tickets = new JButton("��   Ʊ");
	private JButton modify_tickets = new JButton("�޸Ķ�Ʊ��Ϣ");
	private JButton register = new JButton("ע   ��");
	private JTextPane text2 = new JTextPane();
	private JTextPane text1 = new JTextPane();
	Image iocn = Toolkit.getDefaultToolkit().getImage("img//icon.png");
	public Index(){
		super("��ҳ");
		super.setIconImage(iocn);
		setLocation(400, 150);
		setSize(500,400);
		this.setResizable(false);
		setLayout(new GridLayout(3,1));
		//���ñ���ͼƬ�����������ؼ�����Ϊ͸��
		ImageIcon background = new ImageIcon("img//bg.png");
		JLabel bglb = new JLabel(background);
		bglb.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(bglb, new Integer(Integer.MIN_VALUE));
        setVisible(true);
        //�������ؼ�����Ϊ͸��
        jp1.setOpaque(false);
        jp2.setOpaque(false);
        jp3.setOpaque(false);
        jp4.setOpaque(false);
        jp5.setOpaque(false);
        jp6.setOpaque(false);
        modify.setPreferredSize(new Dimension(150, 30));
        query.setPreferredSize(new Dimension(150, 30));
        book_tickets.setPreferredSize(new Dimension(150, 30));
        back_tickets.setPreferredSize(new Dimension(150, 30));
        modify_tickets.setPreferredSize(new Dimension(150, 30));
        register.setPreferredSize(new Dimension(150, 30));
        //������Ļ
        jp1.add(text1);
        text1.setFont(new Font("�����п�",Font.PLAIN,60));
        text1.setOpaque(false);
        text1.setEditable(false);
        //�������ı�����
        jp2.add(text2);
        text2.setEditable(false);
        text2.setPreferredSize(new Dimension(450,100));
        text2.setOpaque(false);
        text2.setFont(new Font("����",Font.BOLD,20));
        //text2.setText("<html><A href='http://www.baidu.com'>test</A></html>");
        //��ӿؼ�
        add(jp1);
		add(jp2);
		add(jp3);
		jp3.setLayout(new GridLayout(2,1));
		jp3.add(jp5);
		jp3.add(jp6);
		jp5.add(modify);jp5.add(query);
		jp5.add(book_tickets);jp6.add(back_tickets);
		jp6.add(modify_tickets);jp6.add(register);
		//��Ӽ�����
		modify.addActionListener(this);
		query.addActionListener(this);
		book_tickets.addActionListener(this);
		back_tickets.addActionListener(this);
		modify_tickets.addActionListener(this);
		register.addActionListener(this);
		//���ð�ť����
		modify.setFont(new Font("��Բ",Font.BOLD,15));
		query.setFont(new Font("��Բ",Font.BOLD,15));
		book_tickets.setFont(new Font("��Բ",Font.BOLD,15));
		back_tickets.setFont(new Font("��Բ",Font.BOLD,15));
		modify_tickets.setFont(new Font("��Բ",Font.BOLD,15));
		register.setFont(new Font("��Բ",Font.BOLD,15));
		modify.setBackground(new Color(155,155,255));
		query.setBackground(new Color(155,255,155));
		book_tickets.setBackground(new Color(255,155,155));
		back_tickets.setBackground(new Color(255,155,255));
		modify_tickets.setBackground(new Color(155,255,255));
		register.setBackground(new Color(255,255,155));
		//������Ļ���½��߳�
		Thread mw = new Thread(this);
		mw.start();
	}
	public void actionPerformed(ActionEvent e){
		//����Ա����
		if(e.getSource()==modify){
			Manager_interface.managerLogin();
		}
		//��ѯ����
		else if(e.getSource()==query){
			Query_interface test2 = new Query_interface();
			test2.setEnabled(true);
		}
		//��Ʊ����
		else if(e.getSource()==book_tickets){
			Login_interface login = new Login_interface(0);//0��ʾ��Ʊ
			login.setEnabled(true);
		}
		//��Ʊ����
		else if(e.getSource()==back_tickets){
			Login_interface login = new Login_interface(1);//1Ϊ��Ʊ
			login.setEnabled(true);
		}
		//��Ʊ����
		else if(e.getSource()==modify_tickets){
			Login_interface login = new Login_interface(2);//2Ϊ��Ʊ
			login.setEnabled(true);
		}
		//ע�Ṧ��
		else if(e.getSource()==register){
			Register_interface	reg = new Register_interface();
			reg.setEnabled(true);
		}
	}
	//������Ļ�͹�������
	public void run(){
		//������Ļ������
		text1.setText("                              ABCD���չ�˾��ӭ��!!");
		//ѭ���������ŵ�����
		String[] str1 = {".  ��  ��  һ  �� �� ��     \r\n",
						".  ABCD�� �� �� ˾ �� ҵ �� \r\n",
						".  ֻ Ҫ �� �� ѧ �� ֤\r\n",
						".  �� �� �� �� ��� �� ��\r\n",
						".  �� ! û �� ��\r\n",
						".  �� �� �� �� ϰ ��\r\n",
						".  �� �� �� �� ϰ  ��\r\n",};
		//������Ļ��������
		int m = 0;
		for(int k = 0;k<100;k++){
			for(int j = 0;j<33;j++){
				try{
					Thread.sleep(200);
				}catch(Exception e){}
				text1.setText(text1.getText() + "    ");
			}
			text2.setText(text2.getText()+(m+1)+str1[m]);
			if(m>=3)
				text2.setText(text2.getText().replace(((m-2)+str1[m-3]),""));
			if(m<=2)
				text2.setText(text2.getText().replace(((m+5)+str1[m+4]),""));
			m++;
			m=m%7;
			text1.setText("                                                                  ABCD���չ�˾��ӭ��!!");
		}
		//�߳̽���
		Thread.interrupted();
	}
	
}
