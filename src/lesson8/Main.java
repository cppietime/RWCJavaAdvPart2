package lesson8;

import lesson7.TreeTable;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Main {

    public static <T extends TreeTable<Integer, Integer, ?>> void testTable(Class<T> clazz) {
        try {
            T table = clazz.getConstructor().newInstance();
            Random rand = new Random();
            for(int i = 0; i < 25; i++) {
                table.put(rand.nextInt(200), i);
//                System.out.println("Inserted number " + i + ": " + table.tree);
            }
            System.out.println("Tree valid? " + table.tree.validate());
            System.out.println(table.tree);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("==============\nTreap");
        testTable(TreeTable.TreapTable.class);
        System.out.println("==============\nAVL");
        testTable(TreeTable.AVLTreeTable.class);
        System.out.println("==============\nSplay");
        testTable(TreeTable.SplayTreeTable.class);
        System.out.println("==============\nRedBlack");
        testTable(TreeTable.RedBlackTreeTable.class);
    }

}
