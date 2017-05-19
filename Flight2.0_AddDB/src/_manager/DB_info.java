package _manager;

import java.sql.Connection;
import java.sql.Statement;

public class DB_info {
		public final static String DB_URL = "jdbc:mysql://10.133.1.196:3306/mysql?characterEncoding=utf8&useSSL=false";//数据库路径
		public final static String DB_USER = "root";//数据库用户名
		public final static String DB_PW = "root";//数据密码
		public final static String USER_TABLE = "_userNameAndPassword";//用户信息表名
		public final static String FLIGHT_TABLE = "_flight";//航班信息表名
		public final static String USER_FLIGHT_TABLE = "_user_flight";//用户购票信息表名
		public static Connection conn = null;//连接
		public static Statement stmt = null;//数据库状态
}
