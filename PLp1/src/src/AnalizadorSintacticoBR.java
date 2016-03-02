/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author gacel
 */
public class AnalizadorSintacticoBR 
{
    public Token token;
    public AnalizadorLexico lexico;
    
    public final void emparejar(int tokEsperado)
    {
        if (token.tipo == tokEsperado)
            token = lexico.siguienteToken();
        else
            errorSintaxis(tokEsperado);
        
    }

    public void errorSintaxis(int tokEsperado)
    {

    }
    public void S()
    {
        
    }
    public void Sp()
    {
        
    }
    public void Funcion()
    {
        
    }
    public void Tipo()
    {
        
    }
    public void Bloque()
    {
        
    }
    public void SecInstr()
    {
        
    }
    public void V()
    {
        
    }
    public void Var()
    {
        
    }
    public void Array()
    {
        
    }
    public void MV()
    {
        
    }
    public void Instr()
    {
        
    }
    public void Expr()
    {
        
    }
    public void ExprAux()
    {
        
    }
    public void Term()
    {
        
    }
    public void TermAux()
    {
        
    }
    public void Factor()
    {
        
    }
}
