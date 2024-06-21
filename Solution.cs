
using System;
using System.Collections.Generic;

public class Solution
{
    public long CountPrefixSuffixPairs(string[] words)
    {
        Trie trie = new Trie();
        foreach (var word in words)
        {
            trie.AddWord(word);
        }
        return trie.countPrefixSuffixPairs;
    }
}

class TrieNode
{
    public int frequency;
    public Dictionary<int, TrieNode> branches = new Dictionary<int, TrieNode>();
}

class Trie
{
    private static readonly int ALPHABET_SIZE = 26;
    private readonly TrieNode root = new TrieNode();
    public long countPrefixSuffixPairs;

    public void AddWord(String word)
    {
        TrieNode current = root;
        int prefix = 0;
        int suffix = word.Length - 1;

        while (prefix < word.Length && suffix >= 0)
        {
            int hashPrefixSuffix = GetHashForPrefixSuffixPair(word[prefix], word[suffix]);
            current.branches.TryAdd(hashPrefixSuffix, new TrieNode());
            current = current.branches[hashPrefixSuffix];
            countPrefixSuffixPairs += current.frequency;
            ++prefix;
            --suffix;
        }
        ++current.frequency;
    }

    private int GetHashForPrefixSuffixPair(char prefix, char suffix)
    {
        return (prefix - 'a') * ALPHABET_SIZE + (suffix - 'a');
    }
}
