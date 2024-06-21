
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public long countPrefixSuffixPairs(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.addWord(word);
        }
        return trie.countPrefixSuffixPairs;
    }
}

class TrieNode {

    int frequency;
    Map<Integer, TrieNode> branches = new HashMap<>();
}

class Trie {

    private static final int ALPHABET_SIZE = 26;
    private final TrieNode root = new TrieNode();
    long countPrefixSuffixPairs;

    void addWord(String word) {
        TrieNode current = root;
        int prefix = 0;
        int suffix = word.length() - 1;

        while (prefix < word.length() && suffix >= 0) {
            int hashPrefixSuffix = getHashForPrefixSuffixPair(word.charAt(prefix), word.charAt(suffix));
            if (!current.branches.containsKey(hashPrefixSuffix)) {
                current.branches.put(hashPrefixSuffix, new TrieNode());
            }
            current = current.branches.get(hashPrefixSuffix);
            countPrefixSuffixPairs += current.frequency;
            ++prefix;
            --suffix;
        }
        ++current.frequency;
    }

    private int getHashForPrefixSuffixPair(char prefix, char suffix) {
        return (prefix - 'a') * ALPHABET_SIZE + (suffix - 'a');
    }
}
