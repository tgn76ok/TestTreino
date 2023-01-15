import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	
	private WebDriver driver;
	private DSL dsl;
	@Before
	public void inicializa(){
		driver = new FirefoxDriver();
		String caminho = System.getProperty("user.dir");
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + caminho + "/src/test/Resouces/html/componentes.html");
		System.setProperty("webdriver.gecko.drive",caminho+"\\src\\test\\Resouces\\geckodriver-v0.32.0-win-aarch64\\geckodriver.exe");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}
	
	@Test
	public void testeTextField(){
		dsl.escreve("elementosForm:nome","Teste de escrita");
		Assert.assertEquals("Teste de escrita",dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveIntegarirComTextArea(){
		dsl.escreve("elementosForm:sugestoes","teste\n\naasldjdlks\nUltima linha");
		Assert.assertEquals("teste\n\naasldjdlks\nUltima linha", dsl.obterValorCampo("\"elementosForm:sugestoes\""));
	}
	
	@Test
	public void deveIntegarirComRadioButton(){
		dsl.clickRadio("elementosForm:sexo:0");
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}
	
	@Test
	public void deveIntegarirComCheckbox(){
		dsl.clickCheckBox("elementosForm:comidaFavorita:2");
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
	}
	
	@Test
	public void deveIntegarirComCombo(){
//		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
//		Select combo = new Select(element);
//		combo.selectByIndex(2);
//		combo.selectByValue("superior");
//		combo.selectByVisibleText("2o grau completo");
		dsl.selecionarComboVidivel("elementosForm:escolaridade","2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCambo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo(){
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")){
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo(){
		dsl.selecionarComboVidivel("elementosForm:esportes","Natacao");
		dsl.selecionarComboVidivel("elementosForm:esportes","Corrida");
		dsl.selecionarComboVidivel("elementosForm:esportes","O que eh esporte?");
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);

		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	}
	
	@Test
	public void deveinteragirComBotoes(){
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
	}
	
	@Test
	public void deveinteragirComLinks(){
		driver.findElement(By.linkText("Voltar")).click();
		
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
	}
	
	@Test
	public void deveBuscarTextosNaPagina(){
//		Assert.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", 
				driver.findElement(By.tagName("h3")).getText());
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", 
				driver.findElement(By.className("facilAchar")).getText());
	}
	
}


