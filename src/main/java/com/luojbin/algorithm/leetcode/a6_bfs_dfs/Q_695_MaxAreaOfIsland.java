package com.luojbin.algorithm.leetcode.a6_bfs_dfs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Q_695_MaxAreaOfIsland {

    @Test
    public void testBfs() {
        // 构造参数与结果数组, 第一个参数是执行结果, 后面的是具体参数
        int[][][] params = new int[][][]{
                {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}},
                {{0, 0, 0, 0, 0, 0, 0, 0}},
        };
        // 循环测试
        for (int[][] p : params) {
            System.out.println("当前参数:" + Arrays.toString(p));
            // 调用待测方法
            System.out.println(maxAreaOfIsland(p));
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int[] area = {0};
                    countAreaByDfs(grid, i, j, m, n, area);
                    if (area[0] > maxArea) {
                        maxArea = area[0];
                    }
                }
            }
        }
        return maxArea;
    }

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    private void countAreaByDfs(
            int[][] grid,
            int i, int j, int m, int n,
            int[] area) {
        area[0]++;
        grid[i][j] = 0;
        for(int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && x < m && y >=0 && y < n && grid[x][y] == 1) {
                countAreaByDfs(grid, x, y, m, n, area);
            }
        }
    }
}
