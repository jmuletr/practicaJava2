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
}
