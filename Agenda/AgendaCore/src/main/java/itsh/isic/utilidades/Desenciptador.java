package itsh.isic.utilidades;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

//import java.util.Base64;  
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import itsh.isic.exception.DesencriptadoException;

public class Desenciptador {
	public static final String DES_ENCRIP_ESQUEMA = "DESede";
	private static final String FORMATO = "UTF8";
	private Cipher cipher;
	private String myEncryptionKey;
	private SecretKey key;

	public Desenciptador() throws DesencriptadoException {
		try {
			this.myEncryptionKey = "LFNEGMTZ20172022";
			this.cipher = Cipher.getInstance(DES_ENCRIP_ESQUEMA);
			this.key = SecretKeyFactory.getInstance(DES_ENCRIP_ESQUEMA)
					.generateSecret(new DESedeKeySpec(this.myEncryptionKey.getBytes(FORMATO)));
		} catch (GeneralSecurityException | UnsupportedEncodingException encryptionException) {
			throw new DesencriptadoException("Error generando instancia de Desenciptador class", encryptionException);
		}
	}

	public String encrypt(final String unencryptedString) throws DesencriptadoException {
		char[] encryptedChar = null;
		try {
			this.cipher.init(Cipher.ENCRYPT_MODE, this.key);
			final byte[] plainText = unencryptedString.getBytes(FORMATO);
			final byte[] encryptedText = this.cipher.doFinal(plainText);
			encryptedChar = Base64Coder.encode(encryptedText);
		} catch (InvalidKeyException | IllegalBlockSizeException | UnsupportedEncodingException | BadPaddingException e) {
			throw new DesencriptadoException(e);
		}
		return new String(encryptedChar);
	}

	public String decrypt(final String encryptedString) throws DesencriptadoException {
		String decryptedText = null;
		try {
			this.cipher.init(Cipher.DECRYPT_MODE, this.key);
			final byte[] encryptedText = Base64Coder.decodeLines(encryptedString);
			final byte[] plainText = this.cipher.doFinal(encryptedText);
			decryptedText = bytes2String(plainText);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new DesencriptadoException(e);
		}
		return decryptedText;
	}

	private static String bytes2String(final byte[] bytes) {
		final StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++)
			stringBuffer.append((char) bytes[i]);
		return stringBuffer.toString();
	}

	/**
	 * Testing the DES Encryption And Decryption Technique
	 */
	public static void main(String args[]) throws Exception {
		Desenciptador myEncryptor = new Desenciptador();

		String stringToEncrypt = "userNotExist";
		String encrypted = myEncryptor.encrypt(stringToEncrypt);
		String decrypted = myEncryptor.decrypt(encrypted);

		System.out.println("String To Encrypt: " + stringToEncrypt);
		System.out.println("Encrypted Value :" + encrypted);
		System.out.println("Decrypted Value :" + decrypted);
	}
}
