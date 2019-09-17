package hard;

import java.util.*;
import java.util.stream.Collectors;

public class Sol297 {

    // preorder, recursive, dfs
    class Codec {

        public String serialize(TreeNode root) {
            if (root == null) {
                return "#";
            }
            return root.val + "," + serialize(root.left)
                    + "," + serialize(root.right);
        }

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
    }

    // leetcode like, level-order, bfs, iterative
    class Redo {

        public String serialize(TreeNode root) {
            List<String> res = new ArrayList<>();
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                TreeNode cur = q.remove();
                if (cur == null) {
                    res.add("#");
                } else {
                    res.add(String.valueOf(cur.val));
                    q.add(cur.left);
                    q.add(cur.right);
                }
            }
            while (res.size() > 1 && res.get(res.size() - 1).equals("#")) {
                res.remove(res.size() - 1);
            }
            return String.join(",", res);
        }

        public TreeNode deserialize(String data) {
            List<TreeNode> nodes = Arrays.stream(data.split(",")).map(src -> {
                if (src.equals("#")) return null;
                else return new TreeNode(Integer.parseInt(src));
            }).collect(Collectors.toList());
            TreeNode root = nodes.get(0);
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            for (int i = 1; i < nodes.size(); i += 2) {
                TreeNode cur = q.remove();
                cur.left = nodes.get(i);
                if (i + 1 < nodes.size()) cur.right = nodes.get(i + 1);
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }
            return root;
        }
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
