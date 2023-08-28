/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package perfectshoes.appweb.utils;

/**
 *
 * @author Carmen Estela
 */
import jakarta.servlet.ServletException;
import java.io.IOException;

public interface IAuthorize {
    void authorize() throws ServletException, IOException;
    
}
