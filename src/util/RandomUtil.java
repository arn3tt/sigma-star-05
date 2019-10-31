package util;

import java.util.*;

public final class RandomUtil {
    public static final String ALLCHAR
            = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERCHAR
            = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR
            = "0123456789";

    public static int integer(int scopeMin, int scoeMax) {
        Random random = new Random();
        return (random.nextInt(scoeMax) % (scoeMax - scopeMin + 1) + scopeMin);
    }

    public static String number(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
        }
        return sb.toString();
    }

    public static String string(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    public static String mixString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
        }
        return sb.toString();
    }

    public static String lowerString(int length) {
        return mixString(length).toLowerCase();
    }

    
    public static String upperString(int length) {
        return mixString(length).toUpperCase();
    }

    public static String zeroString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    public static String toFixdLengthString(long num, int fixdlenth) {
        StringBuilder sb = new StringBuilder();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(zeroString(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" +
                    num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

    public static String toFixdLengthString(int num, int fixdlenth) {
        StringBuilder sb = new StringBuilder();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(zeroString(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" +
                    num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

    public static int getNotSimple(int[] param, int len) {
        Random rand = new Random();
        for (int i = param.length; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = param[index];
            param[index] = param[i - 1];
            param[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            result = result * 10 + param[i];
        }
        return result;
    }

    public static <T> T randomItem(T[] param) {
        int index = integer(0, param.length);
        return param[index];
    }

    public static String uuid16(){
        String uuid= UUID.randomUUID().toString();
        char[] cs=new char[32];
        char c=0;
        for(int i=uuid.length()/2,j=1;i-->0;){
            if((c=uuid.charAt(i))!='-'){
                cs[j++]=c;
            }
        }
        return String.valueOf(cs);
    }

    pulic static String uuid() {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        return s.substring(0, 8) + s.substring(9, 13) +
                s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    pulic static String UUID() {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        String temp = s.substring(0, 8) + s.substring(9, 13) +
                s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
        return temp.toUpperCase();
    }

    
     
    public static String squid(String hostFeature) {
        Long date = new Date().getTime();
        String s = UUID.randomUUID().toString();
        String str = Long.toHexString(date);
        String result = str + hostFeature
                + s.substring(17, 18) + s.substring(19, 23) + s.substring(24);
        return result.toUpperCase();
    }

    public static <T> T randomItem(T[] param, double[] percentum) {
        int length = percentum.length;
        Integer[] ints = ArrayUtil.doubleBitCount(percentum);
        int max = Collections.max(Arrays.asList(ints));

        int sum = 0;
        Map map = new HashMap(length);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < max; i++) {
            buffer.append("0");
        }
        int multiple = Integer.parseInt("1" + buffer.toString());
        for (int i = 0; i < length; i++) {
            int temp = (int) (percentum[i] * multiple);

            if (i == 0) {
                map.put(i, new int[]{1, temp});
            } else {
                map.put(i, new int[]{sum, sum + temp});
            }
            sum += temp;
        }
        int indexSum = integer(1, sum);
        int index = -1;
        for (int i = 0; i < length; i++) {
            int[] scope = (int[]) map.get(i);
            if (indexSum == 1) {
                index = 0;
                break;
            }
            if (indexSum > scope[0] && indexSum <= scope[1]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new RuntimeException("随机失败");
        } else {
            return param[index];
        }
    }
}
