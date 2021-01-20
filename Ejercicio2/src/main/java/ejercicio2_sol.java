import io.github.bonigarcia.wdm.WebDriverManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ejercicio2_sol {

    public static void main(String[] args)
    {

        String url = "https://www.saucedemo.com/";

        String imgUrl = "/html/body/div[2]/div[1]/img";

        // Id de controles de login
        String idUser = "user-name";
        String idPassword = "password";
        String idLogin = "login-button";

        // WebElements
        WebElement inputUser = null;
        WebElement inputPass = null;
        WebElement btnLogin = null;

        WebDriver driver = null;
        WebDriverManager.chromedriver().version("87.0.4280.88").setup();
        driver = new ChromeDriver();

        //Abrimos el browser
        driver.get(url);

        // Aplicando un explicit
        WebDriverWait wait = new WebDriverWait(driver, 10L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(imgUrl)));

        // Autenticacion en login
         inputUser =  driver.findElement(By.id(idUser));
         inputUser.sendKeys(new CharSequence[] {"standard_user"});
         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

         inputPass = driver.findElement(By.id(idPassword));
         inputPass.sendKeys(new CharSequence[] {"secret_sauce"});
         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

         btnLogin = driver.findElement(By.id(idLogin));
         btnLogin.sendKeys(new CharSequence[] {Keys.ENTER});

        // Aplicando implicit
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Agregando al carrito todos los items y productos en arreglo dinamico
        ArrayList<String> productos = new ArrayList<String>();
        int a = 0;

        for (int i = 1; i<=6; ++i)
        {

            String xpProducto = "//*[@id='item_" + a + "_title_link']/div";
            ++a;
            productos.add(driver.findElement(By.xpath(xpProducto)).getText());

            String items = "//*[@id='inventory_container']/div/div[" + i + "]/div[3]/button";
            WebElement btnSel = driver.findElement(By.xpath(items));
            btnSel.click();
        }

        // Implicit de 10 segundos
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        // Listar en orden ascendente los nombres
        Collections.sort(productos);
        System.out.println("Orden Ascendente");
        System.out.println("----------------");
        for (String items: productos)
            System.out.println(items);

        System.out.println("");

        // Listar en orden descendente los nombres
        System.out.println("Orden Descendente");
        System.out.println("-----------------");
        Collections.sort(productos, Collections.reverseOrder());
        for (String items: productos)
            System.out.println(items);

        // Ir al carrito
        driver.findElement(By.id("shopping_cart_container")).click();

        // Implicit de 10 segundos
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        // remover los 3 primero
        for (int i = 3; i<=5; ++i)
        {
            driver.findElement(By.xpath("//*[@id=\'cart_contents_container\']/div/div[1]/div[" + i + "]/div[2]/div[2]/button")).click();
        }

        // Ir al checkout
        driver.findElement(By.xpath("//*[@id='cart_contents_container']/div/div[2]/a[2]")).click();

        // Implicit de 10 segundos
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        // Llenar los datos del formulario
        String idfirst = "first-name";
        String idlast = "last-name";
        String idpostal = "postal-code";

        driver.findElement(By.id(idfirst)).sendKeys("Humberto");
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        driver.findElement(By.id(idlast)).sendKeys("Guadalupe");
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        driver.findElement(By.id(idpostal)).sendKeys("132345");
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        String btnContinuar = "//*[@id=\'checkout_info_container\']/div/form/div[2]/input";
        driver.findElement(By.xpath(btnContinuar)).click();

        // Verificar el total de la suma de los subtotales
        String subtotal = "//*[@id='checkout_summary_container']/div/div[2]/div[5]";
        String impuesto = "//*[@id='checkout_summary_container']/div/div[2]/div[6]";
        String total = "//*[@id='checkout_summary_container']/div/div[2]/div[7]";

        WebElement precioSubTotal = driver.findElement(By.xpath(subtotal));
        WebElement precioImpuesto = driver.findElement(By.xpath(impuesto));
        WebElement precioTotal = driver.findElement(By.xpath(total));

        System.out.println("");

        float st = Float.parseFloat(precioSubTotal.getText().substring(13)) ;
        float im = Float.parseFloat(precioImpuesto.getText().substring(6)) ;
        float tt = Float.parseFloat(precioTotal.getText().substring(8)) ;

        //System.out.println(st);
        //System.out.println(im);
        //System.out.println(tt);

        float tt1 = st + im;
        if (tt1 == tt)
        {System.out.println("Las suma del subtotal e impuesto es igual al total: " + tt );}
        else
        {System.out.println("Las suma del subtotal e impuesto No es igual al total: " + tt );}

        String btnFinal = "//*[@id='checkout_summary_container']/div/div[2]/div[8]/a[2]";
        driver.findElement(By.xpath(btnFinal)).click();

        System.out.println("");

        //THANK YOU FOR YOUR ORDER
        String resultEsperado = "THANK YOU FOR YOUR ORDER";
        String xpObtenido = "//*[@id=\"checkout_complete_container\"]/h2";

        WebElement resul = driver.findElement(By.xpath(xpObtenido));
        System.out.println(resul.getText());
        //System.out.println(resultEsperado);

        if (resul.getText().equals(resultEsperado) ) {
            System.out.println("El mensaje obtenido es igual al esperado.");
        } else {
            System.out.println("Finalizo con error");
        }

        System.out.println("");
        System.out.println("termino el test");

        driver.close();

    }

}



