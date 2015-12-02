/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;

/**
 *
 * @author BigBoss
 */
public class LogErros {
    
    
    public static File criarLogErros(){
        File logErros= new File("LogErros.txt");
        return logErros;
    }
    
    /**
     *
     * @param erro
     * @param escreverlog
     * @throws java.io.FileNotFoundException
     */
    public static void guardarFicheiroErros(String erro, Formatter escreverlog) throws FileNotFoundException{
        escreverlog.format("%n%s", erro);
    }
    
}
