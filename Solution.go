
package main

import "fmt"

const ALPHABET_SIZE = 26

func countPrefixSuffixPairs(words []string) int64 {
    trie := Trie{createTrieNode(), 0}
    for _, word := range words {
            trie.addWord(&word)
    }
    return trie.countPrefixSuffixPairs
}

type TrieNode struct {
    frequency int
    branches  map[int]*TrieNode
}

type Trie struct {
    root                   *TrieNode
    countPrefixSuffixPairs int64
}

func createTrieNode() *TrieNode {
    trieNode := &TrieNode{
        frequency: 0,
        branches:  map[int]*TrieNode{},
    }
    return trieNode
}

func (trie *Trie) addWord(word *string) {
    current := trie.root
    prefix := 0
    suffix := len(*word) - 1

    for prefix < len(*word) && suffix >= 0 {
        hashPrefixSuffix := getHashForPrefixSuffixPair((*word)[prefix], (*word)[suffix])
        if current.branches[hashPrefixSuffix] == nil {
            current.branches[hashPrefixSuffix] = createTrieNode()
        }
        current = current.branches[hashPrefixSuffix]
        trie.countPrefixSuffixPairs += int64(current.frequency)
        prefix++
        suffix--
    }
    current.frequency++
}

func getHashForPrefixSuffixPair(prefix byte, suffix byte) int {
    return int(prefix-'a')*ALPHABET_SIZE + int(suffix-'a')
}
