

public class Polynomial {
    private float[] coef;
    private String coefs;
    // Constructor per defecte. Genera un polinomi zero
    public Polynomial() {
        this.coefs = "0";
    }

    // Constructor a partir dels coeficients del polinomi en forma d'array
    public Polynomial(float[] cfs) {
        int exp = cfs.length - 1;
        int fact;

        for (int i = 0; i < cfs.length; i++) {
            fact = (int) cfs[i];
            if (fact == 0 && exp >= 0) {
                exp--;

                if (i == cfs.length - 1) {
                    this.coefs = "0";
                }
                continue;
            } else {
                //nombres positius
                if (fact >= 0) {
                    //primer valor sense signe
                    if (i == 0) {
                        if (exp > 0) {
                            if (fact == 1) {
                                this.coefs = "x";
                                if (exp > 1) {
                                    this.coefs += "^" + exp;
                                }
                            } else if (fact > 1) {
                                this.coefs = Integer.toString(fact);
                                this.coefs += "x";
                                if (exp > 1) {
                                    this.coefs += "^" + exp;
                                }
                            }
                            //exponent 0
                        } else if (fact == 0) {
                            this.coefs = "0";
                        } else if (fact > 0) {
                            this.coefs += " + " + fact;
                        }

                        //valors amb signe
                    } else if (i > 0) {
                        this.coefs += " + ";
                        if (exp == 0) {
                            if (fact == 0) {
                                this.coefs += "0";
                            } else if (fact > 0) {
                                this.coefs += fact;
                            }
                        } else if (exp > 0) {
                            if (fact == 1) {
                                this.coefs += "x";
                                if (exp > 1) {
                                    this.coefs += "^" + exp;
                                }
                            } else if (fact > 1) {
                                this.coefs += Integer.toString(fact);
                                this.coefs += "x";
                                if (exp > 1) {
                                    this.coefs += "^" + exp;
                                }

                            }
                        } else this.coefs += fact;
                        //nombres negatius
                    }
                } else if (fact < 0) {
                    if (i == 0) {
                        this.coefs = "-";
                        fact = fact * -1;
                        if (exp > 0) {
                            if (fact == 1) {
                                this.coefs += "x";
                                if (exp > 1) {
                                    this.coefs += "^" + exp;
                                }
                            } else if (fact > 1) {
                                this.coefs += Integer.toString(fact);
                                this.coefs += "x";
                                if (exp > 1) {
                                    this.coefs += "^" + exp;
                                }
                            }
                        }
                        //valors amb signe
                    } else if (i > 0) {
                        this.coefs += " - ";
                        if (exp == 0) {
                            if (fact == 0) {
                                this.coefs += "0";
                            } else if (fact < 0) {
                                fact = fact * -1;
                                this.coefs += fact;
                            }
                        } else if (exp > 0) {
                            if (exp > 0) {
                                fact = fact * -1;
                                if (fact == 1) {
                                    this.coefs += "x";
                                    if (exp > 1) {
                                        this.coefs += "^" + exp;
                                    }
                                } else if (fact > 1) {
                                    this.coefs += Integer.toString(fact);
                                    this.coefs += "x";
                                    if (exp > 1) {
                                        this.coefs += "^" + exp;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            exp--;
        }

    }

    // Constructor a partir d'un string
    public Polynomial(String s) {
        String[] monomios = s.split(" [+\\-]");
        System.out.println(monomios.toString());
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
