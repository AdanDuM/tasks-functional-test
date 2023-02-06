package br.adan.tasks.functional;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

  private WebDriver driver;

  @Before
  public void setup() {
    driver = acessarAplicacao();
  }

  @After
  public void teardown() {
    driver.quit();
  }

  @Test
  public void deveSalvarTarefaComSucesso() throws Exception {
    // Clicar em add todo
    driver.findElement(By.id("addTodo")).click();

    // Preencher Descricao
    driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

    // Preencher Data
    driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

    // Clicar em salvar
    driver.findElement(By.id("saveButton")).click();

    // Validar mensagem de sucesso
    final String mensagem = driver.findElement(By.id("message")).getText();

    assertEquals("Success!", mensagem);
  }

  @Test
  public void naoDeveSalvarTarefaComDataPassada() throws Exception {
    // Clicar em add todo
    driver.findElement(By.id("addTodo")).click();

    // Preencher Descricao
    driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

    // Preencher Data
    driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

    // Clicar em salvar
    driver.findElement(By.id("saveButton")).click();

    // Validar mensagem de sucesso
    final String mensagem = driver.findElement(By.id("message")).getText();

    assertEquals("Due date must not be in past", mensagem);
  }

  @Test
  public void naoDeveSalvarTarefaSemData() throws Exception {
    // Clicar em add todo
    driver.findElement(By.id("addTodo")).click();

    // Preencher Descricao
    driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

    // Clicar em salvar
    driver.findElement(By.id("saveButton")).click();

    // Validar mensagem de sucesso
    final String mensagem = driver.findElement(By.id("message")).getText();

    assertEquals("Fill the due date", mensagem);
  }

  @Test
  public void naoDeveSalvarTarefaSemDescricao() throws Exception {
    // Clicar em add todo
    driver.findElement(By.id("addTodo")).click();

    // Preencher Data
    driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

    // Clicar em salvar
    driver.findElement(By.id("saveButton")).click();

    // Validar mensagem de sucesso
    final String mensagem = driver.findElement(By.id("message")).getText();

    assertEquals("Fill the task description", mensagem);
  }

  public WebDriver acessarAplicacao() {
    final WebDriver driver = new ChromeDriver();
    driver.navigate().to("http://localhost:8001/tasks");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    return driver;
  }

}
