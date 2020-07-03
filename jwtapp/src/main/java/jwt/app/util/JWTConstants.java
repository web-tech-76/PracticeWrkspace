package jwt.app.util;

import java.util.Calendar;
import java.util.Date;

public class JWTConstants {

	
	private static Calendar calender = Calendar.getInstance();
	
	
	private static Date getIssueDate() {
			return calender.getTime();
	}
	
	
	private static Date getExpiryDate() {
		calender.add(Calendar.MONTH, 6);
		return calender.getTime();
	}
	
	public static final String SECRET= "12345";
	
	public static final String ISSUER= "localhost";
	
	public static final Date ISSUED_AT= getIssueDate();
	
	public static final Date EXPIRY_DATE= getExpiryDate();
	
	public static final String HEADER= "Authentication";
	
	
}
