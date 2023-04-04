package me.deltaorion.euler.P18_67;

public class TriangleTree {

    private final Node root;

    public TriangleTree(Triangle triangle) {
        this.root = new Node(triangle.get(0,0));
        Node[] currentRow = new Node[]{this.root};
        Node[] nextRow = new Node[2];
        for(int i=1;i<triangle.getRowCount();i++) {
            Node left = new Node(triangle.get(i,0));
            nextRow[0] = left;
            for(int j=0;j<i;j++) {
                Node right = new Node(triangle.get(i,j+1));
                nextRow[j+1] = right;
                currentRow[j].setLeft(left);
                currentRow[j].setRight(right);
                left = right;
            }
            currentRow = nextRow;
            nextRow = new Node[i+2];
        }
    }

    public int getPathSum() {
        return getPathSum(root);
    }

    private int getPathSum(Node node) {
        if(node==null)
            return 0;

        if(node.getMaximumSum()!=-1)
            return node.getMaximumSum();

        int leftPathSum = getPathSum(node.getLeft());
        int rightPathSum = getPathSum(node.getRight());
        node.setMaximumSum(Math.max(leftPathSum,rightPathSum) + node.value);
        return node.getMaximumSum();
    }

    private static class Node {
        private Node left;
        private Node right;
        private int maximumSum = -1;
        private final int value;

        private Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getMaximumSum() {
            return maximumSum;
        }

        public void setMaximumSum(int maximumSum) {
            this.maximumSum = maximumSum;
        }

        public int getValue() {
            return value;
        }

        public String toString() {
            return "["+ value + " " + getLeft() + " " + getRight() +"]";
        }
    }
}
