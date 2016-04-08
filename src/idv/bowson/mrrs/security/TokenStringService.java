package idv.bowson.mrrs.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

/**
 * 產生Token String之服務
 *
 */
@Component
public class TokenStringService {
    private static final String PRNG_NAME = "SHA1PRNG";
    private static final String DIGEST_ALGORITHM_NAME = "SHA-256";
    private static final int PRNG_PRODUCT_LENGTH = 32;

    private SecureRandom secureRandom;
    private MessageDigest messageDigest;

    /**
     * 建構子
     *
     * @throws NoSuchAlgorithmException 用以產生Token之演算法不被目前所在環境支援
     */
    public TokenStringService() throws NoSuchAlgorithmException {
        this.secureRandom = SecureRandom.getInstance(TokenStringService.PRNG_NAME);
        this.messageDigest = MessageDigest.getInstance(TokenStringService.DIGEST_ALGORITHM_NAME);

        // 初始化SecureRandom
        this.secureRandom.nextBytes(new byte[32]);
    }

    public String next() {
        byte[] randomBytes = new byte[TokenStringService.PRNG_PRODUCT_LENGTH];
        this.secureRandom.nextBytes(randomBytes);
        byte[] digestBytes = this.messageDigest.digest(randomBytes);
        return Hex.encodeHexString(digestBytes);
    }
}
