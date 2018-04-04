// package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1_file {
	public static void main(String [] args) throws NoSuchAlgorithmException, IOException {
		boolean result = verifyChecksum("D:\\Programming\\Java\\test.java.txt", "135c3150cb1b9b442dc7a22bf3f3d4b28606a13f");
		System.out.println("- Does the file's checksum matches the expected one?: " + result);
	}
	
	/**
	 * Verifies file's SHA1 checksum
	 * @param Filepath and name of a file that is to be verified
	 * @return true/false
	 */
	 
	 public static boolean verifyChecksum(String file, String testChecksum) throws NoSuchAlgorithmException, IOException {
		 MessageDigest sha1 = MessageDigest.getInstance("SHA1");
		 FileInputStream fis = new FileInputStream(file);
		 
		 byte[] data = new byte[1024];
		 int read = 0;
		 while ((read = fis.read(data)) != -1) {
			 sha1.update(data, 0, read);
		 };
		 byte[] hashBytes = sha1.digest();
		 
		 StringBuffer sb = new StringBuffer();
		 for (int i = 0; i < hashBytes.length; i++) {
			 sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
		 }
		 
		 String fileHash = sb.toString();
		 
		 return fileHash.equals(testChecksum);
	 }
}