package quxiqi.leetcode._10;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/7/2 16:14
 **/
public class Func_10_2 implements Run10.Func_10 {
    @Override
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (s.length() == 0) {
            return false;
        }
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();

        int pcIndex = 0;
        char preChar = '!';
        out:
        for (int i = 0; i < sc.length; i++) {
            if (pcIndex >= pc.length) {
                return false;
            }
            if(pc[pcIndex] == '*') {
                if (sc[i] != preChar && preChar != '.') {
                    pcIndex++;
                    i--;
                    continue ;
                }
                if (preChar == '.') {
                    if (pcIndex + 1 >= pc.length) {
                        return true;
                    } else {
                        boolean hasp = false;
                        for (int pci = pcIndex + 1; pci < pc.length; pci++) {
                            if (pc[pci] >= 'a' && pc[pci] <= 'z') {
                                pcIndex = pci;
                                hasp = true;
                                break;
                            }
                        }
                        if (!hasp) {
                            return true;
                        }

                        for (int sci = i; sci < sc.length; sci++) {
                            if (pc[pcIndex] == sc[sci]) {
                                i = sci - 1;
                                continue out;
                            }
                        }
                        return false;
                    }
                }
            } else if (pc[pcIndex] == '.') {
                preChar = '.';
                pcIndex++;
            } else if (pc[pcIndex] == sc[i]) {
                preChar = sc[i];
                pcIndex++;
            } else if (pcIndex < pc.length - 1 && pc[pcIndex + 1] == '*'){
                pcIndex = pcIndex + 2;
                i--;
            } else {
                return false;
            }
        }

        if (pcIndex < pc.length) {
            if (pc[pcIndex] != '*') {
                return false;
            }
        }
        return true;
    }
}
