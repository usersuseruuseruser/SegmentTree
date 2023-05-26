package test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        SegmentTree st = new SegmentTree(new int[1]);

        int[] arr = new int[10000];

        BufferedWriter insertWriter = new BufferedWriter(new FileWriter("C:/Eroge/insert.txt"));
        BufferedWriter searchWriter = new BufferedWriter(new FileWriter("C:/Eroge/search.txt"));
        BufferedWriter updateWriter = new BufferedWriter(new FileWriter("C:/Eroge/update.txt"));
        BufferedWriter queryWriter = new BufferedWriter(new FileWriter("C:/Eroge/query.txt"));
        BufferedWriter deleteWriter = new BufferedWriter(new FileWriter("C:/Eroge/delete.txt"));

        long start, end;

        // Insertion(essentially - adding to the end)
        for (int i = 0; i < 10000; i++) {
            arr[i] = rand.nextInt(10000);
            st.operations = 0;

            start = System.nanoTime();
            st.insert(i, arr[i]);
            end = System.nanoTime();

            insertWriter.write((end - start) + " " + st.operations + "\n");
        }
        insertWriter.close();

        // Search
        for (int i = 0; i < 300; i++) {
            int index = rand.nextInt(10000);
            st.operations = 0;

            start = System.nanoTime();
            st.findElem(arr[index]);
            end = System.nanoTime();

            searchWriter.write((end - start) + " " + st.operations + "\n");
        }
        searchWriter.close();

        // Update
        for (int i = 0; i < 300; i++) {
            int index = rand.nextInt(10000);
            int value = rand.nextInt(10000);
            st.operations = 0;

            start = System.nanoTime();
            st.update(index, value);
            end = System.nanoTime();

            updateWriter.write((end - start) + " " + st.operations + "\n");
        }
        updateWriter.close();

        // Query
        for (int i = 0; i < 300; i++) {
            int left = rand.nextInt(8000);
            int right = left + 2000;
            st.operations = 0;

            start = System.nanoTime();
            st.query(left, right);
            end = System.nanoTime();

            queryWriter.write((end - start) + " " + st.operations + "\n");
        }
        queryWriter.close();

        // Delete
        for (int i = 0; i < 1000; i++) {
            int index = rand.nextInt(st.getArr().length);
            st.operations = 0;

            start = System.nanoTime();
            st.delete(index);
            end = System.nanoTime();

            deleteWriter.write((end - start) + " " + st.operations + "\n");
        }
        deleteWriter.close();
    }
}