import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

class PercolationTest {

    @Test
    void test0() {
        PercolationFactory pf = new PercolationFactory();
        int N = 0;
        try {
            Percolation p = pf.make(N);
        } catch (IllegalArgumentException e) {
            assertThat(e.getClass()).isEqualTo(IllegalArgumentException.class);
        }
    }

    @Test
    void test1() {
        PercolationFactory pf = new PercolationFactory();
        int N = 1;
        Percolation p = pf.make(N);
        while (!p.percolates()) {
            int randRow = StdRandom.uniform(N);
            int randCol = StdRandom.uniform(N);
            p.open(randRow, randCol);
        }
        assertThat(p.numberOfOpenSites()).isAtLeast((N * N)/2);
    }

    @Test
    void test2() {
        PercolationFactory pf = new PercolationFactory();
        int N = 2;
        Percolation p = pf.make(N);
        while (!p.percolates()) {
            int randRow = StdRandom.uniform(N);
            int randCol = StdRandom.uniform(N);
            p.open(randRow, randCol);
        }
        assertThat(p.numberOfOpenSites()).isAtLeast((N * N)/2);
    }

    @Test
    void test3() {
        PercolationFactory pf = new PercolationFactory();
        int N = 3;
        Percolation p = pf.make(N);
        while (!p.percolates()) {
            int randRow = StdRandom.uniform(N);
            int randCol = StdRandom.uniform(N);
            p.open(randRow, randCol);
        }
        assertThat(p.numberOfOpenSites()).isAtLeast((N * N)/2);
    }

    @Test
    void test4() {
        PercolationFactory pf = new PercolationFactory();
        int N = 4;
        Percolation p = pf.make(N);
        while (!p.percolates()) {
            int randRow = StdRandom.uniform(N);
            int randCol = StdRandom.uniform(N);
            p.open(randRow, randCol);
        }
        assertThat(p.numberOfOpenSites()).isAtLeast((N * N)/2);
    }

    @Test
    void test5() {
        PercolationFactory pf = new PercolationFactory();
        int N = 5;
        Percolation p = pf.make(N);
        while (!p.percolates()) {
            int randRow = StdRandom.uniform(N);
            int randCol = StdRandom.uniform(N);
            p.open(randRow, randCol);
        }
        assertThat(p.numberOfOpenSites()).isAtLeast((N * N)/2);
    }

    @Test
    void backwashTestNegative() {
        PercolationFactory pf = new PercolationFactory();
        int N = 4;
        Percolation p = pf.make(N);
        p.open(0, 1);
        p.open(1, 1);
        p.open(2, 1);
        p.open(3, 1);
        p.open(2, 3);
        p.open(3, 3);
        p.open(2, 2);

        assertThat(p.isFull(3, 3)).isTrue();
        assertThat(p.isFull(2, 3)).isTrue();
        assertThat(p.percolates()).isTrue();
    }

    @Test
    void backwashTestPositive() {
        PercolationFactory pf = new PercolationFactory();
        int N = 4;
        Percolation p = pf.make(N);
        p.open(0, 1);
        p.open(1, 1);
        p.open(2, 1);
        p.open(3, 1);
        p.open(2, 3);
        p.open(3, 3);

        assertThat(p.isFull(3, 3)).isFalse();
        assertThat(p.isFull(2, 3)).isFalse();
        assertThat(p.percolates()).isTrue();
    }
}