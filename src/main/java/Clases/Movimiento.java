/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import javafx.scene.image.ImageView;

interface Movimiento {
    
    /**
     *Avanza a una velocidad d hacia adelante 
     */
    void avanzar();
    
   /**
    * Gira al robot dependiendo del valor de grados ingresados
    * Si el signo es positivo, el giro será a favor de las manecillas del reloj
    * si es negativo girará en sentido contrario
    * @param g 
    */
    void girar(double g);
        
    /**
     *Retorna un String de minerales que se encuentran en el crater sensado
     * y lo registra en el reporte
     */
    void sensar();

    /**
     * 
     * @param c1
     * @param c2
     * @return 
     */
    double distanciaR(Coordenadas c1, Coordenadas c2);

    /**
     * Retorna la imagen del rover
     * @return 
     */
    ImageView getRobot();
     
    /**
     *Recibe dos valores que representan las  
     * @param valorx
     * @param valory 
     */
    void dirigir(double valorx,double valory);
  
}
