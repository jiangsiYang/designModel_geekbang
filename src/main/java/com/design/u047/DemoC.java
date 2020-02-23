package com.design.u047;

import java.util.HashMap;
import java.util.List;

/**
 * 浅拷贝
 * 使用浅拷贝来代替重新从数据库中查询数据出来，这样过程就不会太过耗时。
 * 缺点：拷贝出来的HashMap的value和原HashMap的value指向都是同一个对象，这样一个value做出更改，另一个也会跟着变动，所以我们需要深拷贝来拷贝完全独立的HashMap。
 */
public class DemoC {
    private HashMap<String, SearchWord> currentKeywords = new HashMap<>();
    private long lastUpdateTime = -1;

    public void refresh() {
        // 原型模式就这么简单，拷贝已有对象的数据，更新少量差值
        HashMap<String, SearchWord> newKeywords = (HashMap<String, SearchWord>) currentKeywords.clone();

        // 从数据库中取出更新时间>lastUpdateTime的数据，放入到newKeywords中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords(lastUpdateTime);
        long maxNewUpdatedTime = lastUpdateTime;
        for (SearchWord searchWord : toBeUpdatedSearchWords) {
            if (searchWord.getLastUpdateTime() > maxNewUpdatedTime) {
                maxNewUpdatedTime = searchWord.getLastUpdateTime();
            }
            if (newKeywords.containsKey(searchWord.getKeyword())) {
                SearchWord oldSearchWord = newKeywords.get(searchWord.getKeyword());
                oldSearchWord.setCount(searchWord.getCount());
                oldSearchWord.setLastUpdateTime(searchWord.getLastUpdateTime());
            } else {
                newKeywords.put(searchWord.getKeyword(), searchWord);
            }
        }

        lastUpdateTime = maxNewUpdatedTime;
        currentKeywords = newKeywords;
    }

    private List<SearchWord> getSearchWords(long lastUpdateTime) {
        // TODO: 从数据库中取出更新时间>lastUpdateTime的数据
        return null;
    }
}
