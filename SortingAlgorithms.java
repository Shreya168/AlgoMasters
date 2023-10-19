package efficiencyanalyzer;

//import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class SortingAlgorithms {
    public static void main(String[] args) {
        String[] algorithms = { "Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort",
                "Heap Sort", "Radix Sort" };
        int[] dataSizes = { 10, 100, 1000, 5000 };

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Compare algorithm performance");
            System.out.println("2. Algorithm complexities");
            System.out.println("3. Custom sort");
            System.out.println("4. Exit");

            int option = readInt("Enter your choice: ");

            switch (option) {
                case 1:
                    compareAlgorithmPerformance(algorithms, dataSizes);
                    break;
                case 2:
                    printAlgorithmComplexities();
                    break;
                case 3:
                    customSort();
                    break;
                case 4:
                    System.out.println("Exiting. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Sorting Algorithms

    // Bubble Sort
    public static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    // Selection Sort
    public static int[] selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
        return arr;
    }

    // Insertion Sort
    public static int[] insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && key < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    // Merge Sort
    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
        return result;
    }

    // Quick Sort
    public static int[] quickSort(int[] arr) {
    	if (arr.length <= 1) {
            return arr;
        }
        
        // Choose a random pivot element
        int pivotIndex = new Random().nextInt(arr.length);
        int pivot = arr[pivotIndex];
        
        List<Integer> less = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            if (value < pivot) {
                less.add(value);
            } else if (value == pivot) {
                equal.add(value);
            } else {
                greater.add(value);
            }
        }
        
        // Convert Lists back to arrays
        int[] lessArr = new int[less.size()];
        int[] equalArr = new int[equal.size()];
        int[] greaterArr = new int[greater.size()];
        
        for (int i = 0; i < less.size(); i++) {
            lessArr[i] = less.get(i);
        }
        
        for (int i = 0; i < equal.size(); i++) {
            equalArr[i] = equal.get(i);
        }
        
        for (int i = 0; i < greater.size(); i++) {
            greaterArr[i] = greater.get(i);
        }
        
        // Recursively sort the "less" and "greater" arrays
        int[] sortedLess = quickSort(lessArr);
        int[] sortedGreater = quickSort(greaterArr);
        
        // Concatenate the sorted arrays with the equal elements to get the final result
        int[] result = new int[sortedLess.length + equalArr.length + sortedGreater.length];
        
        System.arraycopy(sortedLess, 0, result, 0, sortedLess.length);
        System.arraycopy(equalArr, 0, result, sortedLess.length, equalArr.length);
        System.arraycopy(sortedGreater, 0, result, sortedLess.length + equalArr.length, sortedGreater.length);
        
        return result;

    }

    // Heap Sort
    public static int[] heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
        return arr;
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    // Radix Sort
    public static int[] countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < n; i++) {
            int index = arr[i] / exp;
            count[index % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            int index = arr[i] / exp;
            output[count[index % 10] - 1] = arr[i];
            count[index % 10]--;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
        return arr;
    }

    public static int[] radixSort(int[] arr) {
        int maxNum = Arrays.stream(arr).max().getAsInt();
        int exp = 1;
        while (maxNum / exp > 0) {
            countingSort(arr, exp);
            exp *= 10;
        }
        return arr;
    }

    // Algorithm Complexities
    public static void printAlgorithmComplexities() {
        String[] complexities = {
            "Bubble Sort: O(n^2)",
            "Selection Sort: O(n^2)",
            "Insertion Sort: O(n^2)",
            "Merge Sort: O(n log n)",
            "Quick Sort: O(n^2) average, O(n log n) best",
            "Heap Sort: O(n log n)",
            "Radix Sort: O(nk)"
        };
        System.out.println("Algorithm Complexities:");
        for (String complexity : complexities) {
            System.out.println(complexity);
        }
    }

    // Custom Sort
    public static void customSort() {
    	String[] algorithms = { "Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort",
                "Heap Sort", "Radix Sort" };
        int dataSize = readInt("Enter the size of the dataset: ");
        int minValue = readInt("Enter the minimum value for elements: ");
        int maxValue = readInt("Enter the maximum value for elements: ");
        int[] data = generateRandomArray(dataSize, minValue, maxValue);
        System.out.println("Select sorting algorithm:");
        for (int i = 0; i < algorithms.length; i++) {
            System.out.println((i + 1) + ". " + algorithms[i]);
        }
        int choice = readInt("Enter your choice: ");
        if (choice >= 1 && choice <= algorithms.length) {
            int[] sortedData = null;
            long startTime = System.currentTimeMillis();
            switch (choice) {
                case 1:
                    sortedData = bubbleSort(data);
                    break;
                case 2:
                    sortedData = selectionSort(data);
                    break;
                case 3:
                    sortedData = insertionSort(data);
                    break;
                case 4:
                    sortedData = mergeSort(data);
                    break;
                case 5:
                    sortedData = quickSort(data);
                    break;
                case 6:
                    sortedData = heapSort(data);
                    break;
                case 7:
                    sortedData = radixSort(data);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid algorithm.");
            }
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("Sorted data using " + algorithms[choice - 1] + ":");
            System.out.println(Arrays.toString(sortedData));
            System.out.println("Time taken: " + (executionTime / 1000.0) + " seconds");
        } else {
            System.out.println("Invalid choice. Please select a valid algorithm.");
        }
    }

    // Utility Methods
    public static int readInt(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int value = 0;
        boolean valid = false;
        
        while (!valid) {
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        
        return value;
    }

    public static int[] generateRandomArray(int size, int minValue, int maxValue) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(maxValue - minValue + 1) + minValue;
        }
        return arr;
    }

    public static void compareAlgorithmPerformance(String[] algorithms, int[] dataSizes) {
        System.out.println("Comparing Algorithm Performance:");
        for (String algorithm : algorithms) {
            System.out.println("Algorithm: " + algorithm);
            for (int dataSize : dataSizes) {
                int[] data = generateRandomArray(dataSize, 0, dataSize);
                long startTime = System.currentTimeMillis();
                switch (algorithm) {
                    case "Bubble Sort":
                        bubbleSort(data);
                        break;
                    case "Selection Sort":
                        selectionSort(data);
                        break;
                    case "Insertion Sort":
                        insertionSort(data);
                        break;
                    case "Merge Sort":
                        mergeSort(data);
                        break;
                    case "Quick Sort":
                        quickSort(data);
                        break;
                    case "Heap Sort":
                        heapSort(data);
                        break;
                    case "Radix Sort":
                        radixSort(data);
                        break;
                }
                long endTime = System.currentTimeMillis();
                double executionTime = (endTime - startTime) / 1000.0;
                System.out.println("Data Size: " + dataSize + ", Execution Time: " + executionTime + " seconds");
            }
        }
    }
}
