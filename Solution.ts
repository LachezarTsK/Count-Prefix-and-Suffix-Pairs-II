
function countPrefixSuffixPairs(words: string[]): number {
    const trie = new Trie();
    for (let word of words) {
        trie.addWord(word);
    }
    return trie.countPrefixSuffixPairs;
};

class TrieNode {
    frequency = 0;
    branches = new Map<number, TrieNode>();
}

class Trie {

    static ALPHABET_SIZE = 26;
    static ASCII_SMALL_CASE_A = 97;

    root = new TrieNode();
    countPrefixSuffixPairs = 0;

    addWord(word: string): void {
        let current = this.root;
        let prefix = 0;
        let suffix = word.length - 1;

        while (prefix < word.length && suffix >= 0) {
            const hashPrefixSuffix = this.getHashForPrefixSuffixPair(word.codePointAt(prefix), word.codePointAt(suffix));
            if (!current.branches.has(hashPrefixSuffix)) {
                current.branches.set(hashPrefixSuffix, new TrieNode());
            }
            current = current.branches.get(hashPrefixSuffix);
            this.countPrefixSuffixPairs += current.frequency;
            ++prefix;
            --suffix;
        }
        ++current.frequency;
    }

    getHashForPrefixSuffixPair(prefixCodePoint: number, suffixCodePoint: number): number {
        return (prefixCodePoint - Trie.ASCII_SMALL_CASE_A) * Trie.ALPHABET_SIZE + (suffixCodePoint - Trie.ASCII_SMALL_CASE_A);
    }
}
