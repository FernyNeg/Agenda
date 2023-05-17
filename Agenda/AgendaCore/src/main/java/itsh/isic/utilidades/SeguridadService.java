package itsh.isic.utilidades;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import itsh.isic.exception.DesencriptadoException;
import itsh.isic.exception.EncriptadoException;

@Service
public class SeguridadService {

	private static final int SALT_LENGTH = 8;
	private static final int PASS_LENGTH = 10;
	private static final String ALLOWED_SALT_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final String ALLOWED_PASS_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	public SeguridadService() {
	}

	public static final String generateSalt() {
		final SecureRandom random = new SecureRandom();
		final StringBuilder salt = new StringBuilder();
		for (int i = 0; i < SALT_LENGTH; i++) {
			salt.append(ALLOWED_SALT_CHARS.charAt(random.nextInt(ALLOWED_SALT_CHARS.length())));
		}
		return salt.toString();
	}

	public static final String generatePass() {
		final SecureRandom random = new SecureRandom();
		final StringBuilder salt = new StringBuilder();
		for (int i = 0; i < PASS_LENGTH; i++) {
			salt.append(ALLOWED_PASS_CHARS.charAt(random.nextInt(ALLOWED_PASS_CHARS.length())));
		}
		return salt.toString();
	}

	public static final String encrypt(final String plaintext, final String salt) {
		if (plaintext == null) {
			throw new NullPointerException();
		}
		if (salt == null) {
			throw new NullPointerException();
		}

		try {
			final MessageDigest md = MessageDigest.getInstance("SHA");
			md.update((plaintext + salt).getBytes("UTF-8"));
			return Base64Coder.encodeLines(md.digest()).trim();
		} catch (NoSuchAlgorithmException e) {
			throw new EncriptadoException(e);
		} catch (UnsupportedEncodingException e) {
			throw new EncriptadoException(e);
		}
	}

	public static void main(String[] args) throws DesencriptadoException {
		final String username = "pbeller";
		final String password = "bad_password";
		final Desenciptador myEncryptor = new Desenciptador();
		final String salt = "jdfifnusdpo";// SecurityService.generateSalt();
		final String pass = SeguridadService.encrypt(password, Base64Coder.encodeString(salt));
		final String encryptedUsername = myEncryptor.encrypt(username);
		System.out.println("Username: " + encryptedUsername);
		System.out.println("Password: " + pass);
		System.out.println("Salt: " + salt);
		System.out.println("/////////////////////----------------/////////////////////");
	}

}