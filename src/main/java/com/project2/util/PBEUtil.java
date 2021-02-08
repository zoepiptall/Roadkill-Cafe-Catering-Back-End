package com.project2.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Component;

@Component
public class PBEUtil {
	private static  Random RANDOM = new SecureRandom();
	private static  String ALNUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static  int ITERATIONS = 10_000;
	private static  int KEY_LENGTH = 256;

	public String createSalt(final int length) {
		final StringBuilder salt = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			salt.append(ALNUM.charAt(RANDOM.nextInt(ALNUM.length())));
		}
		return salt.toString();
	}

	public String generateSecurePassword(final String password, final String salt) {
		final byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
		return Base64.getEncoder().encodeToString(securePassword);
	}

	public boolean verifyUserPassword(final String providedPassword, final String securedPassword,
											 final String salt) {
		final String newSecurePassword = generateSecurePassword(providedPassword, salt);
		return newSecurePassword.equalsIgnoreCase(securedPassword);
	}

	private static byte[] hash(final char[] password, final byte[] salt) {
		final PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		Arrays.fill(password, Character.MIN_VALUE);
		try {
			final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return secretKeyFactory.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			throw new AssertionError("Error while hashing");
		} finally {
			spec.clearPassword();
		}
	}
}
