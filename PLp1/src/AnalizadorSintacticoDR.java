/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author gacel
 */
public class AnalizadorSintacticoDR 
{
    public Token token;
    public AnalizadorLexico analizadorLexico;
    public StringBuilder reglas;
    public boolean imprimeReglas;
    
    AnalizadorSintacticoDR(AnalizadorLexico al)
    {
        analizadorLexico = al;
        token = al.siguienteToken();
        reglas = new StringBuilder();
        imprimeReglas = true;
    }
    
    public final void emparejar(int tokEsperado)
    {
        if (token.tipo == tokEsperado)
            token = analizadorLexico.siguienteToken();
        else
            errorSintaxis(tokEsperado);        
    }

    public void errorSintaxis(int... tokEsperado)
    {
        String s = new String();
        for(int t: tokEsperado)
        {
            s += " "+Token.getLabel(t);
        }
        if(token.tipo == Token.EOF)
            System.err.println("Error sintactico: encontrado fin de fichero, esperaba"+s);
        else
            System.err.println("Error sintactico ("+token.fila+","+token.columna+"): encontrado '"+token.lexema+"', esperaba"+s);
        System.exit(-1);
    }
    
    public void comprobarFinFichero()
    {
        if(token.tipo == Token.EOF && imprimeReglas)
            System.out.println(reglas);
    }
    
    
    public void addRegla(int regla)
    {
        String s = regla + " ";
        reglas.append(s);
    }
    
    public void S()
    {
        if(token.tipo == Token.INT || token.tipo == Token.DOUBLE || token.tipo == Token.MAIN)
        {
            addRegla(1);    // Numero de regla en los conjuntos de prediccion
            Sp();
            emparejar(Token.MAIN);
            emparejar(Token.PARI);
            emparejar(Token.PARD);
            Bloque();
        }
        else
            errorSintaxis(Token.DOUBLE, Token.INT, Token.MAIN);
    }
    public void Sp()
    {
        if(token.tipo == Token.INT || token.tipo == Token.DOUBLE)
        {
            addRegla(2);
            Funcion();
            Sp();
        }
        else if(token.tipo == Token.MAIN)
        {
            addRegla(3);
        }
        else
            errorSintaxis(Token.DOUBLE, Token.INT, Token.MAIN);
    }
    public void Funcion()
    {
        if(token.tipo == Token.INT || token.tipo == Token.DOUBLE)
        {
            addRegla(4);
            Tipo();
            emparejar(Token.ID);
            emparejar(Token.PARI);
            emparejar(Token.PARD);
            Bloque();
        }
        else
            errorSintaxis(Token.DOUBLE, Token.INT);
    }
    public void Tipo()
    {
        if(token.tipo == Token.INT)
        {
            addRegla(5);
            emparejar(Token.INT);
        }
        else if(token.tipo == Token.DOUBLE)
        {
            addRegla(6);
            emparejar(Token.DOUBLE);
        }
        else
            errorSintaxis(Token.DOUBLE, Token.INT);
    }
    public void Bloque()
    {
        if(token.tipo == Token.LLAVEI)
        {
            addRegla(7);
            emparejar(Token.LLAVEI);
            SecInstr();
            emparejar(Token.LLAVED);
        }
        else
            errorSintaxis(Token.LLAVEI);
    }
    public void SecInstr()
    {
        if(token.tipo == Token.INT || token.tipo == Token.DOUBLE)
        {
            addRegla(8);
            V();
            SecInstr();
            
        }
        else if(token.tipo == Token.LLAVEI || token.tipo == Token.ID)
        {
            addRegla(9);
            Instr();
            SecInstr();            
        }
        else if(token.tipo == Token.LLAVED)
        {
            addRegla(10);            
        }
        else
            errorSintaxis(Token.LLAVEI, Token.LLAVED, Token.DOUBLE, Token.INT, Token.ID);
    }
    public void V()
    {
        if(token.tipo == Token.INT || token.tipo == Token.DOUBLE)
        {
            addRegla(11);
            Tipo();
            Var();
            MV();
        }
        else
            errorSintaxis(Token.DOUBLE, Token.INT);
        
    }
    public void Var()
    {
        if(token.tipo == Token.ID)
        {
            addRegla(12);
            emparejar(Token.ID);
            Array();
        }
        else
            errorSintaxis(Token.ID);
    }
    public void Array()
    {
        if(token.tipo == Token.CORI)
        {
            addRegla(13);
            emparejar(Token.CORI);
            emparejar(Token.ENTERO);
            emparejar(Token.CORD);
            Array();
        }
        else if(token.tipo == Token.COMA || token.tipo == Token.PYC)
        {
            addRegla(14);
        }
        else
            errorSintaxis(Token.PYC, Token.COMA, Token.CORI);
    }
    public void MV()
    {
        if(token.tipo == Token.COMA)
        {
            addRegla(15);
            emparejar(Token.COMA);
            Var();
            MV();
        }
        else if(token.tipo == Token.PYC)
        {
            addRegla(16);
            emparejar(Token.PYC);
        }
        else
            errorSintaxis(Token.PYC, Token.COMA, Token.CORI);
    }
    public void Instr()
    {
        if(token.tipo == Token.ID)
        {
            addRegla(17);
            emparejar(Token.ID);
            emparejar(Token.ASIG);
            Expr();
            emparejar(Token.PYC);
        }
        else if(token.tipo == Token.LLAVEI)
        {
            addRegla(18);
            Bloque();
        }
        else
            errorSintaxis(Token.LLAVEI, Token.ID);
    }
    public void Expr()
    {
        if(token.tipo == Token.ID || token.tipo == Token.ENTERO || token.tipo == Token.REAL)
        {
            addRegla(19);
            Term();
            ExprAux();
        }
        else
            errorSintaxis(Token.ENTERO, Token.ID, Token.REAL);
    }
    public void ExprAux()
    {
        if(token.tipo == Token.ADDOP)
        {
            addRegla(20);
            emparejar(Token.ADDOP);
            Term();
            ExprAux();
            
        }
        else if(token.tipo == Token.PYC)
        {
            addRegla(21);            
        }
        else
            errorSintaxis(Token.ADDOP, Token.PYC);
    }
    public void Term()
    {
        if(token.tipo == Token.ID || token.tipo == Token.ENTERO || token.tipo == Token.REAL)
        {
            addRegla(22);
            Factor();
            TermAux();
        }
        else
            errorSintaxis(Token.ENTERO, Token.ID, Token.REAL);
    }
    public void TermAux()
    {
        if(token.tipo == Token.MULOP)
        {
            addRegla(23);
            emparejar(Token.MULOP);
            Factor();
            TermAux();
        }
        else if(token.tipo == Token.ADDOP || token.tipo == Token.PYC)
        {
            addRegla(24);            
        }
        else
            errorSintaxis(Token.MULOP, Token.ADDOP, Token.PYC);
    }
    public void Factor()
    {
        if(token.tipo == Token.REAL)
        {
            addRegla(25);
            emparejar(Token.REAL);
        }
        else if(token.tipo == Token.ENTERO)
        {
            addRegla(26);
            emparejar(Token.ENTERO);
        }
        else if(token.tipo == Token.ID)
        {
            addRegla(27);
            emparejar(Token.ID);
        }
        else
            errorSintaxis(Token.ENTERO, Token.ID, Token.REAL);
    }
}
