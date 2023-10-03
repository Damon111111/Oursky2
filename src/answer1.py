class TrieNode:
    def __init__(self):
        self.children = {}
        self.count = 0

class PrefixTree:
    def __init__(self):
        self.root = TrieNode()
        self.ingest_count = 0

    def ingest(self, input):
        tokens = input.split(":")
        node = self.root
        for token in tokens:
            if token not in node.children:
                node.children[token] = TrieNode()
            node = node.children[token]
            node.count += 1
        self.ingest_count += 1

    def appearance(self, prefix):
        node = self.root
        tokens = prefix.split(":")
        for token in tokens:
            if token in node.children:
                node = node.children[token]
            else:
                return 0.0
        return node.count / self.ingest_count

prefix_tree = PrefixTree()

prefix_tree.ingest("oursky:uk:dev")
prefix_tree.ingest("oursky:hk:design")
prefix_tree.ingest("oursky:hk:pm")
prefix_tree.ingest("oursky:hk:dev")
prefix_tree.ingest("skymaker")

print(prefix_tree.appearance("oursky"))
print(prefix_tree.appearance("oursky:hk"))

prefix_tree.ingest("skymaker:london:ealing:dev")
prefix_tree.ingest("skymaker:london:croydon")
prefix_tree.ingest("skymaker:london:design")
prefix_tree.ingest("skymaker:man:pm")
prefix_tree.ingest("skymaker:man:pm")

print(prefix_tree.appearance("skymaker:man"))
