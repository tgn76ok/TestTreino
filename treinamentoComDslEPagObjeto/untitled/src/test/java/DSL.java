import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

    private WebDriver driver;

    public DSL(WebDriver driver) {

        this.driver = driver;

    }
    //campos de testo
    public void  escreve(String id_campo,String id_valor){
        driver.findElement(By.id(id_campo)).sendKeys(id_valor);
    }

    public String obterValorCampo(String id_campo){
        return driver.findElement(By.id(id_campo)).getAttribute("value");
    }


    public void clickRadio(String id_campo){
        driver.findElement(By.id(id_campo)).click();

    }
    public void clickCheckBox(String id_campo){
        driver.findElement(By.id(id_campo)).click();

    }

    public void selecionarComboVidivel(String id_campo,String valor){
        WebElement element = driver.findElement(By.id(id_campo));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);

    }
    public String obterValorCambo(String id_campo){
        WebElement element = driver.findElement(By.id(id_campo));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();

    }

    public void clicarBotao(String id_campo){
        driver.findElement(By.id(id_campo)).click();

    }
}
