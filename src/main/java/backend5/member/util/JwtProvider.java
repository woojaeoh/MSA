package backend5.member.util;

import lombok.extern.slf4j.Slf4j;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class JwtProvider {

    KeyPairGenerator keyPairGenerator = null;
    public KeyPair makeRsaKey() {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }

        KeyPair pa = keyPairGenerator.generateKeyPair();
//        String privateKey = new String(Base64.getEncoder().encode(pa.getPrivate().getEncoded()));
//        String publicKey = new String(Base64.getEncoder().encode(pa.getPublic().getEncoded()));
        log.info("privateKey = {}",new String(Base64.getEncoder().encode(pa.getPrivate().getEncoded())));
        log.info("publicKey = {}",new String(Base64.getEncoder().encode(pa.getPublic().getEncoded())));

        return pa;
    }
}
