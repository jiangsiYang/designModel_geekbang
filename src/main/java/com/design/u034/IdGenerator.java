package com.design.u034;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * 整个 ID 由三部分组成。第一部分是本机名的最后一个字段。第二部分是当前时间戳，精确到毫秒。第三部分是 8 位的随机字符串，包含大小写字母和数字。尽管这样生成的 ID 并不是绝对唯一的，有重复的可能，但事实上重复的概率非常低。
 *
 * 例如：
 *
 103-1577456311467-3nR3Do45
 103-1577456311468-0wnuV5yw
 103-1577456311468-sdrnkFxN
 103-1577456311468-8lwk0BP0

 这段代码只有短短不到 40 行，里面却有很多值得优化的地方。你可以先思考一下，在纸上试着罗列一下这段代码存在的问题，然后再对比来看我下面的讲解。

 1、整段代码还可以抽取最小函数出来，比如hostName的生成、随机8位字符串的生成。
 2、一些魔数可以用常量替换，比如几位随机字符串。
 3、整个代码可测试性不好，比如测试hostName、随机数生成，这点可以跟第一点一起解决。
 4、没有注释，可阅读性很差，比如随机8位字符串生成的代码为何要这样写。

 */

public class IdGenerator {
    private static final Logger logger = LoggerFactory.getLogger(IdGenerator.class);

    public static String generate() {
        String id = "";
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            String[] tokens = hostName.split("\\.");
            if (tokens.length > 0) {
                hostName = tokens[tokens.length - 1];
            }
            char[] randomChars = new char[8];
            int count = 0;
            Random random = new Random();
            while (count < 8) {
                int randomAscii = random.nextInt(122);
                if (randomAscii >= 48 && randomAscii <= 57) {
                    randomChars[count] = (char)('0' + (randomAscii - 48));
                    count++;
                } else if (randomAscii >= 65 && randomAscii <= 90) {
                    randomChars[count] = (char)('A' + (randomAscii - 65));
                    count++;
                } else if (randomAscii >= 97 && randomAscii <= 122) {
                    randomChars[count] = (char)('a' + (randomAscii - 97));
                    count++;
                }
            }
            id = String.format("%s-%d-%s", hostName,
                    System.currentTimeMillis(), new String(randomChars));
        } catch (UnknownHostException e) {
            logger.warn("Failed to get the host name.", e);
        }

        return id;
    }
}
