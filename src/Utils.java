import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by jmuletr on 2/02/17.
 */
class Utils {

    static String fToString(float[] cfs) {
        int exp = cfs.length - 1;
        int fact;
        int first = 0;
        boolean isFirst = false;
        String coefs = "";
        //Comprovam totes les posicions del array
        for (int i = 0; i < cfs.length; i++) {
            //assignam el valor de la posicio i del array a la variable fact
            fact = (int) cfs[i];
            //sercam quin monomi es el major exponent
            if (fact != 0 && !isFirst) {
                first = i;
                isFirst = true;
            }

            //si el monomi val 0 i el exponent es major que 0 reduim el contador exp, si el exponent es 0 i no hi ha
            //res dins el string coefs es dona valor 0 al string
            if (fact == 0 && exp >= 0) {
                exp--;

                if (i == cfs.length - 1 && coefs.equals("")) {
                    coefs = "0";
                }
                continue;
            }
            //si el monomi no es 0 s'entra al else
            else {

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
                    } else {
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

    static String[][] identifyFact(String s) {
        //juntam els signes al valor que els segueix
        s = s.replace("- ", "-");
        s = s.replace("+ ", "+");
        //separam el string en un array pels espais que queden davant cada signe
        String[] monomial = s.split(" ");
        String[][] coefExp = new String[2][monomial.length];
        int coef, exp;
        //recorrem totes les posicions del array monomial
        for (int i = 0; i < monomial.length; i++) {
            //monomis amb exponent 1 o major
            if (monomial[i].contains("x")) {
                //monomis amb valor major a 1
                if (monomial[i].matches(".*\\d[x].*")) {
                    coef = Integer.parseInt(monomial[i].substring(0, monomial[i].indexOf('x')));
                    coefExp[0][i] = Integer.toString(coef);
                    //monomis amb exponent major a 1
                    if (monomial[i].matches(".*\\d[x]\\^\\d*")) {
                        exp = Integer.parseInt(monomial[i].substring(monomial[i].indexOf('^') + 1));
                        coefExp[1][i] = Integer.toString(exp);
                    }
                    //exponent 1
                    else coefExp[1][i] = "1";
                }
                //monomis amb valor 1
                else {
                    //monomi amb valor -1
                    if (monomial[i].matches("[-][x]\\^\\d*")) {
                        coefExp[0][i] = "-1";
                    } else coefExp[0][i] = "1";
                    //identificacio del valor del exponent major que 1
                    if (monomial[i].matches(".*[x]\\^\\d*")) {
                        exp = Integer.parseInt(monomial[i].substring(monomial[i].indexOf('^') + 1));
                        coefExp[1][i] = Integer.toString(exp);
                    } else coefExp[1][i] = "1";
                }
            }

            //monomis amb exponent 0
            else if (!monomial[i].contains("x")) {
                coef = Integer.parseInt(monomial[i]);
                coefExp[0][i] = Integer.toString(coef);
                coefExp[1][i] = "0";
            }
        }
        coefExp = order(coefExp);
        return coefExp;
    }

    private static String[][] order(String[][] s) {
        String[][] s2 = new String[2][s[0].length];
        String[] order = new String[s[0].length];
        int[] IntArray = new int[order.length];

        //omplim un nou array de int amb els valors dels exponents transformats de string a int per a poder ordenarlos
        for (int i = 0; i < order.length; i++) {
            IntArray[i] = Integer.parseInt(s[1][i]);
        }
        //ordenam els valors de major a menor
        Arrays.sort(IntArray);
        //omplim el array order amb els valors de intArray pasanlos a string
        for (int i = order.length - 1, x = 0; i >= 0; i--, x++) {
            order[x] = Integer.toString(IntArray[i]);
        }

        //ordenam els monomis mantenguent el valor del monomi amb la mateixa posicio que el seu exponent
        for (int i = 0; i < order.length; i++) {
            for (int x = 0; x < order.length; x++) {
                if (order[i].equalsIgnoreCase(s[1][x])) {
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
            //comprovam si els dos monomis correlatius tenen el mateix exponent per sumarlos i eliminar el primer
            //i deixar el segon amb el resultat de l'operacio
            if (Integer.parseInt(s[1][i]) == Integer.parseInt(s[1][i + 1])) {
                val1 = Integer.parseInt(s[0][i]);
                val2 = Integer.parseInt(s[0][i + 1]);
                s[0][i + 1] = Integer.toString(val1 + val2);
                s[0][i] = null;
                s[1][i] = null;
            }
        }
        return s;
    }

    static float[] sToFloat(String[][] s) {
        boolean y = false;
        //notNull
        int nN = 0;
        for (int i = 0; !y; i++) {
            if (s[0][i] != null) {
                nN = i;
                y = true;
            }
        }

        int maxExp = Integer.parseInt(s[1][nN]);
        int counter = 0;
        int counter2 = 0;
        float[] coef = new float[maxExp + 1];
        while (counter2 < Integer.parseInt(s[1][nN]) + 1) {
            if (counter < s[0].length) {
                if (s[0][counter] != null) {
                    if (maxExp == Integer.parseInt(s[1][counter])) {
                        coef[counter2] = Integer.parseInt(s[0][counter]);
                        counter++;
                        counter2++;
                        maxExp--;
                    } else {
                        coef[counter2] = 0;
                        maxExp--;
                        counter2++;
                    }
                } else counter++;
            } else counter2++;
        }
        return coef;
    }

    static int monTotal(String s) {
        s = s.replace("- ", "-");
        s = s.replace("+ ", "+");
        String[] monomial = s.split(" ");
        return monomial.length;
    }

    static float[] simpleRoot(Polynomial p) {
        float[] results = new float[2];
        //polinomis simples
        if (p.coef.length - 1 == 1) {
            results = new float[1];
            if (p.coef[p.coef.length - 1] < 0) {
                p.coef[p.coef.length - 1] *= -1;
            }
            results[0] = p.coef[p.coef.length - 1];
            return results;
        } else if (p.coef.length - 1 > 1) {
            if ((p.coef.length - 1) % 2 == 0) {
                if (p.coef[p.coef.length - 1] < 1) {
                    p.coef[p.coef.length - 1] *= -1;
                } else {
                    return null;
                }
                float x = (float) Math.pow(p.coef[p.coef.length - 1], 1.0 / (p.coef.length - 1));
                results[1] = x;
                results[0] = x * -1;
                return results;
            }
            if ((p.coef.length - 1) % 2 != 0) {
                if (p.coef[p.coef.length - 1] < 0) {
                    return null;
                } else {
                    results = new float[1];
                    results[0] = (float) Math.pow(p.coef[p.coef.length - 1], 1.0 / (p.coef.length - 1));
                    results[0] *= -1;
                    return results;
                }
            }
        }
        return null;
    }

    static float[] secondRoot(Polynomial p) {
        float[] results = new float[p.coef.length - 1];
        float a = p.coef[0];
        float b = p.coef[1];
        float c = p.coef[2];
        float disc = b * b - 4 * a * c;
        if (disc > 0) {
            results[0] = ((-1 * b) + (float) Math.sqrt(disc)) / (2 * a);
            results[1] = ((-1 * b) - (float) Math.sqrt(disc)) / (2 * a);
            Arrays.sort(results);
            return results;
        } else if (disc == 0) {
            results = new float[(p.coef.length - 1) / 2];
            results[0] = ((-1 * b) + (float) Math.sqrt(disc)) / (2 * a);
            return results;
        } else if (disc < 0) {
            return null;
        }
        return null;
    }

    static float[] bicuadRoot(Polynomial pol) {
        float[] results = new float[pol.coef.length - 1];
        float[] secondGrade = {pol.coef[0], pol.coef[2], pol.coef[4]};
        Polynomial p = new Polynomial(secondGrade);
        float[] results2 = new float[(pol.coef.length - 1) / 2];
        results2 = Utils.secondRoot(p);
        if (results2.length == 2) {
            for (int i = 0, x = 3; i < 2; i++, x--) {
                results[i] = (float) Math.sqrt(results2[i]);
                results[x] = (float) Math.sqrt(results2[i]) * -1;
            }
        }
        if (results2.length == 1) {
            results = new float[2];
            results[0] = (float) Math.sqrt(results2[0]);
            results[1] = (float) Math.sqrt(results2[0]) * -1;
        }
        Arrays.sort(results);
        return results;
    }

    static float[] ruffini(Polynomial p) {
        int count = 1;
        Polynomial temp = new Polynomial(p.coef);
        List<Float> pResults;
        pResults = new ArrayList<Float>();
        float[] coeficients = temp.coef;
        int[] valX;
        float[] tempCoef = new float[coeficients.length - 1];
        while (count < p.coef[p.coef.length - 1]) {
            //Valor de x
            if (p.coef[p.coef.length - 1] > 0) {
                valX = div((int) p.coef[p.coef.length - 1], count);
            } else {
                valX = div((int) (p.coef[p.coef.length - 1]) * -1, count);
            }
            //Coeficient del polinomi
            float resultPol = 0;
            //Recorrer els coeficients
            for (int j = 0; j < coeficients.length; j++) {
                //Multiplicar el valor parcial el valor de x mes el coeficient
                resultPol = resultPol * valX[0] + coeficients[j];
                if (j < tempCoef.length) {
                    tempCoef[j] = resultPol;
                }
            }
            if (resultPol == 0) {
                pResults.add((float) valX[0]);
                temp = new Polynomial(tempCoef);
                coeficients = temp.coef;
                tempCoef = new float[tempCoef.length - 1];
            } else {
                //Recorrer els coeficients amb x negatiu
                for (int j = 0; j < coeficients.length; j++) {
                    resultPol = resultPol * valX[1] + coeficients[j];
                    if (j < tempCoef.length) {
                        tempCoef[j] = resultPol;
                    }
                }
                if (resultPol == 0) {
                    pResults.add((float) valX[0]);
                    temp = new Polynomial(tempCoef);
                    coeficients = temp.coef;
                    tempCoef = new float[tempCoef.length - 1];
                }
            }
            count = valX[0] + 1;
            if (temp.coef.length == 3) {
                float[] secondG = secondRoot(temp);
                for (int i = 0; i < secondG.length; i++) {
                    pResults.add(secondG[i]);
                }
            }
            if (pResults.size() == p.coef.length - 1) {
                float[] results = new float[pResults.size()];
                for (int i = 0; i < pResults.size(); ++i) {
                    results[i] = pResults.get(i);
                }
                Arrays.sort(results);
                return results;
            }
        }
        return null;
    }

    static int[] div(int d, int c) {
        int count = c;
        int[] divs = new int[2];
        while (count < d) {
            if (d % count == 0) {
                divs[0] = count;
                divs[1] = count * -1;
                return divs;
            } else count++;
        }
        return null;
    }
}