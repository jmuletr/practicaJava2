import java.util.Arrays;

public class Polynomial {
    private float[] coef = {0f};
    private String coefStr;
    // Constructor per defecte. Genera un polinomi zero
    public Polynomial() {
        this.coefStr = "0";
    }

    // Constructor a partir dels coeficients del polinomi en forma d'array
    public Polynomial(float[] cfs) {
        this.coefStr = Utils.fToString(cfs);
        this.coef = cfs;
    }

    // Constructor a partir d'un string
    public Polynomial(String s) {
        String[][] cfs = Utils.identifyFact(s);
        this.coef = Utils.sToFloat(cfs);
        this.coefStr = Utils.fToString(this.coef);
    }

    // Suma el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    public Polynomial add(Polynomial p) {
        if (this.coefStr == "0"){
            Polynomial polyAdd = new Polynomial(p.coefStr);
            return polyAdd;
        }
        String p1 = this.coefStr;
        String p2 = p.coefStr;
        if (this.coef.length < p.coef.length){
            p1 = p.coefStr;
            p2 = this.coefStr;
        }
        if (!(p2.matches("\\s[-]\\s\\d*[x].*"))){
            if(p2.charAt(0) == '-'){
                String s2 = " - " + p2.substring(1);
                p2 = s2;
            }
            else{
                String s2 = " + " + p2;
                p2 = s2;
            }
        }
        String s2 = p1 +""+ p2;
        Polynomial polyAdd = new Polynomial(s2);
        return polyAdd;
    }

    // Multiplica el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    public Polynomial mult(Polynomial p2) {
        float[] pol1 = this.coef;
        float[] pol2 = p2.coef;
        int maxExp = pol1.length + pol2.length -1;
        float[] operations = new float[maxExp];
        Polynomial answer = new Polynomial();
        if (this.coef.length < p2.coef.length){
            pol1 = p2.coef;
            pol2 = this.coef;
        }
        Polynomial p;
        for(int x = 0; x < pol1.length; x++) {
            if(pol1[x] == 0){
                continue;
            }
            for (int y = 0; y < pol2.length; y++) {
                if(pol2[y] == 0){
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
       return null;
    }

    // Troba les arrels del polinomi, ordenades de menor a major
    public float[] roots() {
        return null;
    }

    // Torna "true" si els polinomis són iguals. Això és un override d'un mètode de la classe Object
    @Override
    public boolean equals(Object o) {
        if (o instanceof Polynomial){
            Polynomial p2 = (Polynomial)o;
            if (p2.toString().equals(o.toString())){
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
