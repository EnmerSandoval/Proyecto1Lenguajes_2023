/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sintactico;

import com.mycompany.sintactico.lexico.ParserView;



/**
 *
 * @author enmer
 */
public class Sintactico {

    public static void main(String[] args) {
    //     String lexico= System.getProperty("user.dir")+"/src/main/java/AnalizadorLexico/Lexico.flex";
       
      // try {
      
        //jflex.Main.generate(new String[]{lexico});
        //} catch (SilentExit ex) {
         //   System.out.println("Error al compilar/generar el archivo flex: " + ex);
        //}
        
        ParserView analizador = new ParserView();
        analizador.setVisible(true);
   
    }
}
