package runners;

//Classe responsavel por gerenciar as execucoes (runner executa todos os testes referente a funcionalidade reestricoes)

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
	plugin = {"pretty", "html:target/cucumber-report"},
	features="src/main/resources/features/",
	glue="stepsDefinitions",
	tags = {"@restricoes"},
	snippets = SnippetType.CAMELCASE,
	dryRun = false,
	monochrome = true)

public class RunnerRestricoesTest {

}
