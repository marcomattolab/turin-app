package it.eng.decido.security;

public class CustomCipher {
	private static final int DEFAULT_SHIFT = 3;

	/**
	 * Encodes the modern Caesar Cipher by offsetting the characters by +3.
	 * 
	 * @param uncoded the word or words to encode
	 * @return the encoded phrase
	 */
	public static String encode(String uncoded) {
		return encode(uncoded, DEFAULT_SHIFT);
	}
	
	/**
	 * Decodes the modern Caesar Cipher by offsetting the characters by -3.
	 * 
	 * @param encoded the word or words to decode
	 * @return the decoded phrase
	 */
	public static String decode (String encoded) {
		return decode(encoded, DEFAULT_SHIFT);
	}
	
	/**
	 * Encodes the modern Caesar Cipher by offsetting the characters by passed parameter.
	 * 
	 * @param uncoded the word or words to encode
	 * @param offset shift
	 * @return the encoded phrase
	 */
	public static String encode(String uncoded, int shift) {
		char [] coder = uncoded.toCharArray();
		for (int i = 0; i < coder.length; i++) {
			coder[i] = (char)(coder[i] + shift);
		}
		return String.valueOf(coder);
	}
	
	/**
	 * Decodes the modern Caesar Cipher by offsetting the characters by passed parameter.
	 * 
	 * @param encoded the word or words to decode
	 * @param offset shift
	 * @return the decoded phrase
	 */
	public static String decode (String encoded, int shift) {
		char [] coder = encoded.toCharArray();
		for (int i = 0; i < coder.length; i++) {
			coder[i] = (char)(coder[i] - shift);
		}
		return String.valueOf(coder);
	}
	
	
	public static void main(String[] args) {
		System.out.println("---------------------------------");
		System.out.println("--------  Caesar Cipher  --------");
		System.out.println("---------------------------------");
		String originalText = "Danny JHONSON	Mànèlidà PeoPPle \\\\n\\\\l MIMMO ! ? !*** -+%£1234567890";
		System.out.println("originalText => "+originalText);
		
		String encripted = CustomCipher.encode(originalText);
		System.out.println("encripted => "+encripted);
		
		String decripted = CustomCipher.decode(encripted);
		System.out.println("decripted => "+decripted);
		System.out.println("---------------------------------");
		if (decripted.equals(originalText)) {
			System.out.println("OK ===> decripted == originalText");
		} else {
			System.out.println("KO ===> decripted != originalText (Action should be provided!!)");
		}
		System.out.println("---------------------------------");
	}
	
}
