import java.io.*;
import java.util.*;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice:");
        System.out.println("(1) Generate an array of random integers and store it in a file");
        System.out.println("(2) Read an existing file containing a list of integers, sort it, and store the sorted array in another file");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                System.out.println("Enter the length of the array:");
                int length = scanner.nextInt();
                scanner.nextLine();
                
                int[] randomArray = createRandomArray(length);
                writeArrayToFile(randomArray, "random_array.txt");
                System.out.println("Random array created and stored in random_array.txt");
                break;
            case 2:
                System.out.println("Enter the filename containing the unsorted array:");
                String unsortedFileName = scanner.nextLine();
                int[] unsortedArray = readFileToArray(unsortedFileName);
                
                bubbleSort(unsortedArray);
                
                System.out.println("Enter the filename to store the sorted array:");
                String sortedFileName = scanner.nextLine();
                writeArrayToFile(unsortedArray, sortedFileName);
                System.out.println("Sorted array stored in " + sortedFileName);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        
        scanner.close();
    }

    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(101);
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (int num : array) {
                writer.println(num);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static int[] readFileToArray(String filename) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}