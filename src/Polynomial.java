import java.util.Arrays;


public class Polynomial {
    private float[] coef = {0f};
    private String coefs;
    // Constructor per defecte. Genera un polinomi zero
    public Polynomial() {}

    // Constructor a partir dels coeficients del polinomi en forma d'array
    public Polynomial(float[] cfs) {
        this.coefs = Utils.fToString(cfs);
        this.coef = cfs;
    }

    // Constructor a partir d'un string
    public Polynomial(String s) {
        String[][] cfs = Utils.identifyFact(s);
        this.coef = Utils.sToFloat(cfs);
        this.coefs = Utils.fToString(this.coef);
    }

    // Suma el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    public Polynomial add(Polynomial p) {
        if(p.coefs.charAt(0) == '-'){
            String s2 = " - " + p.coefs.substring(1);
            p.coefs = s2;
        }
        else{
            String s2 = " + " + p.coefs;
            p.coefs = s2;
        }
        Polynomial polyAdd = new Polynomial(this.coefs +""+ p.coefs);
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
        return this.coefs;
    }
}
