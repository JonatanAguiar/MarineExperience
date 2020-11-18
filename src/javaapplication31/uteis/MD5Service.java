/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication31.uteis;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author jonat
 */
public class MD5Service {
    public String md5(String senha) {
        String password = senha;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset(); // <---- Reseta antes de fazer o password
            m.update(password.getBytes(), 0, password.length());
            BigInteger password1 = new BigInteger(1, m.digest());
            password = String.format("%1$032X", password1);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return password;
    }
}
