import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {
    @Test
    //@DisplayName("LinkedListDeque has no fields besides nodes and primitives")
    void noNonTrivialFields() {
//        Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
//        List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
//                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
//                .toList();
//
//        assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
    }

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
//        Deque<String> lld1 = new LinkedListDeque<>();
//
//        lld1.addFirst("back"); // after this call we expect: ["back"]
//        assertThat(lld1.toList()).containsExactly("back").inOrder();
//
//        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
//        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();
//
//        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
//        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
//
//         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
//            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
//            but not ["front", "middle", "back"].
//          */
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
//        Deque<String> lld1 = new LinkedListDeque<>();
//
//        lld1.addLast("front"); // after this call we expect: ["front"]
//        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
//        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
//        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
//        Deque<Integer> lld1 = new LinkedListDeque<>();
//
//         /* I've decided to add in comments the state after each call for the convenience of the
//            person reading this test. Some programmers might consider this excessively verbose. */
//        lld1.addLast(0);   // [0]
//        lld1.addLast(1);   // [0, 1]
//        lld1.addFirst(-1); // [-1, 0, 1]
//        lld1.addLast(2);   // [-1, 0, 1, 2]
//        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
//
//        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    // Below, you'll write your own tests for LinkedListDeque.
    @Test
    public void isEmptyEmptyCaseTest() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        assertThat(ad1.isEmpty()).isTrue();
    }

    @Test
    public void isEmptyNonEmptyCaseTest() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addLast(0);
        ad1.addLast(1);
        assertThat(ad1.isEmpty()).isFalse();
    }

    @Test
    public void sizeZeroEmptyCaseTest() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        assertThat(ad1.size()).isEqualTo(0);
    }

    @Test
    public void correctSizeNonzeroTest() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        assertThat(ad1.size()).isEqualTo(3);
    }

    @Test
    public void isEmptyAndSizeEmptyCaseTest() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        assertThat(ad1.isEmpty()).isTrue();
        assertThat(ad1.size()).isEqualTo(0);
    }

    @Test
    public void isEmptyAndSizeNonEmptyCaseTest() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        assertThat(ad1.isEmpty()).isFalse();
        assertThat(ad1.size()).isNotEqualTo(0);
        assertThat(ad1.size()).isEqualTo(3);
    }

    @Test
    public void getOutOfBoundsTest() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        assertThat(ad1.get(10)).isNull();
        assertThat(ad1.get(-5)).isNull();
    }

    @Test
    public void getCorrectTest() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        assertThat(ad1.toList()).containsExactly(3, 2, 1).inOrder();
    }

    @Test
    public void getEmptyListTest() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        assertThat(ad1.get(10)).isNull();
        assertThat(ad1.get(-5)).isNull();
        assertThat(ad1.get(0)).isNull();
    }

    @Test
    public void removeFirstEmptyListTest() {
//        Deque<Integer> lld1 = new LinkedListDeque<>();
//        assertThat(lld1.removeFirst()).isNull();
    }

    @Test
    public void removeFirstSizeOneTest() {
//        Deque<Integer> lld1 = new LinkedListDeque<>();
//        lld1.addFirst(1);
//        assertThat(lld1.removeFirst()).isEqualTo(1);
//        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    public void removeFirstSizeOverOneTest() {
//        Deque<Integer> lld1 = new LinkedListDeque<>();
//        lld1.addFirst(1);
//        lld1.addFirst(2);
//        assertThat(lld1.removeFirst()).isEqualTo(2);
//        assertThat(lld1.size()).isEqualTo(1);
    }

    @Test
    public void removeLastEmptyListTest() {
//        Deque<Integer> lld1 = new LinkedListDeque<>();
//        assertThat(lld1.removeLast()).isNull();
    }

    @Test
    public void removeLastSizeOneTest() {
//        Deque<Integer> lld1 = new LinkedListDeque<>();
//        lld1.addFirst(1);
//        assertThat(lld1.removeLast()).isEqualTo(1);
//        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    public void removeLastSizeOverOneTest() {
//        Deque<Integer> lld1 = new LinkedListDeque<>();
//        lld1.addFirst(1);
//        lld1.addFirst(2);
//        assertThat(lld1.removeLast()).isEqualTo(1);
//        assertThat(lld1.size()).isEqualTo(1);
    }

    @Test
    public void removeLastSizeFixedTimeTest() {
//        Deque<Integer> lld1 = new LinkedListDeque<>();
//        Deque<Integer> lld2 = new LinkedListDeque<>();
//        for (int i = 0; i < 1000000; i++) {
//            lld1.addFirst(i);
//        }
//        for(int i = 0; i < 100; i++) {
//            lld2.addFirst(i);
//        }
//        Stopwatch sw = new Stopwatch();
//        assertThat(lld1.removeLast()).isEqualTo(0);
//        double largeListRemoveTime = sw.elapsedTime();
//        Stopwatch sw2 = new Stopwatch();
//        assertThat(lld2.removeLast()).isEqualTo(0);
//        double smallListRemoveTime = sw2.elapsedTime();
//        assertThat(largeListRemoveTime - smallListRemoveTime).isAtMost(1);
    }

    @Test
    public void add_first_after_remove_to_empty() {
//        Deque<Integer> lld1 = new LinkedListDeque<>();
//        lld1.addFirst(1);
//        lld1.addFirst(2);
//        lld1.removeFirst();
//        lld1.removeLast();
//        assertThat(lld1.isEmpty()).isTrue();
//        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    public void add_first_after_remove_to_empty2() {
//        Deque<Integer> lld1 = new LinkedListDeque<>();
//        lld1.addFirst(1);
//        lld1.addFirst(2);
//        lld1.removeFirst();
//        lld1.removeLast();
//        lld1.addFirst(3);
//        assertThat(lld1.isEmpty()).isFalse();
//        assertThat(lld1.size()).isEqualTo(1);
    }
    @Test
    public void removeFirst() {
//        Deque<Integer> lld1 = new LinkedListDeque<>();
//        lld1.addFirst(1);
//        lld1.addFirst(2);
//        lld1.addFirst(3);
//        assertThat(lld1.removeFirst()).isEqualTo(3);
//        assertThat(lld1.size()).isEqualTo(2);
    }

    @Test
    public void toListEmptyTest() {
//        Deque<Integer> lld1 = new LinkedListDeque<>();
//        assertThat(lld1.toList()).isEmpty();
    }

}
