package sort;

/**
 * @author CRR
 *
 */
public class Algorithm_Java {
	public static void main(String[] args) {
		int[] elements = new int[] {1, 2, 5, 4, 3, 9, 100, 66, 7};
		quickSort(elements, 0, elements.length - 1);
        for (int element : elements) {
            System.out.print(element + " ");
        }
	}
	
	/**
	 * Insert sort.
	 * O(n^2)
	 * @param elements
	 */
	public static void insertSort(int[] elements) {
		for(int i = 1; i < elements.length; i++) {
			int temp = elements[i];
			int pre  = i - 1;
			for(; pre >= 0; pre--) {
				if (temp < elements[pre]) {
                    elements[pre + 1] = elements[pre];
                } else {
                    break;
                }
			}
			elements[pre + 1] = temp;
		}
	}
	
	
	/**
	 * Hell sort.
	 * O(nlogn)
	 * @param element
	 */
	public static void hellSort(int[] element){
        int d = element.length;
        do {
            d = d / 2;
            for (int i = 0; i < d; i++) {
                for (int j = i + d; j < element.length; j += d) {
                    int tmp = element[j];
                    int k = j - d;
                    for (; k >= 0; k -= d) {
                        if (tmp < element[k]) {
                            element[k + d] = element[k];
                        } else {
                            break;
                        }
                    }
                    element[k + d] = tmp;
                }
            }
        } while (d != 1);
    }
	
	/**
	 * Select sort.
	 * O(n^2)
	 * @param element
	 */
	public static void selectSort(int[] element){
        int minPos;
        int tmp;
        for(int i = 0 ; i < element.length; i++ ){
            minPos = i;
            for(int j = i+1; j < element.length; j++){
                if(element[j] < element[minPos]){
                    minPos = j;
                }
            }
            tmp = element[minPos];
            element[minPos] = element[i];
            element[i] = tmp;
        }
    }
	
	/**
	 * Bubble sort.
	 * O(n^2)
	 * @param element
	 */
	public static void bubbleSort(int[] element){
        int tmp;
        int len = element.length;
        for(int i = 0; i < len; i++ ) {
            for (int j = len -1; j - 1 >= i; j--) {
                if (element[j] < element[j - 1]) {
                    tmp = element[j];
                    element[j] = element[j - 1];
                    element[j - 1] = tmp;
                }
            }
        }
    }
	
	/**
	 * Quick sort.
	 * O(nlogn)
	 * @param elements
	 */
	public static void quickSort(int []elements, int low, int high) {
		if(low < high) {
			int mid = partition(elements, low, high);
			quickSort(elements, low, mid - 1);
			quickSort(elements, mid + 1, high);
		}
	}
	/**
	 * Part the array by base element.
	 * @param elements
	 * @param low
	 * @param high
	 * @return
	 */
	private static int partition(int[] elements, int low, int high) {
	    int base = elements[low];
	    while (low < high) {
	        while (low < high && base < elements[high]) {
                high--;
            }
	        elements[low] = elements[high];
	        while (low < high && base > elements[low]) {
                low++;
            }
	        elements[high] = elements[low];
        }
	    elements[low] = base;
	    return low;
	}
	
	/**
	 * Heap sort.
	 * O(nlogn)
	 * @param element
	 */
	public static void heapSort(int[] element) {
        int length = element.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            adjustHeap(element, i, length - 1);
        }
        int tmp;
        for (int j = length - 1; j >= 0; j--) {
            tmp = element[j];
            element[j] = element[0];
            element[0] = tmp;
            adjustHeap(element, 0, j - 1);
        }
    }

    private static void adjustHeap(int[] element, int start, int end) {
        int tmp = element[start];
        for (int i = 2 * start + 1; i <= end; i = 2 * i + 1) {
            if (i < end && element[i] < element[i + 1]) {
                i++;
            }
            if (tmp > element[i]) {
                break;
            }
            element[start] = element[i];
            start = i;
        }
        element[start] = tmp;
    }
    
    /**
     * Merge sort.
     * O(nlogn)
     * @param element
     * @param left
     * @param right
     */
    public static void mergeSort(int[] element, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(element, left, mid);
            mergeSort(element, mid + 1, right);
            merge(element, left, mid, right);
        }
    }
    
    private static void merge(int[] element, int left, int middle, int right) {
        int[] tmpElement = new int[element.length];
        int index = left;
        int mid = middle + 1;
        int tmpIndex = left;
        while (left <= middle && mid <= right) {
            if (element[left] < element[mid]) {
                tmpElement[index++] = element[left++];
            } else {
                tmpElement[index++] = element[mid++];
            }
        }
        while (left <= middle) {
            tmpElement[index++] = element[left++];
        }
        while (mid <= right) {
            tmpElement[index++] = element[mid++];
        }
        while (tmpIndex <= right){
            element[tmpIndex] = tmpElement[tmpIndex ++];
        }
    }
    
    /**
     * Radix sort.
     * @param number
     * @param d
     */
    public static void radixSort(int[] number, int d) {
        int k = 0;
        int n = 1;
        int m = 1;
        int[][] temp = new int[10][number.length];
        int[] order = new int[10];
        while (m <= d) {
            for (int value : number) {
                int lsd = ((value / n) % 10);
                temp[lsd][order[lsd]] = value;
                order[lsd]++;
            }
            for (int i = 0; i < 10; i++) {
                if (order[i] != 0) {
                    for (int j = 0; j < order[i]; j++) {
                        number[k] = temp[i][j];
                        k++;
                    }
                }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
            m++;
        }
    }
}
