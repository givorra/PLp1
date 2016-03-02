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
    
    public void setTipo(int status)
    {
        switch(status)
        {            
            case PARI:
                tipo = 0;
                break;         
            case PARD:
                tipo = 1;
                break;         
            case MULOP1:case MULOP2:
                tipo = 2;
                break;         
            case ADDOP:
                tipo = 3;
                break;         
            case PYC:
                tipo = 4;
                break;         
            case COMA:
                tipo = 5;
                break;         
            case ASIG:
                tipo = 6;
                break;         
            case LLAVEI:
                tipo = 7;
                break;         
            case LLAVED:
                tipo = 8;
                break;        
            case CORI:
                tipo = 9;
                break;        
            case CORD:
                tipo = 10;
                break;    
            case DOUBLE:
                tipo = 11;
                break;    
            case INT:
                tipo = 12;
                break;      
            case MAIN:
                tipo = 13;
                break;       
            case ENTERO1:case ENTERO2:
                tipo = 14;
                break;        
            case ID:
                tipo = 15;
                break;       
            case REAL:
                tipo = 16;
                break;    
        }
    }
}
