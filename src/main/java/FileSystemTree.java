import lombok.Builder;

import java.util.List;

public class FileSystemTree {
    @Builder
    public static class Node{
        private List<Node> children;
        private NodeType type;
        private Long size;

        public Node(List<Node> children, NodeType type, Long size) {
            this.children = children;
            this.type = type;
            this.size = size;
        }

        public long calcSize() {
            return calcSize(this);
        }

        public long calcSize(Node node) {
            if(node.type == NodeType.FILE) {
                return node.size;
            }

            int result = 0;

            for (Node child : node.children) {
                result += calcSize(child);
            }
            return result;
        }
    }

    public enum NodeType {
        FILE, FOLDER
    }

}
