package median;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Sol449 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder().append(root.val);
            if (root.left != null) {
                sb.append(',').append(serialize(root.left));
            }
            if (root.right != null) {
                sb.append(',').append(serialize(root.right));
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null) {
                return null;
            }
            String[] split = data.split(",");
            return deserialize(new LinkedList<>(
                            Arrays.stream(split).map(Integer::parseInt)
                                    .collect(Collectors.toList())),
                    Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        // O(n)
        private TreeNode deserialize(LinkedList<Integer> data, int lo, int hi) {
            if (data.isEmpty()) return null;
            int rootVal = data.get(0);
            if (rootVal > hi || rootVal < lo) return null;
            data.removeFirst();
            TreeNode root = new TreeNode(rootVal);
            root.left = deserialize(data, lo, rootVal);
            root.right = deserialize(data, rootVal, hi);
            return root;
        }
    }
}
