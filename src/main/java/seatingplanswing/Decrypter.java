package seatingplanswing;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.Key;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Decrypter {
	private static final String dbKey = "à¨Fe~}K;¨YW_FU&86\\£kW0E{w8#|0c=I";
	private static final File appFile = new File("C:\\Dev\\Workspace\\seatingplanswing\\properties.ini");
	
	public final static String encode(String dataToEncode)
	{
		String outputString = "";
		Key aesKey = null;
		try
		{
			aesKey = new SecretKeySpec(dbKey.getBytes(),"AES");	
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(dataToEncode.getBytes());
			outputString = Base64.getEncoder().encodeToString(encrypted);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return outputString;
	}
	
	public final static String decode(String dataToDecode)
	{
		String outputString = "";
		Key aesKey = null;
		try
		{
			aesKey = new SecretKeySpec(dbKey.getBytes(),"AES");	
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			outputString = new String(cipher.doFinal(Base64.getDecoder().decode(dataToDecode.getBytes())));
        }
		catch(Exception e)
		{
            e.printStackTrace();
        }
		return outputString;
	}

	public final static String getDBPassword()
	{
		Scanner scan;
		try {
			scan = new Scanner(appFile);
	    	if (scan.hasNextLine())
	    	{
	    		String str = scan.nextLine();
	    		scan.close();
	    		return decode(str);
	    	}
	    	else
	    	{
	    		scan.close();
	    		return "";
	    	}
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return "";
		}   

	}
}
