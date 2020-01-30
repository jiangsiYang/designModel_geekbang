package com.design.u035;

import com.design.u034.IdGenerator;
import com.google.common.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class RandomIdGenerator implements LogTraceIdGenerator {
    private static final Logger logger = LoggerFactory.getLogger(IdGenerator.class);

    public String generator() {
        String id = "";
        String substrOfHostName = getLastfieldOfHostName();
        String randomString = generateRandomAlphameric(8);
        id = String.format("%s-%d-%s", substrOfHostName,
                System.currentTimeMillis(), randomString);
        return id;
    }

    /**
     * 生成合适的随机字符串
     *
     * @param length
     * @return
     */
    private String generateRandomAlphameric(int length) {
        char[] randomChars = new char[length];
        int count = 0;
        Random random = new Random();
        while (count < length) {
            int maxAscii = 'z';
            int randomAscii = random.nextInt(maxAscii);
            //换种写法去掉多余的if
            boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
            boolean isUppercase = randomAscii >= 'A' && randomAscii <= 'Z';
            boolean isLowercase = randomAscii >= 'a' && randomAscii <= 'z';
            if (isDigit || isUppercase || isLowercase) {
                randomChars[count] = (char) (randomAscii);
                ++count;
            }
        }
        return new String(randomChars);
    }

    /**
     * 从 getLastfieldOfHostName() 函数中，将逻辑比较复杂的那部分代码剥离出来，定义为 getLastSubstrSplittedByDot() 函数。
     * 因为 getLastfieldOfHostName() 函数依赖本地主机名，所以，剥离出主要代码之后这个函数变得非常简单，可以不用测试。
     * 我们重点测试 getLastSubstrSplittedByDot() 函数即可。
     *
     * @return
     * @throws UnknownHostException
     */
    private String getLastfieldOfHostName() {
        String substrOfHostName = null;
        try {
            substrOfHostName = InetAddress.getLocalHost().getHostName();
            substrOfHostName = getLastSubstrSplittedByDot(substrOfHostName);
        } catch (UnknownHostException e) {
            logger.warn("Failed to get the host name.", e);
        }
        return substrOfHostName;
    }


    @VisibleForTesting
    public String getLastSubstrSplittedByDot(String hostName) {
        String[] tokens = hostName.split("\\.");
        //因为substrOfHostName和hostName有不同的语义功能，所以这里不能重用hostName
        String substrOfHostName = hostName;
        if (tokens.length > 0) {
            substrOfHostName = tokens[tokens.length - 1];
        }
        return substrOfHostName;
    }
}
