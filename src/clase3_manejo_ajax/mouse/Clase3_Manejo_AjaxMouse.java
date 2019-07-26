/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clase3_manejo_ajax.mouse;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

/**
 *
 * @author liudmila
 * En esta clase vamos a estar viendo como utilizar distintas clases para manipular el cursor y el teclado.
 */
public class Clase3_Manejo_AjaxMouse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        //Posicionando el cursor sobre un elementos de la web sin dar clic.
        //Precondiciones
        System.setProperty("webdriver.gecko.driver", "./src/drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver(); 
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //Accediendo a la página
        //Vamos a utilizar este recurso donde vamos a poder experimentar con un objeto dinamico
        driver.get("https://www.amazon.com/");
         //Maximizando la pantalla
        driver.manage().window().maximize();        
        //Lo que vamos a hacer en este caso es manejar el objeto con la clase Actions
        //El objeto de esta clase se crea a partir del driver, 
       // por lo que obtiene sus atributos y funcionalidades,
       //Actions nos permite realizar una serie de eventos con el mraton sobre los objetos.
       //Inicializamos el objeto de la clase Actions
       Actions acciones = new Actions(driver);
       //Ahora contruimos toda la operacion
       //Primero debemos indicar la accion que queremos realizar con el raton
       //segundo le decimos hacia que objeto de la web vamos a estar haciendo esa operaci'on
       //Luego contruimos la accion con Build();
       //Luego llevamos a cabo la accion con Performance();
       acciones.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div[2]/div/a[2]/span[1]")))
               .build().perform();
       //ahora intentamos hacer click sobre un elemento de ls lista desplegable utilizando el FluentWait
       
        Wait<WebDriver> tiempo_espera = new FluentWait<WebDriver>(driver)
               .withTimeout(Duration.ofSeconds(10))//Definimos el tiempo total para lanzar el timeOut
               .pollingEvery(Duration.ofSeconds(2))//Definimos el espacio de tiempo para las consultas
               .ignoring(NoSuchElementException.class);//Especificamos que no haga caso a las excepciones mientras se ejecuta.
       WebElement objeto_dinamico = tiempo_espera.until(new Function<WebDriver, WebElement>() {
        public WebElement apply(WebDriver driver) {
            if (driver.findElement(By.xpath("//span[text()='Create a List']")).isDisplayed())
            {
                //aqui vemos como podemos utilizar dos tipos de conceptos:
                //el de manejo de tiempo y el de manejo de elementos por medio del maus y el teclado
                acciones.moveToElement(driver.findElement(By.xpath("//span[text()='Create a List']")))
               .click().build().perform();
                System.out.println(driver.findElement(By.xpath("//span[text()='Create a List']")).getText());
                return driver.findElement(By.xpath("//span[text()='Create a List']"));
            }
            else
            {
                return null;
            }}
        });
       //Como la ejecucion se va lanzando a mayor velocidad de la que el navegador puede ir respondiendo
       //hacemos una parada que nos permite recuperar la sincronizacion entre la ejecucion del codigo y las acciones de la web.
       Thread.sleep(5000);
       
       //Ahora vamos a escribir sobre un campo, utilizando mayusculas y dando doble click.
       //Luego se selecciona un elemento de la lista desplegada
       WebElement campo_escritura = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        acciones.moveToElement(campo_escritura).click()
               .keyDown(Keys.SHIFT)
               .sendKeys("hola")
               .doubleClick()
               .build().perform();
       //Para hacer click derecho sobre un objeto utilizamos contextClick();
        acciones.moveToElement(campo_escritura).contextClick().build().perform();
       
    }
    
}
