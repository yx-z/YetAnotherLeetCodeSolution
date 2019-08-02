package median;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Sol341 {
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     * <p>
     * // @return true if this NestedInteger holds a single integer, rather than a nested list.
     * public boolean isInteger();
     * <p>
     * // @return the single integer that this NestedInteger holds, if it holds a single integer
     * // Return null if this NestedInteger holds a nested list
     * public Integer getInteger();
     * <p>
     * // @return the nested list that this NestedInteger holds, if it holds a nested list
     * // Return null if this NestedInteger holds a single integer
     * public List<NestedInteger> getList();
     * }
     */
    public interface NestedInteger {
        boolean isInteger();

        Integer getInteger();

        List<NestedInteger> getList();
    }

    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */
    public class NestedIterator implements Iterator<Integer> {
        Stack<NestedInteger> s = new Stack<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            build(nestedList);
        }

        private void build(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; i--)
                if (nestedList.get(i).isInteger()) s.add(nestedList.get(i));
                else build(nestedList.get(i).getList());
        }

        @Override
        public Integer next() {
            return s.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            return !s.isEmpty();
        }
    }
}
