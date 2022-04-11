package com.luojbin.algorithm.structures.tree;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Objects;

public class T2_FileSystemTreePrint {

    @Test
    public void test() {
        File home = new File("/Users/luojbin/Downloads");
        listAll(home);
    }

    /**
     * 驱动方法, 隐藏不需要暴露的深度计数器0
     */
    private void listAll(File home) {
        listAll(home, 0);
    }

    /**
     * 实际处理逻辑的递归方法
     */
    private void listAll(File file, int depth){
        printFile(file.getName(), depth);
        if (file.isDirectory()) {
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                listAll(subFile, depth + 1);
            }
        }
    }

    /**
     * 打印方法
     */
    private void printFile(String name, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("\t");
        }
        System.out.println(name);
    }
}
