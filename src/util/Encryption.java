package util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
import po.User;

@Component
public class Encryption {

    private SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "MD5";

    private int iterationTimes = 5;

    public SecureRandomNumberGenerator getRandomNumberGenerator() {
        return randomNumberGenerator;
    }

    public void setRandomNumberGenerator(SecureRandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public int getIterationTimes() {
        return iterationTimes;
    }

    public void setIterationTimes(int iterationTimes) {
        this.iterationTimes = iterationTimes;
    }

    public void encryptPassword(User user){
        if (user.getPassword() != null){
            user.setSalt(randomNumberGenerator.nextBytes().toHex());
            String password = new SimpleHash(algorithmName, user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()), iterationTimes).toHex();
            user.setPassword(password);
        }
    }
}
