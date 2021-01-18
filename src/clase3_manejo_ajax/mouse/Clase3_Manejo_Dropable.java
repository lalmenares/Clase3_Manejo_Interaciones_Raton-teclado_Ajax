/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clase3_manejo_ajax.mouse;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.*;
/**
 *
 * @author liudmila
 * En esta clase vamos a estar viendo como utilizar distintas clases para manipular el cursor y el teclado.
 */
public class Clase3_Manejo_Dropable{

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) throws InterruptedException {

 //Precondicione		 System.setProperty("webdriver.chrome.driver", "./src/drivers/chromedriver.exe");
	     WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //Accediendo a la página
        //Vamos a utilizar este recurso donde vamos a poder utilizar una pagina adicional
        driver.get("https://jqueryui.com/droppable/");
         //Maximizando la pantalla
        driver.manage().window().maximize(); 
        //Ahora vamos a ver el titulo de la pagina a la que hace referencia el driver, asi sabremos a cual ventana estara apuntando.
        System.out.println(driver.getTitle());//como podemos ver sigue apuntando al padre
        //Localizamos el iframe que se encuentra alojado dentro de la ventana padre
        //System.out.println(driver.findElement(By.tagName("iframe")).getSize());
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[class='demo-frame']")));     
        //Ahora utilizando la clase Actions, manejamos el objeto que queremos mover
        Actions accion_raton = new Actions(driver);
        //Declaramos los dos elementos webs que van a estar involucrados en la accion
        WebElement obeto_fuente = driver.findElement(By.id("draggable"));
        WebElement objeto_destino = driver.findElement(By.id("droppable"));
        //Una vez que tenemos ambos objetos procedemos a realizar la accion
        //Les pasamos como parametro los elementos web que ya habiamos obtenido       
        accion_raton.dragAndDrop(obeto_fuente, objeto_destino).build().perform();
        System.out.println(objeto_destino.getText());
        //Post condicion, quitar el navegador.
        //Luego colvemos a la pagina padre para continuar haciendo acciones 
        driver.switchTo().defaultContent();//Con este metodo el driver vuelve a la posicion inicial
        //hacemos click en la funcion Aceptar del contenedor.
        driver.findElement(By.xpath("//a[text()='Accept']")).click();
               
    }
    
}