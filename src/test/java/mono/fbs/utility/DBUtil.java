package mono.fbs.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class DBUtil {
	private static Connection tryOracleADM(){
		OracleDataSource ods;
		Connection conn = null;
		try {
			ods = new OracleDataSource();
			ods.setDriverType("oci");
			ods.setNetworkProtocol("tcp");
			ods.setUser("PRJVRB");
			ods.setPassword("tvrb1234");
			ods.setURL("jdbc:oracle:thin:@quest31.nus.edu.sg:1523:ADMT");
			conn = ods.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static boolean hasBookingByRefNo(String refNo){
		String sql = "select * from ROOM_BOOKING where BKING_N=?";
		PreparedStatement stmt = null;
		Connection Ocon = null;
		ResultSet rset = null;
		boolean hasResult = false;
		
		try {
			Ocon = tryOracleADM();
			Ocon.setAutoCommit(false);
			stmt = Ocon.prepareStatement(sql);
			stmt.setString(1, refNo);
			rset = stmt.executeQuery();
			hasResult = rset.next();
			Ocon.commit();
	        Ocon.setAutoCommit(true);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (stmt != null){
    			try{
    				stmt.close();
    				stmt = null;
    			}catch (Exception e){}
    		}
        	if (rset != null){
    			try{
    				rset.close();
    				rset = null;
    			}catch (Exception e){}
    		}
    		if (Ocon != null){
    			try{
    				Ocon.close();
    				Ocon = null;
    			}catch (Exception e){}
    		}
    		return hasResult;
		}
	}
	
	public static void insertEntry(String ref){
		String sql = "insert into ROOM_BOOKING (BKING_D, BKING_END_DTM, BKING_PPOSE_T, BKING_REQ_D, BKING_ST_DTM, BKING_STS_C, BKING_GUEST_Q, BKING_N, USER_TP_C, ROOM_REF_N,USER_I, ACK_F) values ( sysdate, sysdate+1, 'Test purpose', sysdate, sysdate, 'C', '1', ?, 'STF', '3072', 'CCELVT', 'Y')";
		PreparedStatement stmt = null;
		Connection Ocon = null;
		ResultSet rset = null;
		
		try {
			Ocon = tryOracleADM();
			Ocon.setAutoCommit(false);
			stmt = Ocon.prepareStatement(sql);
			stmt.setString(1, ref);
			stmt.executeUpdate();
			Ocon.commit();
	        Ocon.setAutoCommit(true);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (stmt != null){
    			try{
    				stmt.close();
    				stmt = null;
    			}catch (Exception e){}
    		}
        	if (rset != null){
    			try{
    				rset.close();
    				rset = null;
    			}catch (Exception e){}
    		}
    		if (Ocon != null){
    			try{
    				Ocon.close();
    				Ocon = null;
    			}catch (Exception e){}
    		}
		}
	}
	
	public static void deleteEntry(String ref){
		String sql = "delete from ROOM_BOOKING where BKING_N=?";
		PreparedStatement stmt = null;
		Connection Ocon = null;
		ResultSet rset = null;
		
		try {
			Ocon = tryOracleADM();
			Ocon.setAutoCommit(false);
			stmt = Ocon.prepareStatement(sql);
			stmt.setString(1, ref);
			stmt.executeUpdate();
			Ocon.commit();
	        Ocon.setAutoCommit(true);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (stmt != null){
    			try{
    				stmt.close();
    				stmt = null;
    			}catch (Exception e){}
    		}
        	if (rset != null){
    			try{
    				rset.close();
    				rset = null;
    			}catch (Exception e){}
    		}
    		if (Ocon != null){
    			try{
    				Ocon.close();
    				Ocon = null;
    			}catch (Exception e){}
    		}
		}
	}

}
