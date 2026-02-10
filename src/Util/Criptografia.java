package Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

    /**
     * Transforma uma string (senha plana) em um Hash MD5 de 32 caracteres.
     */
    public static String md5(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Converte a senha para bytes e gera o digest
            byte[] messageDigest = md.digest(senha.getBytes());
            
            // Converte o array de bytes em representação hexadecimal
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            
            // Garante que a string tenha 32 caracteres (adicionando zeros à esquerda)
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao buscar algoritmo de criptografia", e);
        }
    }
}