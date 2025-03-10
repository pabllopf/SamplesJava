import java.util.*;

public class Main {

    // Ex 1 : Array of X numbers : find the two biggest Array of X numbers,
    // find the two smaller by removing the negative numbers Solve a Merge sort
    public static int[] findTwoBiggest(int[] arr) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }
        return new int[]{max1, max2};
    }

    // Ex 2 :
    // Consider 2 sorted arrays. Write an algorithm that will merge those 2 arrays into 1 single sorted array.
    // Example : [1, 5, 12, 18] and [2, 4, 15] -> [1, 2, 4, 5, 12, 15, 18]
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] mergedArray = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                mergedArray[k++] = arr1[i++];
            } else {
                mergedArray[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            mergedArray[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            mergedArray[k++] = arr2[j++];
        }

        return mergedArray;
    }

    // Ex 3 :
    //In the old database there is a column that stores a float value in the format of a string. The remaining places are filled with 0s (zero/s)  or .0s (dot zero/s) so that the string has always the same length.
    //
    //12.0000000000
    //132.345000000
    //12354.3050000
    //
    //Write a code to clean the artificially added 0s or dots  and 0s by using the loops and basic string or array operations to get as a result:
    //12
    //132.345
    //12354.305
    public static String cleanFloatString(String floatStr) {
        // Remove trailing zeros and dots
        int end = floatStr.length();
        while (end > 0 && (floatStr.charAt(end - 1) == '0' || floatStr.charAt(end - 1) == '.')) {
            end--;
        }
        return floatStr.substring(0, end);
    }

    //Ex 4 :
    //Consider a traveler going by plane to a given city and coming back, with additional stops along the way. The full ordered list of cities of the travel is called a route.
    //For each question, given the origin, the destination and a route, you will need to write an algorith that will check whether the partial route from origin to destination is the exact opposite of the partial route from destination to origin.
    //Question 1 : consider that there is exactly the same number of stops the the way upward, betweek origin and destination, as the number of stops on the way back, between destination and origin.
    //Ex : Fligh from NCE to TYO, route : [NCE,PAR,DXB,TYO,DXB,PAR,NCE] -> OK
    //Flight from NCE to LAX, route : [NCE,LON,LAX,NYC,NCE] -> KO
    public static boolean isRouteSymmetric(String origin, String destination, List<String> route) {
        // Find the indices of the origin and destination cities in the route list
        int originIndex = route.indexOf(origin);
        int destinationIndex = route.indexOf(destination);

        // If either of them is not found, the route is invalid
        if (originIndex == -1 || destinationIndex == -1) {
            return false;
        }

        // Check that the number of stops between origin and destination is the same as the number of stops on the return trip
        int upwardStops = destinationIndex - originIndex - 1;
        int downwardStops = route.size() - 1 - route.lastIndexOf(destination) - (route.size() - 1 - route.indexOf(origin));

        if (upwardStops != downwardStops) {
            return false;
        }

        // Check that the segment of the route from origin to destination is the exact reverse of the segment from destination to origin
        for (int i = 0; i <= upwardStops; i++) {
            if (!route.get(originIndex + 1 + i).equals(route.get(route.size() - 2 - i))) {
                return false;
            }
        }

        return true;
    }


    // Ex 5 :
    //You need to write an algorithm to check whether a string made of "(" and ")" is balanced.
    //Definition:
    //•	Empty string is balanced
    //•	If A is a balanced string, then "(" + A + ")" is balanced (+ represents string concatenation)
    //
    //Example
    //"( ( ( ) ) )" is balanced. "( ) ) (" is not balanced
    public static boolean isBalanced(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

    // Ex 6 :
    //Let A an array of non null, distinct integers (positive or negative).
    //Write a method returning the value of the integer closest to zero. If two integers, one positive and one negative, are both equally close to zero, then the positive one should be returned.
    //Ex : [7,2,-3,5,-8,-2,6,-4] -> should return 2
    public static int closestToZero(int[] arr) {
        int closest = Integer.MAX_VALUE;
        for (int num : arr) {
            if (Math.abs(num) < Math.abs(closest) || (Math.abs(num) == Math.abs(closest) && num > closest)) {
                closest = num;
            }
        }
        return closest;
    }

    // Ex 7 :
    //
    //Part 1 - Write an algorithm that converts a Roman numeral base 10 number in a base 10 number.
    //I	V	X	L	C	D	M
    //1	5	10	50	100	500	1000
    //
    //Example : MMMDCI = 3601.
    public static int romanToDecimal(String roman) {
        Map<Character, Integer> map = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);
        int sum = 0, prev = 0;
        for (char c : roman.toCharArray()) {
            int curr = map.get(c);
            sum += curr;
            if (curr > prev) sum -= 2 * prev;
            prev = curr;
        }
        return sum;
    }

    // Ex 8 :
    //
    //Consider 2 lists of characters. Make an algorithm to find all the common characters to this 2 lists.
    //Example:
    // L1 = a, e, e, e , L2 = b, b, c, e, e, g , Result = e
    public static List<Character> findCommonCharacters(List<Character> list1, List<Character> list2) {
        List<Character> commonCharacters = new ArrayList<>();
        Map<Character, Integer> countMap = new HashMap<>();

        for (char c : list1) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        for (char c : list2) {
            if (countMap.containsKey(c) && countMap.get(c) > 0) {
                commonCharacters.add(c);
                countMap.put(c, countMap.get(c) - 1);
            }
        }

        return commonCharacters;
    }

    // Ex 9 :
    //
    //How do you find all pairs of an integer array whose sum is equal to a given number?
    //array : [0, 14, 0, 4, 7, 8, 3, 5, 7]
    //Sum : 11
    //pair of numbers from an array whose sum equals 11
    //(7, 4)
    //(3, 8)
    //(7, 4)
    public static List<int[]> findPairsWithSum(int[] arr, int sum) {
        List<int[]> pairs = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            int complement = sum - num;
            if (map.getOrDefault(complement, 0) > 0) {
                pairs.add(new int[]{num, complement});
                map.put(complement, map.get(complement) - 1);
            } else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        return pairs;
    }

    // Ex 10
    //Determine if a string is palindrome
    //1) Start with strings without spaces or special characters, by using basic string manipulation
    //“racecar” should return true
    //“magazine” should return false
    //2) Now include use case for strings with spaces,
    //e.g. “never odd or even” should return true
    public static boolean isPalindrome(String str) {
        str = str.replaceAll("\\s", "").toLowerCase();
        return new StringBuilder(str).reverse().toString().equals(str);
    }

    // Ex 11 :
    //Write a method which takes an array of integers (positive or negative)
    // as input parameter and will find the 2 integers that make the biggest product (multiplication).
    // The method will then write the 2 integers and the product on the system out.
    public static void findTwoBiggestProduct(int[] arr) {
        if (arr.length < 2) {
            System.out.println("Array should have at least two elements");
            return;
        }

        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for (int num : arr) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }

            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }

        int product1 = max1 * max2;
        int product2 = min1 * min2;

        if (product1 > product2) {
            System.out.println("The two integers are: " + max1 + " and " + max2);
        } else {
            System.out.println("The two integers are: " + min1 + " and " + min2);
            System.out.println("Their product is: " + product2);
        }
    }

    // Ex 12 :
    //Alan, a 9-year-old young boy is doing his homework.
    //Today, he needs to count the unnecessary 0 in floats ( ie the ones that can be deleted without consequences)
    //Can you help him by writing a method returning this number?
    //Please use only basic statements.
    //1234.560000
    //12.00000000
    //12345.60700
    public static int countUnnecessaryZeros(String floatStr) {
        int count = 0;
        for (int i = floatStr.length() - 1; i >= 0; i--) {
            if (floatStr.charAt(i) == '0') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    // Ex 13 :
    //Disclaimer : There is no link between this exercise and the reality.
    //Consider a molecule which is called a palindromic sequences.
    //It is composed of 2 sequences: a sequence A and its complementary sequence B.
    //Imagine also that a sequence is build of packs.
    //A pack can have different size.
    //
    //Example of a sequence :
    //{ [A,C,C], [T,A,G],[A,G,C],[T,G,G] }
    //
    //Examples of packs :
    //[A,C,C]
    //[A,C,C,T]
    //
    //Let's imagine a palindromic sequence is when a sequence A is symmetric with its complementary sequence B, with the respect of number of elements in a pack.
    //
    //Example of palindromic sequences :
    //A= {[A,C,C], [T,T,A,G],[G,G,G],[A,G,C],[T,G,G]}
    //B= {[G,G,T] ,[C,G,A],[G,G,G],[G,A,T,T],[C,C,A]}
    //A is palindromic with B.
    //
    //1- Write an algorithm checking if a sequence and his complementary one are palindromic.
    //Lets now imagine a double palindromic sequence.
    //It means that this sequence is a palindrome by itself.
    //For instance,
    //C = {[T,G,G,A] ,[A,G,C],[G,G,G],[C,G,A],[A, G,G,T]}
    //
    //2 - Write an algorithm that test if a sequence is a palindrome.
    public static boolean isPalindromicSequence(List<List<Character>> sequenceA, List<List<Character>> sequenceB) {
        if (sequenceA.size() != sequenceB.size()) return false;
        for (int i = 0; i < sequenceA.size(); i++) {
            List<Character> packA = sequenceA.get(i);
            List<Character> packB = sequenceB.get(sequenceB.size() - 1 - i);
            Collections.reverse(packB);
            if (!packA.equals(packB)) return false;
        }
        return true;
    }

    // Ex 14 :
    //Given a non sorted list of integer, make a new list with even
    // numbers at the beginning of the list in ascending order and the
    // odd number from the end of the list in descending order
    //Ex : [93, 24, 38, 1, 87, 100]  [24,36, 96, 100, 93, 87, 1]
    public static List<Integer> sortEvenOdd(List<Integer> list) {
        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();

        for (int num : list) {
            if (num % 2 == 0) {
                evens.add(num);
            } else {
                odds.add(num);
            }
        }

        Collections.sort(evens);
        Collections.sort(odds, Collections.reverseOrder());

        List<Integer> result = new ArrayList<>(evens);
        result.addAll(odds);

        return result;
    }


    // Ex 15 :
    //How do you check if two strings are anagrams of each other?
    //An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
    //For example: secure and rescue are anagrams
    public static boolean areAnagrams(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : str1.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        for (char c : str2.toCharArray()) {
            if (!countMap.containsKey(c) || countMap.get(c) == 0) return false;
            countMap.put(c, countMap.get(c) - 1);
        }
        return true;
    }

    // Ex 16 :
    //P1 (x1, y1) and P2 (x2, y2) are 2 points
    //The Euclidean distanced is √(x1-x2)²+(y1-y2)²
    //Given a set of planes in the sky, write a function that in a returning the couple of planes with the  smaller Euclidean distance.
    //Ex : [ [1,2], [3,4], [5,6], [1,3] ] -> [ [1,2], [1,3] ]
    public static double euclideanDistance(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }

    //Ex 17 :
    //Find in a string the character with the maximum number of occurrences ?
    //Example : “banana” -> a
    public static char maxOccurrenceChar(String str) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        return Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    // Ex 18 : An anagram is a word or phrase that is made by transposing the letters of another word or phrase.
    //For example: secure and rescue are anagrams
    //Write a program (using basic string manipulation) that verifies if two strings are anagrams or not
    //Ex 18
    //Write a function that returns all the duplicated elements in a list.
    //List =[A,V,Y,Q,A,O,Q,I,N]
    //Result =[A,Q]
    public static List<Character> findDuplicates(List<Character> list) {
        List<Character> duplicates = new ArrayList<>();
        Set<Character> seen = new HashSet<>();
        Set<Character> added = new HashSet<>();

        for (Character c : list) {
            if (seen.contains(c) && !added.contains(c)) {
                duplicates.add(c);
                added.add(c);
            } else {
                seen.add(c);
            }
        }

        return duplicates;
    }

    // Ex 19:
    //Write a function that can pad (left or right) a string to get to a determined length
    //Test Data :
    //'0000',123,'L'
    //'00000000',123,''
    //Output :
    //"0123"
    //"12300000"
    public static String padString(String str, int length, char padChar, char direction) {
        if (str.length() >= length) return str;
        int diff = length - str.length();
        StringBuilder sb = new StringBuilder();
        if (direction == 'L') {
            for (int i = 0; i < diff; i++) {
                sb.append(padChar);
            }
            sb.append(str);
        } else {
            sb.append(str);
            for (int i = 0; i < diff; i++) {
                sb.append(padChar);
            }
        }
        return sb.toString();
    }

    // Ex 20:
    //Write a method which takes an array of integers as
    // input parameter and returns the two integers of the array with the biggest absolute value.
    public static int[] findTwoBiggestAbsolute(int[] arr) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        for (int num : arr) {
            if (Math.abs(num) > Math.abs(max1)) {
                max2 = max1;
                max1 = num;
            } else if (Math.abs(num) > Math.abs(max2)) {
                max2 = num;
            }
        }
        return new int[]{max1, max2};
    }

    // Ex : 21 :
    //The median is the value allowing to split an array into two different arrays of same size, the first array containing values lower than the median, and the second array containing values higher than the median.
    //Examples: [3, 5, 1, 7, 9] -> the median is 5 ; [6, 3, 0, 4] -> the median is 3.5: (3 + 4) /2
    public static double findMedian(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        if (n % 2 == 0) {
            return (arr[n / 2 - 1] + arr[n / 2]) / 2.0;
        } else {
            return arr[n / 2];
        }
    }


     //Exercise 22: Calculate the perimeter of the given figure.
     //The figure is an L-shaped polygon with given dimensions:
     //- One horizontal segment of 5 cm
     //- One vertical segment of 6 cm
     //- One horizontal segment of 4 cm
     //The missing lengths can be inferred as follows:
     //- The total width is 5 cm
     //- The total height is 6 cm
     // Perimeter is the sum of all outer sides.
    public static int calculatePerimeter(int width, int height) {
        return 2 * (width + height);
    }


    public static void main(String[] args) {
        // Case 1: Find two biggest numbers in an array
        System.out.println("Case 1: Find two biggest numbers in an array");
        System.out.println(Arrays.toString(findTwoBiggest(new int[]{1, 9, 7, 3, 5})));

        // Case 2: Merge two sorted arrays
        System.out.println("Case 2: Merge two sorted arrays");
        System.out.println(Arrays.toString(mergeSortedArrays(new int[]{1, 9, 7, 3, 5}, new int[]{-1, -2, -3})));

        // Case 3: Clean float string values
        System.out.println("Case 3: Clean float string values");
        System.out.println(cleanFloatString("12.0000000000"));

        // Case 4: Check if route is symmetric
        System.out.println("Case 4: Check if route is symmetric");
        System.out.println(isRouteSymmetric("NCE", "TYO", List.of("NCE", "PAR", "DXB", "TYO", "DXB", "PAR", "NCE")));

        // Case 5: Check balanced parentheses
        System.out.println("Case 5: Check balanced parentheses");
        System.out.println(isBalanced("(()())"));

        // Case 6: Closest to zero
        System.out.println("Case 6: Closest to zero");
        System.out.println(closestToZero(new int[]{7, 2, -3, 5, -8, -2, 6, -4}));

        // Case 7: Convert Roman numerals to decimal
        System.out.println("Case 7: Convert Roman numerals to decimal");
        System.out.println(romanToDecimal("MMMDCI"));

        // Case 8: Find common characters in two lists
        System.out.println("Case 8: Find common characters in two lists");
        System.out.println(findCommonCharacters(List.of('a', 'e', 'e', 'e'), List.of('b', 'b', 'c', 'e', 'e', 'g')));

        // Case 9: Find pairs with sum
        System.out.println("Case 9: Find pairs with sum");
        System.out.println(findPairsWithSum(new int[]{0, 14, 0, 4, 7, 8, 3, 5, 7}, 11));

        // Case 10: Check if string is palindrome
        System.out.println("Case 10: Check if string is palindrome");
        System.out.println(isPalindrome("racecar"));

        // Case 11: Finding the two integers in an array that make the biggest product:
        System.out.println("Case 11: Finding the two integers in an array that make the biggest product");
        findTwoBiggestProduct(new int[]{7, 2, -3, 5, -8, -2, 6, -4});

        // Case 12: Count the unnecessary zeros in a float string
        System.out.println("Case 12: Count the unnecessary zeros in a float string");
        System.out.println(countUnnecessaryZeros("1234.560000"));

        // Case 13: Check if a sequence and its complementary are palindromic
        System.out.println("Case 13: Check if a sequence and its complementary are palindromic");
        List<List<Character>> sequenceA = List.of(List.of('A', 'C', 'C'), List.of('T', 'A', 'G'), List.of('A', 'G', 'C'), List.of('T', 'G', 'G'));
        List<List<Character>> sequenceB = List.of(List.of('G', 'G', 'T'), List.of('C', 'G', 'A'), List.of('G', 'G', 'G'), List.of('G', 'A', 'T', 'T'), List.of('C', 'C', 'A'));
        System.out.println(isPalindromicSequence(sequenceA, sequenceB));

        // Case 14: Sort even numbers at the beginning and odd numbers at the end
        System.out.println("Case 14: Sort even numbers at the beginning and odd numbers at the end");
        System.out.println(sortEvenOdd(List.of(93, 24, 38, 1, 87, 100)));

        // Case 15: Check if two strings are anagrams
        System.out.println("Case 15: Check if two strings are anagrams");
        System.out.println(areAnagrams("secure", "rescue"));

        // Case 16: Find the two planes with the smallest Euclidean distance
        System.out.println("Case 16: Find the two planes with the smallest Euclidean distance");
        System.out.println(euclideanDistance(new int[]{1, 2}, new int[]{3, 4}));

        // Case 17: Find in a string the character with the maximum number of occurrences ?
        System.out.println("Case 17: Find in a string the character with the maximum number of occurrences ?");
        System.out.println(maxOccurrenceChar("banana"));

        // Case 18: Find duplicates in a list
        System.out.println("Case 18: Find duplicates in a list");
        System.out.println(findDuplicates(List.of('A', 'V', 'Y', 'Q', 'A', 'O', 'Q', 'I', 'N')));

        // Case 19: Pad a string to a determined length
        System.out.println("Case 19: Pad a string to a determined length");
        System.out.println(padString("0000", 4, '0', 'L'));

        // Case 20: Find the two integers with the biggest absolute value
        System.out.println("Case 20: Find the two integers with the biggest absolute value");
        System.out.println(Arrays.toString(findTwoBiggestAbsolute(new int[]{7, 2, -3, 5, -8, -2, 6, -4})));

        // Case 21: Find the median of an array
        System.out.println("Case 21: Find the median of an array");
        System.out.println(findMedian(new int[]{3, 5, 1, 7, 9}));

        // Case 22: Calculate the perimeter of the given figure
        System.out.println("Case 22: Calculate the perimeter of the given figure");
        System.out.println(calculatePerimeter(5, 6));
    }
}
