import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class TestTasks {
    public static void main(String[] args) {
        //перевернуть число через лонг
        System.out.println("========================");
        System.out.println(reverseInt(-123));

        //перевернуть число через StringBuilder с минусом
        System.out.println("========================");
        System.out.println(reverseIntReverse(-123));

        //подсчет слов
        System.out.println("========================");
        System.out.println(countWords("a b c d d c b"));

        //обьединение двух сортированых массивов int
        System.out.println("========================");
        int[] a = {1, 5, 6};
        int[] b = {2, 7, 10, 11};
        System.out.println(Arrays.toString(merge(a, b)));

        //итерации объекта типа HashMap с использованием цикла while и for
        System.out.println("========================");
        iteratedMap();

        //пересичение масивов int
        System.out.println("========================");
        System.out.println("пересичение масивов int " + Arrays.toString(intersection(new int[]{1, 2, 3}, new int[]{2, 2, 1})));

        //lucky
        System.out.println("========================");
        System.out.println("lucky " + findLucky(new int[]{2, 2, 3, 4}));


        //romanToInt
        System.out.println("========================");
        System.out.println("romanToInt " + romanToInt("III"));

        //mergeTwoArrays
        System.out.println("========================");
        System.out.println("Слияние двух масивов: ");
        int[] result = merge(new int[] {1,3,2,6,7,10,9}, new int[] {3,6,2,7,8});
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }

        System.out.println("========================");
        System.out.println("Найти дубликат в массиве: ");
        System.out.println(findDuplicateNumber(List.of(1,2,3,4,5,6,7,8,8,9,10)));

        System.out.println("========================");
        System.out.println("полиндром: ");
        String s = "babad";
        System.out.println(longestPalindrome(s));

        System.out.println("======Длина уникальной самой длинной подстроки============");
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println("======посчитать символы в строке============");
        System.out.println(checkChar("AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBB"));
    }

    public static String checkChar(String str) {
        for (char s : str.toCharArray()) {
            if (Character.isDigit(s)) {
                throw new NumberFormatException("String contains number!");
            }
        }

        List<String> listStrs = Arrays.stream(str.split("")).toList();
        Map<String, Integer> mapStrs = new LinkedHashMap<>();
        String result = "";
        int count = 0;
        for (int i = 0; i < listStrs.size(); i++) {
            if (i + 1 < listStrs.size()) {
                if (listStrs.get(i).equals(listStrs.get(i + 1))) {
                    count += 1;
                } else {
                    if (!mapStrs.containsKey(listStrs.get(i))) {
                        mapStrs.put(listStrs.get(i), count + 1);
                        count = 0;
                    } else {
                        mapStrs.put(listStrs.get(i) + "_", count + 1);
                        count = 0;
                    }
                }
            } else {
                mapStrs.put(listStrs.get(i) + "_", count + 1);
            }
        }

        for (String key : mapStrs.keySet()) {
            if (mapStrs.get(key) == 1) {
                result += key.replace("_", "");
            } else {
                result += key.replace("_", "") + mapStrs.get(key);
            }
        }
        return result;
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character>set=new HashSet<>();
        int maxLength=0;
        int left=0;
        for(int right=0;right<s.length();right++){

            if(!set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                maxLength=Math.max(maxLength,right-left+1);

            }else{
                while(s.charAt(left)!=s.charAt(right)){
                    set.remove(s.charAt(left));
                    left++;
                }
                set.remove(s.charAt(left));left++;
                set.add(s.charAt(right));
            }

        }
        return maxLength;
    }
    private static boolean check(int i, int j, String s) {
        int left = i;
        int right = j - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
    public static String longestPalindrome(String s) {
        for (int length = s.length(); length > 0; length--) {
            for (int start = 0; start <= s.length() - length; start++) {
                if (check(start, start + length, s)) {
                    return s.substring(start, start + length);
                }
            }
        }

        return "";
    }
    public static void iteratedMap() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("2", "2");
        map.put("3", "2");
        map.put("4", "2");

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        System.out.println("===============While==============");
        while (iterator.hasNext()) {
            var i = iterator.next();
            System.out.println("key: " + i.getKey() + " value: " + i.getValue());
        }
        System.out.println("===============Foreach==============");
        for (String key : map.keySet()) {
            System.out.println("key: " + key + " value: " + map.get(key));
        }

    }

    public static int reverseInt(int input) {
        long reversedNum = 0;
        long input_long = input;
        while (input_long != 0) {
            reversedNum = reversedNum * 10 + input_long % 10;
            input_long = input_long / 10;
        }
        if (reversedNum > Integer.MAX_VALUE || reversedNum < Integer.MIN_VALUE) {
            throw new IllegalArgumentException();
        }
        return (int) reversedNum;
    }

    public static int reverseIntReverse(int i) {
        var ch = String.valueOf(i);
        var chMass = ch.toCharArray();
        String symbol;
        StringBuilder str = new StringBuilder();

        if (!Character.isDigit(chMass[0])) {
            symbol = String.valueOf(chMass[0]);
            for (int j = 1; j < chMass.length; j++) {
                str.append(chMass[j]);
            }
            StringBuilder stringBuilder = new StringBuilder(str);
            return parseInt(symbol + stringBuilder.reverse());
        } else {
            StringBuilder stringBuilder = new StringBuilder(String.valueOf(i));

            return parseInt(stringBuilder.reverse().toString());
        }
    }

    public static Map<String, Long> countWords(String str) {
        return new ArrayList<>(Arrays.asList(str.split("\\s")))
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static int[] merge(int[] a, int[] b) {
        int[] answer = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length)
            answer[k++] = a[i] < b[j] ? a[i++] : b[j++];
        while (i < a.length)
            answer[k++] = a[i++];
        while (j < b.length)
            answer[k++] = b[j++];
        return answer;
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        return Arrays.stream(nums1)
                .distinct()
                .filter(x -> Arrays.stream(nums2).anyMatch(y -> y == x))
                .toArray();
    }

    public static int findLucky(int[] arr) {
        int[] count = new int[501];
        for (int n : arr) {
            count[n]++;
        }
        int max = -1;
        for (int i = 1; i < count.length; i++) {
            if (count[i] == i) max = i;
        }
        return max;
    }

    public static int romanToInt(String s) {
        int ans = 0, num = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            switch(s.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            if (4 * num < ans) ans -= num;
            else ans += num;
        }
        return ans;
    }

    public int[] mergeArrays(int[] array1, int[] array2) {
        //        int[] answer = new int[a.length + b.length];
//        int i = 0, j = 0, k = 0;
//        while (i < a.length && j < b.length)
//            answer[k++] = a[i] < b[j] ? a[i++] :  b[j++];
//        while (i < a.length)
//            answer[k++] = a[i++];
//        while (j < b.length)
//            answer[k++] = b[j++];
//        return answer;

        return IntStream.concat(Arrays.stream(array1), Arrays.stream(array2))
                .sorted()
                .toArray();
    }

    public static int findDuplicateNumber(List<Integer> numbers) {

//        Map<Integer, Long> map = numbers.stream()
//                .collect(Collectors.groupingBy(Function.identity(),
//                        Collectors.counting()));
//        var listKey = map.keySet().stream().toList();
//
//        for (int i = 0; i < listKey.size(); i++) {
//            if (map.get(listKey.get(i)) > 1) {
//                return listKey.get(i);
//            }
//        }

//        return numbers.stream()
//                .filter(i -> Collections.frequency(numbers, i) > 1)
//                .collect(Collectors.toSet())
//                .stream().toList().get(0);

        return numbers
                .stream()
                .mapToInt(Integer::valueOf)
                .sum() -
                new HashSet<>(numbers)
                        .stream()
                        .mapToInt(Integer::valueOf)
                        .sum();
    }
}

