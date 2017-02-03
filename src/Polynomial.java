import java.util.Arrays;


public class Polynomial {
    private float[] coef;
    private String coefs;
    // Constructor per defecte. Genera un polinomi zero
    public Polynomial() {
        this.coefs = "0";
    }

    // Constructor a partir dels coeficients del polinomi en forma d'array
    public Polynomial(float[] cfs) {
        this.coefs = Util.fToString(cfs);
    }

    // Constructor a partir d'un string
    public Polynomial(String s) {
        s = s.replace("- ", "-");
        s = s.replace("+ ", "+");
        String[] monomial = s.split(" ");
        System.out.println(Arrays.deepToString(monomial));
        String[][] coefExp = new String[2][monomial.length];
        int coef;
        for (int i = 0; i < monomial.length; i++) {
            if (monomial[i].contains("x")){
                if (monomial[i].matches(".*\\d[x].*")){
                    coef = Integer.parseInt(monomial[i].substring(0,monomial[i].indexOf('x')));
                    coefExp[0][i] = Integer.toString(coef);
                } else {coefExp[0][i] = "1";}

            }else if (!monomial[i].contains("x")){
                coef = Integer.parseInt(monomial[i]);
                coefExp[0][i] = Integer.toString(coef);
                coefExp[1][i] = "0";
            }
        }
    }

    // Suma el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    public Polynomial add(Polynomial p) {
        return null;
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
