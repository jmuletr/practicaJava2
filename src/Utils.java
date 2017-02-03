import java.util.Arrays;
import java.util.Collections;

/**
 * Created by jmuletr on 2/02/17.
 */
public class Utils {
    static String fToString(float[] cfs){
        int exp = cfs.length - 1;
        int fact;
        String coefs ="";
        for (int i = 0; i < cfs.length; i++) {
            fact = (int) cfs[i];
            if (fact == 0 && exp >= 0) {
                exp--;

                if (i == cfs.length - 1) {
                    coefs = "0";
                }
                continue;
            } else {
                //nombres positius
                if (fact >= 0) {
                    //primer valor sense signe
                    if (i == 0) {
                        if (exp > 0) {
                            if (fact == 1) {
                                coefs = "x";
                                if (exp > 1) {
                                    coefs += "^" + exp;
                                }
                            } else if (fact > 1) {
                                coefs = Integer.toString(fact);
                                coefs += "x";
                                if (exp > 1) {
                                    coefs += "^" + exp;
                                }
                            }
                            //exponent 0
                        } else if (fact == 0) {
                            coefs = "0";
                        } else if (fact > 0) {
                            coefs += " + " + fact;
                        }

                        //valors amb signe
                    } else if (i > 0) {
                        coefs += " + ";
                        if (exp == 0) {
                            if (fact == 0) {
                                coefs += "0";
                            } else if (fact > 0) {
                                coefs += fact;
                            }
                        } else if (exp > 0) {
                            if (fact == 1) {
                                coefs += "x";
                                if (exp > 1) {
                                    coefs += "^" + exp;
                                }
                            } else if (fact > 1) {
                                coefs += Integer.toString(fact);
                                coefs += "x";
                                if (exp > 1) {
                                    coefs += "^" + exp;
                                }

                            }
                        } else coefs += fact;
                        //nombres negatius
                    }
                } else if (fact < 0) {
                    if (i == 0) {
                        coefs = "-";
                        fact = fact * -1;
                        if (exp > 0) {
                            if (fact == 1) {
                                coefs += "x";
                                if (exp > 1) {
                                    coefs += "^" + exp;
                                }
                            } else if (fact > 1) {
                                coefs += Integer.toString(fact);
                                coefs += "x";
                                if (exp > 1) {
                                    coefs += "^" + exp;
                                }
                            }
                        }
                        //valors amb signe
                    } else if (i > 0) {
                        coefs += " - ";
                        if (exp == 0) {
                            if (fact == 0) {
                                coefs += "0";
                            } else if (fact < 0) {
                                fact = fact * -1;
                                coefs += fact;
                            }
                        } else if (exp > 0) {
                            if (exp > 0) {
                                fact = fact * -1;
                                if (fact == 1) {
                                    coefs += "x";
                                    if (exp > 1) {
                                        coefs += "^" + exp;
                                    }
                                } else if (fact > 1) {
                                    coefs += Integer.toString(fact);
                                    coefs += "x";
                                    if (exp > 1) {
                                        coefs += "^" + exp;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            exp--;
        }
        return coefs;
    }

    static String[][] identifyFact(String s){
        s = s.replace("- ", "-");
        s = s.replace("+ ", "+");
        String[] monomial = s.split(" ");
        String[][] coefExp = new String[2][monomial.length];
        int coef, exp;
        for (int i = 0; i < monomial.length; i++) {
            if (monomial[i].contains("x")){
                if (monomial[i].matches(".*\\d[x].*")){
                    coef = Integer.parseInt(monomial[i].substring(0,monomial[i].indexOf('x')));
                    coefExp[0][i] = Integer.toString(coef);
                    if (monomial[i].matches(".*\\d[x]\\^\\d*")){
                        exp = Integer.parseInt(monomial[i].substring(monomial[i].indexOf('^')+1));
                        coefExp[1][i] = Integer.toString(exp);
                    }else coefExp[1][i] = "1";
                } else {
                    if (monomial[i].matches("[-][x]\\^\\d*")){coefExp[0][i] = "-1";}else coefExp[0][i] = "1";
                    if (monomial[i].matches(".*[x]\\^\\d*")){
                        exp = Integer.parseInt(monomial[i].substring(monomial[i].indexOf('^')+1));
                        coefExp[1][i] = Integer.toString(exp);
                    }else coefExp[1][i] = "1";
                }
            }else if (!monomial[i].contains("x")){
                coef = Integer.parseInt(monomial[i]);
                coefExp[0][i] = Integer.toString(coef);
                coefExp[1][i] = "0";
            }
        }
        coefExp = order(coefExp);
        return coefExp;
    }

    static String[][] order(String[][] s){
        String[][] s2 = new String[2][s[0].length];
        String[] order = new String[s[0].length];
        for (int i = 0; i < order.length; i++) {
            order[i] = s[1][i];
        }
        Arrays.sort(order, Collections.reverseOrder());
        for (int i = 0; i < order.length; i++) {
            for (int x = 0; x < order.length; x++) {
                if (order[i] == s[1][x]){
                    s2[0][i] = s[0][x];
                    s2[1][i] = s[1][x];
                    s[1][x] = null;
                    break;
                }
            }
        }
        s2 = add(s2);
        return s2;
    }

    private static String[][] add(String[][] s) {
        int val1, val2;
        for (int i = 0; i < s[0].length - 1; i++) {
            if (Integer.parseInt(s[1][i]) == Integer.parseInt(s[1][i + 1])){
                val1 = Integer.parseInt(s[0][i]);
                val2 = Integer.parseInt(s[0][i + 1]);
                s[0][i + 1] = Integer.toString(val1 + val2);
                s[0][i] = null;
                s[1][i] = null;
            }
        }
        return s;
    }

    static float[] sToFloat(String[][] s){
        int maxExp = Integer.parseInt(s[1][0]);
        int counter = 0;
        int counter2 = 0;
        float[] coef = new float[maxExp + 1];
        for (int i = 0; counter2 < Integer.parseInt(s[1][0]) + 1; i++) {
            if (s[0][counter] != null){
                if (maxExp == Integer.parseInt(s[1][counter])){
                    coef[counter2] = Integer.parseInt(s[0][counter]);
                    counter++;
                    counter2++;
                    maxExp--;
                }else{
                    coef[counter2] = 0;
                    maxExp--;
                    counter2++;
                }
            }else counter++;
        }
        return coef;
    }
}
