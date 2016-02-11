package src;

public class Token {

    public int fila;
    public int columna;

    public String lexema;
    public String etiqueta;

    public int tipo; 	// tipo es: ID, ENTERO, REAL ...
    
    //public static final String[] etiquetas = 
   // {"(", ")", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 

    public static final int 
            ERROR           = -1,
            INIT            = 0,
            PARI            = 1,
            PARD            = 2,
            MULOP1          = 3,
            MULOP2          = 4,
            ADDOP           = 5,
            PYC             = 6,
            COMA            = 7,
            ASIG            = 8,
            LLAVEI          = 9,
            LLAVED          = 10,
            CORI            = 11,
            CORD            = 12,
            ENTERO1         = 13,
            ENTERO2         = 14,
            ID              = 15,
            REAL            = 16,
            DOUBLE          = 17,
            MAIN            = 18,
            INT             = 19,
            NUMBER          = 20, // Despues de recibir un numero
            IDAUX           = 21, // Despues de recibir una letra
            COMMENTorMULOP  = 22, // /
            OPENCOMMENT     = 23, // /*
            CLOSECOMMENT    = 24, // Despues de abrir comentario y encontrar asterisco (faltaria / para cerrar y volver a 0
            REALAUX1        = 25, // Despues de recibir un punto
            REALAUX2        = 26, // Despues de recibir punto + nro
            EOF             = 27;

    public Token() {
        lexema = new String();
        etiqueta = new String();
    }

    public String toString() {
        return etiqueta;
    }
}
