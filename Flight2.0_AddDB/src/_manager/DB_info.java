package _manager;

import java.sql.Connection;
import java.sql.Statement;

public class DB_info {
		public final static String DB_URL = "jdbc:mysql://10.133.1.196:3306/mysql?characterEncoding=utf8&useSSL=false";//���ݿ�·��
		public final static String DB_USER = "root";//���ݿ��û���
		public final static String DB_PW = "root";//��������
		public final static String USER_TABLE = "_userNameAndPassword";//�û���Ϣ����
		public final static String FLIGHT_TABLE = "_flight";//������Ϣ����
		public final static String USER_FLIGHT_TABLE = "_user_flight";//�û���Ʊ��Ϣ����
		public static Connection conn = null;//����
		public static Statement stmt = null;//���ݿ�״̬
}
