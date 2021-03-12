package it.eng.decido.security;

public class CaesarCipher {
	private static String values = "-+%£$1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzòàèìù !?*\\	";
	
	/**
	 * Encrypt string with CaesarCipher
	 * 
	 * @param text
	 * @param key
	 * @return String
	 */
	public static String encrypt(String text, int key) {
		char charEnc;
		int valEnc;
		int newEnc;
		String encrypted = "";
		for (int i = 0; i < text.length(); i++) {
			charEnc = text.charAt(i);
			valEnc = values.indexOf(charEnc);
			newEnc = (key + valEnc) % values.length();
			encrypted += values.charAt(newEnc);
		}
		return encrypted;
	}

	/**
	 * Decrypt string with CaesarCipher
	 * 
	 * @param text
	 * @param key
	 * @return String
	 */
	public static String decrypt(String text, int key) {
		char charEnc;
		int valEnc;
		int newEnc;
		String decoded = "";
		for (int i = 0; i < text.length(); i++) {
			charEnc = text.charAt(i);
			valEnc = values.indexOf(charEnc);
			newEnc = (valEnc - key) % values.length();
			// check for negative
			if (newEnc < 0) {
				newEnc += Math.abs(values.length());
			}
			decoded += values.charAt(newEnc);
		}
		return decoded;
	}
 
    public static void main(String[] args) {
        String originalText = "Danny JHONSON	PeoPPle \\n\\l MIMMO ! ? !*** -+%£1234567890";
        
        int shiftCount = 1;
        System.out.println("Caesar Cipher Example");
        System.out.println("Encryption");
        System.out.println("Text  : " + originalText);
        System.out.println("Shift : " + shiftCount);
        
        String cipher = encrypt(originalText, shiftCount).toString();
        System.out.println("Encrypted Cipher: " + cipher);
        System.out.println("Decryption");
        System.out.println("Encrypted Cipher: " + cipher);
        System.out.println("Shift : " + shiftCount);
        String decryptedPlainText = decrypt(cipher, shiftCount).toString();
        System.out.println("Decrypted Plain Text  : " + decryptedPlainText);
        
        if(decryptedPlainText.equals(originalText)) {
        	System.out.println("OK: originalText and decryptedPlainText are EQUALS !");
        } else {
        	System.out.println("KO: originalText and decryptedPlainText are NOT EQUALS !!!!");
        }
        
    }
    
}
