package median;

import java.util.Arrays;
import java.util.List;
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
            return deserialize(Arrays.stream(split)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList()),
                    Integer.MIN_VALUE, Integer.MAX_VALUE,
                    0, split.length - 1);
        }

        private TreeNode deserialize(List<Integer> data,
                                     int lo, int hi, int start, int end) {
            if (end < start) {
                return null;
            }
            int rootVal = data.get(start);
            TreeNode root = new TreeNode(rootVal);
            int i = start + 1;
            while (i <= end && data.get(i) < rootVal) {
                i++;
            }
            root.left = deserialize(data, lo, rootVal, start + 1, i - 1);
            root.right = deserialize(data, rootVal, hi, i, end);
            return root;
        }
    }
}
