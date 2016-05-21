package com.nimbusds.jose.crypto;


import net.jcip.annotations.ThreadSafe;

import com.nimbusds.jose.CompressionAlgorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.util.DeflateUtils;


/**
 * Deflate (RFC 1951) helper methods, intended for use by JWE encrypters and
 * decrypters. This class is thread-safe.
 *
 * @author Vladimir Dzhuvinov
 * @version 2014-07-08
 */
@ThreadSafe
class DeflateHelper {


	/**
	 * Applies compression to the specified plain text if requested.
	 *
	 * @param jweHeader The JWE header. Must not be {@code null}.
	 * @param bytes     The plain text bytes. Must not be {@code null}.
	 *
	 * @return The bytes to encrypt.
	 *
	 * @throws JOSEException If compression failed or the requested 
	 *                       compression algorithm is not supported.
	 */
	public static byte[] applyCompression(final JWEHeader jweHeader, final byte[] bytes)
		throws JOSEException {

		CompressionAlgorithm compressionAlg = jweHeader.getCompressionAlgorithm();

		if (compressionAlg == null) {

			return bytes;

		} else if (compressionAlg.equals(CompressionAlgorithm.DEF)) {

			try {
				return DeflateUtils.compress(bytes);

			} catch (Exception e) {

				throw new JOSEException("Couldn't compress plain text: " + e.getMessage(), e);
			}

		} else {

			throw new JOSEException("Unsupported compression algorithm: " + compressionAlg);
		}
	}


	/**
	 * Applies decompression to the specified plain text if requested.
	 *
	 * @param jweHeader The JWE header. Must not be {@code null}.
	 * @param bytes     The plain text bytes. Must not be {@code null}.
	 *
	 * @return The output bytes, decompressed if requested.
	 *
	 * @throws JOSEException If decompression failed or the requested 
	 *                       compression algorithm is not supported.
	 */
	public static byte[] applyDecompression(final JWEHeader jweHeader, final byte[] bytes)
		throws JOSEException {

		CompressionAlgorithm compressionAlg = jweHeader.getCompressionAlgorithm();

		if (compressionAlg == null) {

			return bytes;

		} else if (compressionAlg.equals(CompressionAlgorithm.DEF)) {

			try {
				return DeflateUtils.decompress(bytes);

			} catch (Exception e) {

				throw new JOSEException("Couldn't decompress plain text: " + e.getMessage(), e);
			}

		} else {

			throw new JOSEException("Unsupported compression algorithm: " + compressionAlg);
		}
	}
}