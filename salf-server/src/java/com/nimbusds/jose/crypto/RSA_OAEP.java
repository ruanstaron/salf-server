package com.nimbusds.jose.crypto;


import java.security.Provider;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import net.jcip.annotations.ThreadSafe;

import com.nimbusds.jose.JOSEException;


/**
 * RSAES OAEP methods for Content Encryption Key (CEK) encryption and 
 * decryption. Uses the BouncyCastle.org provider. This class is thread-safe
 *
 * @author Vladimir Dzhuvinov
 * @version 2014-01-24
 */
@ThreadSafe
class RSA_OAEP {


	/**
	 * Encrypts the specified Content Encryption Key (CEK).
	 *
	 * @param pub      The public RSA key. Must not be {@code null}.
	 * @param cek      The Content Encryption Key (CEK) to encrypt. Must
	 *                 not be {@code null}.
	 * @param provider The JCA provider, or {@code null} to use the default
	 *                 one.
	 *
	 * @return The encrypted Content Encryption Key (CEK).
	 *
	 * @throws JOSEException If encryption failed.
	 */
	public static byte[] encryptCEK(final RSAPublicKey pub, final SecretKey cek, final Provider provider)
		throws JOSEException {

		try {
			Cipher cipher = CipherHelper.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding", provider);
			cipher.init(Cipher.ENCRYPT_MODE, pub, new SecureRandom());
			return cipher.doFinal(cek.getEncoded());

		} catch (Exception e) {
			// java.security.NoSuchAlgorithmException
			// java.security.NoSuchPaddingException
			// java.security.InvalidKeyException
			// javax.crypto.IllegalBlockSizeException
			// javax.crypto.BadPaddingException
			throw new JOSEException(e.getMessage(), e);
		}
	}

	
	/**
	 * Decrypts the specified encrypted Content Encryption Key (CEK).
	 *
	 * @param priv         The private RSA key. Must not be {@code null}.
	 * @param encryptedCEK The encrypted Content Encryption Key (CEK) to
	 *                     decrypt. Must not be {@code null}.
	 * @param provider     The JCA provider, or {@code null} to use the
	 *                     default one.
	 *
	 * @return The decrypted Content Encryption Key (CEK).
	 *
	 * @throws JOSEException If decryption failed.
	 */
	public static SecretKey decryptCEK(final RSAPrivateKey priv, 
		                           final byte[] encryptedCEK, final Provider provider)
		throws JOSEException {

		try {
			Cipher cipher = CipherHelper.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding", provider);
			cipher.init(Cipher.DECRYPT_MODE, priv);
			return new SecretKeySpec(cipher.doFinal(encryptedCEK), "AES");

		} catch (Exception e) {
			// java.security.NoSuchAlgorithmException
			// java.security.NoSuchPaddingException
			// java.security.InvalidKeyException
			// javax.crypto.IllegalBlockSizeException
			// javax.crypto.BadPaddingException
			throw new JOSEException(e.getMessage(), e);
		}
	}


	/**
	 * Prevents public instantiation.
	 */
	private RSA_OAEP() { }
}