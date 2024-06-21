
#include <array>
#include <vector>
#include <memory>
#include <string>
#include <string_view>
using namespace std;

struct TrieNode {
    int frequency;
    unordered_map<int, shared_ptr<TrieNode>> branches;
};

class Trie {

private:
    static const int ALPHABET_SIZE = 26;
    shared_ptr<TrieNode> root = make_shared<TrieNode>();

    int getHashForPrefixSuffixPair(char prefix, char suffix) const {
        return (prefix - 'a') * ALPHABET_SIZE + (suffix - 'a');
    }

public:
    long long countPrefixSuffixPairs = 0;

    void addWord(string_view word) {
        shared_ptr<TrieNode> current = root;
        size_t prefix = 0;
        size_t suffix = word.length() - 1;

        while (prefix < word.length() && suffix != variant_npos) {
            int hashPrefixSuffix = getHashForPrefixSuffixPair(word[prefix], word[suffix]);
            if (!current->branches.contains(hashPrefixSuffix)) {
                current->branches[hashPrefixSuffix] = make_shared<TrieNode>();
            }
            current = current->branches[hashPrefixSuffix];
            countPrefixSuffixPairs += current->frequency;
            ++prefix;
            --suffix;
        }
        ++current->frequency;
    }
};

class Solution {

public:
    long long countPrefixSuffixPairs(const vector<string>& words) const {
        auto trie = make_unique<Trie>();
        for (const auto& word : words) {
            trie->addWord(word);
        }
        return trie->countPrefixSuffixPairs;
    }
};
