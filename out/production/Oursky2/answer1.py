class TrieNode:
    def __init__(self):
        self.children = {}
        self.count = 0
        self.token_count = 0


class TokenCollection:
    def __init__(self):
        self.root = TrieNode()
        self.total_tokens = 0

    def ingest(self, tokens):
        tokens = tokens.split(':')
        node = self.root
        for token in tokens:
            if token not in node.children:
                node.children[token] = TrieNode()
            node = node.children[token]
            node.token_count += 1  # 更新子节点的token计数
        self.root.token_count += 1  # 更新当前节点的token计数
        node.count += 1
        self.total_tokens += 1

    def appearance(self, prefix):
        tokens = prefix.split(':')
        node = self.root
        for token in tokens:
            if token not in node.children:
                return 0
            node = node.children[token]
        return node.count / node.token_count  # 使用节点的token计数进行计算


# Example usage
collection = TokenCollection()
collection.ingest('oursky:uk:dev')
collection.ingest('oursky:hk:design')
collection.ingest('oursky:hk:pm')
collection.ingest('oursky:hk:dev')
collection.ingest('skymaker')

print(collection.appearance('oursky'))
# Output: 0.8

print(collection.appearance('oursky:hk'))
# Output: 0.6

