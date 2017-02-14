import java.util.Arrays;

public class Polynomial {
    protected float[] coef = {0f};
    private String coefStr;

    // Constructor per defecte. Genera un polinomi zero
    public Polynomial() {
        //tornam string 0
        this.coefStr = "0";
    }

    // Constructor a partir dels coeficients del polinomi en forma d'array
    public Polynomial(float[] cfs) {
        //Cridam les funcions fToString per transformar els float a string
        this.coefStr = Utils.fToString(cfs);
        this.coef = cfs;
    }

    // Constructor a partir d'un string
    public Polynomial(String s) {
        //identificam cada monomi del string
        String[][] cfs = Utils.identifyFact(s);
        //transformam els monomis en un array de float
        this.coef = Utils.sToFloat(cfs);
        //transformam els float a string
        this.coefStr = Utils.fToString(this.coef);
    }

    // Suma el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    public Polynomial add(Polynomial p) {
        //si el primer polinomi es 0 no fa falta sumar res i es retorna p
        if (this.coefStr == "0") {
            Polynomial polyAdd = new Polynomial(p.coefStr);
            return polyAdd;
        }

        //es posa el polinomi mes gran a la variable p1 i el mes petit a p2
        String p1 = this.coefStr;
        String p2 = p.coefStr;
        if (this.coef.length < p.coef.length) {
            p1 = p.coefStr;
            p2 = this.coefStr;
        }

        //es comproba si p2 comensa per mes o menys
        if (!(p2.matches("\\s[-]\\s\\d*[x].*"))) {
            if (p2.charAt(0) == '-') {
                String s2 = " - " + p2.substring(1);
                p2 = s2;
            } else {
                String s2 = " + " + p2;
                p2 = s2;
            }
        }

        //es junten els string dels dos polinomis i es crea un de nou amb la suma dels dos
        String s2 = p1 + "" + p2;
        Polynomial polyAdd = new Polynomial(s2);
        return polyAdd;
    }

    // Multiplica el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    public Polynomial mult(Polynomial p2) {
        //variables de la funcio i ordenacio de pol1 i pol2 depenguent de la longitud del array
        float[] pol1 = this.coef;
        float[] pol2 = p2.coef;
        int maxExp = pol1.length + pol2.length - 1;
        float[] operations = new float[maxExp];
        Polynomial p;
        Polynomial answer = new Polynomial();
        if (this.coef.length < p2.coef.length) {
            pol1 = p2.coef;
            pol2 = this.coef;
        }

        //bucles per recorre totes les posicions dels dos polinomis
        for (int x = 0; x < pol1.length; x++) {
            if (pol1[x] == 0) {
                continue;
            }
            for (int y = 0; y < pol2.length; y++) {
                if (pol2[y] == 0) {
                    continue;
                }
                /*
                pol2=		     x^4 - 6x^2 + 8 *
                pol1=				         12
                ____________________________________
                             12x^4 - 72x^2 + 96


                operation.lenght = longitud del array de pol1 mes la de pol2 incluits valors 0 menys 1.
                ex:per treure 8*12:
                operation.lenght(es 5) - (((pol1.lenght(es 1)- la x del bucle que valdria 0)) +
                ((pol2.lenght(es 3)- la y del bucle que valdria 0) - 1 )) = 5 -((1-0) + (3-0)-1)=4-(4-1)=5-3=2
                aixi que pol1[0] que es 12 * pol2[0] que es 8 = 96 aixi que operations posicio 2 val 96
                 */
                //en cas de multiplicar dos exponents es multiplica el valor dels coeficients i es sumen els valors dels exponents.
                operations[operations.length - (((pol1.length - x)) + ((pol2.length - y) - 1))] = pol1[x] * pol2[y];
            }
            p = new Polynomial(operations);
            answer = answer.add(p);
            Arrays.fill(operations, 0);
        }
        return answer;
    }


    // Divideix el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    // Torna el quocient i també el residu (ambdós polinomis)
    public Polynomial[] div(Polynomial p2) {
        Polynomial[] result = new Polynomial[2];
        int position = 0;
        int count = 0;
        float[][] operations = new float[2][this.coef.length];
        result[1] = new Polynomial(this.coef);
        if (this.coef.length < p2.coef.length) {
            return null;
        }
        while (count < this.coef.length - p2.coef.length + 1) {
            if (result[1].coef[position] == 0) {
                position++;
            }
            operations[0][position + p2.coef.length - 1] = result[1].coef[position] / p2.coef[0];
            for (int i = 0; i < p2.coef.length; i++) {
                operations[1][count + i] = operations[0][position + (this.coef.length -
                        (this.coef.length - (p2.coef.length - 1)))] * p2.coef[i] * -1;
            }
            result[1] = result[1].add(new Polynomial(operations[1]));
            operations[1][count] = 0;
            count++;
        }
        result[0] = new Polynomial(operations[0]);
        return result;
    }

    // Troba les arrels del polinomi, ordenades de menor a major
    public float[] roots() {
        int numExp = Utils.monTotal(this.coefStr);
        float[] results = new float[this.coef.length - 1];
        Arrays.fill(results, 0);
        // polinomis simples
        if (numExp == 2 && this.coef[this.coef.length - 1] != 0) {
            results = Utils.simpleRoot(this);
            return results;
        }
        //polinomis 2n grau
        if (this.coef.length == 3) {
            results = Utils.secondRoot(this);
            return results;
        }
        //bicuadrades
        if (this.coef.length == 5 && this.coef[1] == 0) {
            results = Utils.bicuadRoot(this);
            return results;
        } else {
            results = Utils.ruffini(this);
            return results;
        }
    }

    // Torna "true" si els polinomis són iguals. Això és un override d'un mètode de la classe Object
    @Override
    public boolean equals(Object o) {
        if (o instanceof Polynomial) {
            Polynomial p2 = (Polynomial) o;
            if (p2.toString().equals(o.toString())) {
                return true;
            }
        }
        return false;
    }

    // Torna la representació en forma de String del polinomi. Override d'un mètode de la classe Object
    @Override
    public String toString() {
        return this.coefStr;
    }
}