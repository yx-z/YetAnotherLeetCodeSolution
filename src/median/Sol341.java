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
        private Stack<NestedInteger> src = new Stack<>();

        // we shall not copy data in Iterator Pattern
        public NestedIterator(List<NestedInteger> nestedList) {
            pushList(nestedList);
        }

        // instead, we shall do work while actually getting data
        @Override
        public Integer next() {
            return src.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            // make sure that the top element is an int so that
            // we can get it directly in `next()`
            while (!src.isEmpty()) {
                NestedInteger peek = src.peek();
                if (peek.isInteger()) {
                    return true;
                } else {
                    pushList(src.pop().getList());
                }
            }
            return false;
        }

        private void pushList(List<NestedInteger> curList) {
            for (int i = curList.size() - 1; i >= 0; i--)
                src.push(curList.get(i));
        }
    }
}
