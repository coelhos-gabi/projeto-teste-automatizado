package br.com.letscode.projetotesteautomatizado.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeTest {
    private WebDriver webDriver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver","chromedriver");
    }

    @Test
    void listFilmes(){
        webDriver = new ChromeDriver();

        webDriver.get("http://localhost:8080/home");
        assertEquals("Filmes", webDriver.getTitle());

        WebElement nome = webDriver.findElement(By.name("nome"));
        String valorNome = nome.getText();
        WebElement genero = webDriver.findElement(By.name("genero"));
        String valorGenero = nome.getText();
        WebElement ano = webDriver.findElement(By.name("ano"));
        String valorAno = nome.getText();
        WebElement atores = webDriver.findElement(By.name("atores"));
        String valorAtores = nome.getText();

        assertEquals("Bacurau", valorNome);
        assertEquals("Drama", valorGenero);
        assertEquals("2019",valorAno);
        assertEquals("Sonia Braga", valorAtores);

        webDriver.quit();
    }

    @Test
    void cadastrarFilme(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/home");

        WebElement linkCadastrarAtor = webDriver.findElement(By.id("linkAtor"));
        linkCadastrarAtor.click();
        WebElement nomeAtor = webDriver.findElement(By.name("nome"));
        nomeAtor.sendKeys("Alice Braga");
        WebElement botaoCadastro = webDriver.findElement(By.id("submitButton"));
        botaoCadastro.click();

        WebElement linkCadastrarAtor2 = webDriver.findElement(By.id("linkAtor"));
        linkCadastrarAtor2.click();
        WebElement nomeAtor2 = webDriver.findElement(By.name("nome"));
        nomeAtor2.sendKeys("Alice Braga");
        WebElement botaoCadastro2 = webDriver.findElement(By.id("submitButton"));
        botaoCadastro2.click();


        WebElement linkCadastrarFilme = webDriver.findElement(By.id("linkFilme"));
        linkCadastrarFilme.click();

        WebElement nome = webDriver.findElement(By.name("nome"));
        nome.sendKeys("Cidade de Deus");
        WebElement genero = webDriver.findElement(By.name("genero"));
        genero.sendKeys("Drama");
        WebElement ano = webDriver.findElement(By.name("ano"));
        ano.sendKeys("2002");
        Select dropAtor = new Select(webDriver.findElement(By.name("optionAtores")));
        dropAtor.selectByVisibleText("Alice Braga");
        Select dropAtor2 = new Select(webDriver.findElement(By.name("optionAtores")));
        dropAtor2.selectByVisibleText("Douglas Silva");

        WebElement botaoCadastro3 = webDriver.findElement(By.id("submitButton"));
        botaoCadastro3.click();

        webDriver.quit();
    }

    @Test
    void cadastrarAtor(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/create-ator");

        WebElement nome = webDriver.findElement(By.name("nome"));
        nome.sendKeys("Lazaro Ramos");

        WebElement submit = webDriver.findElement(By.id("submitButton"));
        submit.click();

        webDriver.quit();
    }
}
