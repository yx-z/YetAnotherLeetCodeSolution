package hard;

import java.util.Arrays;
import java.util.LinkedList;

public class Sol297 {

    class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "#";
            }
            return root.val + "," + serialize(root.left)
                    + "," + serialize(root.right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return des(new LinkedList<>(Arrays.asList(data.split(","))));
        }

        public TreeNode des(LinkedList<String> data) {
            String first = data.remove();
            if (first.equals("#")) return null;
            TreeNode root = new TreeNode(Integer.parseInt(first));
            root.left = des(data);
            root.right = des(data);
            return root;
        }

        class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }
    }
}
