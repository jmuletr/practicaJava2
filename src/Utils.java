import java.util.Arrays;


/**
 * Created by jmuletr on 2/02/17.
 */
public class Utils {

    static String fToString(float[] cfs){
        int exp = cfs.length - 1;
        int fact;
        int first = 0;
        boolean isFirst = false;
        String coefs ="";
        for (int i = 0; i < cfs.length; i++) {
            fact = (int) cfs[i];
            if (cfs[i] != 0 && !isFirst){
                first = i;
                isFirst = true;
            }
            if (fact == 0 && exp >= 0) {
                exp--;

                if (i == cfs.length - 1 && coefs == "") {
                    coefs = "0";
                }
                continue;
            } else {

                    //primer valor
                    if (i == first) {
                        if (fact < 0) {
                            coefs = "-";
                            fact = fact * -1;
                        }
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
                            //exponent 0
                        } else if (fact == 0) {
                            coefs = "0";
                        } else if (fact > 0) {
                            coefs += " + " + fact;
                        }

                        //valors amb signe
                    } else if (i > 0) {
                        if (fact >= 0) {
                            coefs += " + ";
                        }else {
                            coefs += " - ";
                            fact = fact * -1;
                        }
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
        int[] IntArray = new int[order.length];

        for (int i = 0; i < order.length; i++) {
            IntArray[i] = Integer.parseInt(s[1][i]);
        }
        Arrays.sort(IntArray);
        for (int i = order.length - 1, x = 0; i >=0 ; i--, x++) {
            order[x] = Integer.toString(IntArray[i]);
        }

        for (int i = 0; i < order.length; i++) {
            for (int x = 0; x < order.length; x++) {
                if (order[i].equalsIgnoreCase(s[1][x])){
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
        boolean y = false;
        //notNull
        int nN = 0;
        for (int i = 0; y != true; i++) {
            if (s[0][i] != null){
                nN = i;
                y = true;
            }
        }

        int maxExp = Integer.parseInt(s[1][nN]);
        int counter = 0;
        int counter2 = 0;
        float[] coef = new float[maxExp + 1];
        while ( counter2 < Integer.parseInt(s[1][nN]) + 1) {
            if (counter < s[0].length){
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
            }else counter2++;
        }
        return coef;
    }

    static int monTotal(String s){
        s = s.replace("- ", "-");
        s = s.replace("+ ", "+");
        String[] monomial = s.split(" ");
        return monomial.length;
    }

    static float[] simpleRoot(Polynomial p , int numExp){
        float[] results = new float[p.coef.length - 1];
        //polinomis simples

            if (p.coef.length - 1 == 2){
                if (p.coef[p.coef.length - 1] < 0){
                    p.coef[p.coef.length - 1] *= -1;
                }
                float x = (float)Math.sqrt(p.coef[p.coef.length - 1]);
                results[1] = x;
                results[0] = x * -1;
                return results;
            }
            else {
                if (p.coef[p.coef.length - 1] < 0){
                    p.coef[p.coef.length - 1] *= -1;
                }
                results[0] = p.coef[p.coef.length -1];
                return results;
            }
    }

    static float[] secondRoot(Polynomial p, int numExp) {
        float[] results = new float[p.coef.length - 1];
        float a = p.coef[0];
        float b = p.coef[1];
        float c = p.coef[2];
        float disc = b * b - 4 * a * c;
        if (disc > 0){
            results[0] = ((-1 * b) + (float)Math.sqrt(disc)) / (2 * a);
            results[1] = ((-1 * b) - (float)Math.sqrt(disc)) / (2 * a);
            Arrays.sort(results);
            return results;
        }else if (disc == 0){
            results = new float[(p.coef.length - 1)/2];
            results[0] = ((-1 * b) + (float)Math.sqrt(disc)) / (2 * a);
            return results;
        }else if (disc < 0){
            return null;
        }
        return null;
    }

    static float[] bicuadRoot(Polynomial pol, int numExp) {
        float[] results = new float[pol.coef.length - 1];
        float[] secondGrade = {pol.coef[0],pol.coef[2],pol.coef[4]};
        Polynomial p = new Polynomial(secondGrade);
        float[] results2 = new float[(pol.coef.length - 1)/2];
        results2 = Utils.secondRoot(p, numExp);
        if (results2.length == 2){
            for (int i = 0, x = 3; i < 2; i++, x--) {
                results[i] = (float)Math.sqrt(results2[i]);
                results[x] = (float)Math.sqrt(results2[i]) * -1;
            }
        }
        if (results2.length == 1){
            results = new float[2];
            results[0] = (float)Math.sqrt(results2[0]);
            results[1] = (float)Math.sqrt(results2[0]) * -1;
        }
        Arrays.sort(results);
        return results;
    }
}
