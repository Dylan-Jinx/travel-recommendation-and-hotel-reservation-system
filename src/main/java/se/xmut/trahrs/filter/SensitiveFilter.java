package se.xmut.trahrs.filter;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import se.xmut.trahrs.util.YamlUtil;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SensitiveFilter {
    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    //用来替代敏感词
    private static final String REPLACECHARCTER = "***";
    //前缀树的根节点
    private static final PrefixTree rootNode = new PrefixTree();

    @PostConstruct
    public void init() {
        try (
                InputStream is = this.getClass().getClassLoader().getResourceAsStream(YamlUtil.getStringByYaml("SensitiveWordFilter.keywordFileName"));
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        ) {
            String keyword;
            while ((keyword = reader.readLine()) != null) {
                this.addSensitiveWord(keyword);
            }
        } catch (IOException e) {
            logger.error("获取敏感词文件失败：" + e.getMessage());
        }
    }

    public void addSensitiveWord(String keyword) {
        PrefixTree tempNode = rootNode;
        for (int i = 0; i < keyword.length(); ++i) {
            char key = keyword.charAt(i);
            //找子节点
            PrefixTree subNode = tempNode.getSubNode(key);
            if (subNode == null) {//如果没有这个子节点
                //初始化子节点；
                subNode = new PrefixTree();
                tempNode.addSubNode(key, subNode);
            }
            //指向子节点，进入下一次循环
            tempNode = subNode;
            if (i == keyword.length() - 1) {
                tempNode.setKeywordEnd(true);
            }
        }
    }

    public String filter(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        // 接下来三个指针 一个指向树 另外两个为快慢指针用来检查 快的指针向后检查，不是就归位
        PrefixTree treeNode = rootNode;
        int begin = 0;
        int position = 0;

        StringBuilder result = new StringBuilder();
        while (position < text.length()) {
            char c = text.charAt(position);
            if (checkSign(c)) {
                if (treeNode == rootNode) {
                    result.append(c);
                    begin++;
                }
                position++;
                continue;
            }
            c = Character.toLowerCase(c);
            treeNode = treeNode.getSubNode(c);
            if (treeNode == null) {
                result.append(text.charAt(begin));
                position = ++begin;
                treeNode = rootNode;
            } else if (treeNode.isKeywordEnd()) {
                result.append(REPLACECHARCTER);
                begin = ++position;
                treeNode = rootNode;
            } else {
                position++;
            }
        }
        result.append(text.substring(begin));
        return result.toString();
    }


    private boolean checkSign(Character c) {
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9fff);
    }


}

class PrefixTree {

    private boolean isKeywordEnd = false;

    private Map<Character, PrefixTree> subNode = new ConcurrentHashMap<>();

    public boolean isKeywordEnd() {
        return isKeywordEnd;
    }

    public void setKeywordEnd(boolean keywordEnd) {
        isKeywordEnd = keywordEnd;
    }

    public void addSubNode(Character key, PrefixTree subNode) {
        this.subNode.put(key, subNode);
    }

    public PrefixTree getSubNode(Character key) {
        return subNode.get(key);
    }
}
