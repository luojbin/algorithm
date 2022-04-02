package com.luojbin.algorithm.leetcode.a6_bfs_dfs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Q_733_FloodFill {

    @Test
    public void test() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        Object[][] params = new Object[][]{
                // sr, sc, newColor, image
                {1, 1, 2, new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}},
                {0, 0, 2, new int[][]{{0, 0, 0}, {0, 0, 0}}},
                {1, 1, 1, new int[][]{{0, 0, 0}, {0, 1, 1}}},

        };
        // 循环测试
        for (Object[] p : params) {
            System.out.println("当前参数:" + Arrays.toString(p));
            // 调用待测方法
            int[][] result = floodFill((int[][]) p[3], (int) p[0], (int) p[1], (int) p[2]);
            for (int[] row : result) {
                System.out.println(Arrays.toString(row));
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // 递归
        int oldColor = image[sr][sc];
        int row = image.length;
        int col = image[0].length;
        fillAround(image, sr, sc, row, col, oldColor, newColor);
        return image;
    }

    /**
     * 其实就是深度优先算法, 类似栈的操作, 处理到每个格子时, 都检查他四周的格子是否已染色
     *
     * @param image
     * @param sr
     * @param sc
     * @param row
     * @param col
     * @param oldColor
     * @param newColor
     */
    public void fillAround(int[][] image,
                           int sr, int sc,
                           int row, int col,
                           int oldColor, int newColor) {
        image[sr][sc] = newColor;
        // 有上方格子, 且同色
        if (sr - 1 >= 0 && image[sr - 1][sc] == oldColor) {
            fillAround(image, sr - 1, sc, row, col, oldColor, newColor);
        }
        // 有右方格子, 且同色
        if (sc + 1 < col && image[sr][sc + 1] == oldColor) {
            fillAround(image, sr, sc + 1, row, col, oldColor, newColor);
        }
        // 有下方格子, 且同色
        if (sr + 1 < row && image[sr + 1][sc] == oldColor) {
            fillAround(image, sr + 1, sc, row, col, oldColor, newColor);
        }
        // 有左方格子, 且同色
        if (sc - 1 >= 0 && image[sr][sc - 1] == oldColor) {
            fillAround(image, sr, sc - 1, row, col, oldColor, newColor);
        }
    }

    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};

    @Test
    public void testDfs() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        Object[][] params = new Object[][]{
                // sr, sc, newColor, image
                {1, 1, 2, new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}},
                {0, 0, 2, new int[][]{{0, 0, 0}, {0, 0, 0}}},
                {1, 1, 1, new int[][]{{0, 0, 0}, {0, 1, 1}}},
        };
        // 循环测试
        for (Object[] p : params) {
            System.out.println("当前参数:" + Arrays.toString(p));
            // 调用待测方法
            int[][] result = floodFillByDfs((int[][]) p[3], (int) p[0], (int) p[1], (int) p[2]);
            for (int[] row : result) {
                System.out.println(Arrays.toString(row));
            }
        }
    }

    public int[][] floodFillByDfs(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        // 如果一开始就是同色, 直接返回, 否则 dfs 会栈溢出
        if (oldColor == newColor) {
            return image;
        }
        int row = image.length;
        int col = image[0].length;
        dfs(image, sr, sc, row, col, oldColor, newColor);
        return image;
    }

    /**
     * 其实就是深度优先算法, 类似栈的操作, 处理到每个格子时, 都检查他四周的格子是否已染色
     * @param image
     * @param sr
     * @param sc
     * @param row
     * @param col
     * @param oldColor
     * @param newColor
     */
    public void dfs(int[][] image,
                    int sr, int sc,
                    int row, int col,
                    int oldColor, int newColor) {
        // 如果当前格子与原格子不同色, 直接返回
        if (image[sr][sc] != oldColor) {
            return;
        }
        // 如果当前格子与原格子同色, 染色, 然后处理四周的
        image[sr][sc] = newColor;
        // 处理四周的格子
        for (int i = 0; i < 4; i++) {
            // 计算周围格子的坐标
            int r = sr + dr[i];
            int c = sc + dc[i];
            // 如果新坐标有效, 处理该格子
            if (r >= 0 && r < row && c >= 0 && c < col) {
                dfs(image, r, c, row, col, oldColor, newColor);
            }
        }
    }


    @Test
    public void testBfs() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        Object[][] params = new Object[][]{
                // sr, sc, newColor, image
                {1, 1, 2, new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}},
                {0, 0, 2, new int[][]{{0, 0, 0}, {0, 0, 0}}},
                {1, 1, 1, new int[][]{{0, 0, 0}, {0, 1, 1}}},
        };
        // 循环测试
        for (Object[] p : params) {
            System.out.println("当前参数:" + Arrays.toString(p));
            // 调用待测方法
            int[][] result = floodFillByBfs((int[][]) p[3], (int) p[0], (int) p[1], (int) p[2]);
            for (int[] row : result) {
                System.out.println(Arrays.toString(row));
            }
        }
    }

    /**
     * 处理每个格子时, 将四周的格子添加到队列, 然后变色, 再从队列中取出元素, 处理该格子四周
     */
    public int[][] floodFillByBfs(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        // 如果一开始就是同色, 直接返回, 否则 dfs 会栈溢出
        if (oldColor == newColor) {
            return image;
        }
        int row = image.length;
        int col = image[0].length;
        // 创建一个队列, 记录四周未处理的格子
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for(int i = 0; i < 4; i++){
                int r = x + dr[i];
                int c = y + dc[i];
                if (r >= 0 && r < row && c >= 0 && c < col && image[r][c] == oldColor) {
                    // 添加到队列
                    queue.offer(new int[]{r, c});
                    image[r][c] = newColor;
                }
            }
        }
        return image;
    }
}
