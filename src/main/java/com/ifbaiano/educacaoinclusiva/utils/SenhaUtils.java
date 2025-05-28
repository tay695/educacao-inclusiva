package com.ifbaiano.educacaoinclusiva.utils;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class SenhaUtils {

	private static final int ITERACOES = 65536;
	private static final int COMPRIMENTO_CHAVE = 256;
	private static final String ALGORITMO = "PBKDF2WithHmacSHA256";

	private SenhaUtils() {
	}



	public static String gerarSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}

	public static boolean verificarSenha(String senhaDigitada, String saltArmazenado, String hashArmazenado) {
		if (senhaDigitada == null || saltArmazenado == null || hashArmazenado == null) {
			return false;
		}
		String hashCalculado = gerarHashSenha(senhaDigitada, saltArmazenado);
		return hashCalculado.equals(hashArmazenado);
	}
	
	public static String gerarHashSenha(String senha, String salt) {
		if (senha == null || salt == null) {
			throw new IllegalArgumentException("Senha e salt não podem ser nulos");
		}
		try {
			PBEKeySpec spec = new PBEKeySpec(senha.toCharArray(), Base64.getDecoder().decode(salt), ITERACOES,
					COMPRIMENTO_CHAVE);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITMO);
			byte[] hash = skf.generateSecret(spec).getEncoded();
			return Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException("Erro ao gerar hash da senha", e);
		}
	}

}
