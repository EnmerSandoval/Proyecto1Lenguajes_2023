/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sintactico.lexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;

/**
 *
 * @author enmer
 */
public class Parser {
        
   private static ArrayList<Token> ListaToken = new ArrayList<>();
   private static ArrayList<Token> ListaGeneral = new ArrayList<>();
   private static ArrayList<Token> ListaErrores = new ArrayList<>();
  
   
   private String contenido;
    
   public Parser(String Contenido){
       this.contenido = Contenido;
     //  Compilar();
   }
   /*
   
   private void Compilar(){
   
        File archivo = new File(System.getProperty("user.dir")+"/src/main/java/AnalizadorLexico/calc.py.txt");
        PrintWriter escribir;
        try {
            escribir = new PrintWriter(archivo);
            escribir.print(contenido);
            escribir.close();
        } catch (FileNotFoundException ex) {
        
        }
        
        try {
            Reader lector = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/main/java/AnalizadorLexico/calc.py.txt"));
            
            Lexico.Lexico lexer = new Lexico.Lexico(lector);
            while (true) {
                Token tokens = lexer.yylex();
                if (tokens == null) {
                    return;
                }
                if(tokens.getTipotoken()==TipoToken.Error){
                ListaErrores.add(tokens);
                ListaGeneral.add(tokens);
                }else{
                ListaGeneral.add(tokens);
                ListaToken.add(tokens);
                }
             
                
            }
        } catch (FileNotFoundException ex) {
        
        } catch (IOException ex) {
        
        }
   
   }
   
   */
   
   public ArrayList<Token> getTokens(){
   return ListaToken;
   }
   
   public ArrayList<Token> getTokensGeneral(){
   return ListaGeneral;
   }
   
   public ArrayList<Token> getErrores(){
   return ListaErrores;
   }

}
