package _manager;

import java.sql.Connection;
import java.sql.Statement;

public class ManagerInfo {
	//59.110.159.191
	//root
	//sj521LJ!!
		public final static String systemN = "root";
		public final static String systemP = "root";
		public final static String DB_URL = "jdbc:mysql://127.0.0.1/mysql?characterEncoding=utf8&useSSL=false";//���ݿ�·��
		public final static String DB_USER = "root";//���ݿ��û���
		public final static String DB_PW = "root";//��������
		public final static String USER_TABLE = "_userNameAndPassword";//�û���Ϣ����
		public final static String FLIGHT_TABLE = "_flight";//������Ϣ����
		public final static String USER_FLIGHT_TABLE = "_user_flight";//�û���Ʊ��Ϣ����
		public static Connection conn = null;//����
		public static Statement stmt = null;//���ݿ�״̬
}
