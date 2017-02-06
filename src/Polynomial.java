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
        return null;
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
