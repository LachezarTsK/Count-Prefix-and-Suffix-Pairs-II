
import java.util.HashMap

class Solution {
    fun countPrefixSuffixPairs(words: Array<String>): Long {
        val trie = Trie()
        for (word in words) {
            trie.addWord(word)
        }
        return trie.countPrefixSuffixPairs
    }
}

class TrieNode {

    var frequency = 0
    val branches: HashMap<Int, TrieNode> = HashMap<Int, TrieNode>()
}

class Trie {

    private companion object {
        const val ALPHABET_SIZE = 26
    }

    private val root: TrieNode = TrieNode()
    var countPrefixSuffixPairs: Long = 0

    fun addWord(word: String) {
        var current: TrieNode = root
        var prefix = 0
        var suffix = word.length - 1

        while (prefix < word.length && suffix >= 0) {
            val hashPrefixSuffix = getHashForPrefixSuffixPair(word[prefix], word[suffix])
            if (!current.branches.containsKey(hashPrefixSuffix)) {
                current.branches[hashPrefixSuffix] = TrieNode()
            }
            current = current.branches[hashPrefixSuffix]!!
            countPrefixSuffixPairs += current.frequency
            ++prefix
            --suffix
        }
        ++current.frequency
    }

    private fun getHashForPrefixSuffixPair(prefix: Char, suffix: Char): Int {
        return (prefix - 'a') * ALPHABET_SIZE + (suffix - 'a')
    }
}
