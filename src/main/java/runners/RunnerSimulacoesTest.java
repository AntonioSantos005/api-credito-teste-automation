package runners;

//Classe responsavel por gerenciar as execucoes (runner que executa todos os testes referentes a funcionalidade de simulacoes)

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
	plugin = {"pretty", "html:target/cucumber-report"},
	features="src/main/resources/features/",
	glue="stepsDefinitions",
	tags = {"@simulacoes"},
	snippets = SnippetType.CAMELCASE,
	dryRun = false,
	monochrome = true)

public class RunnerSimulacoesTest {

}
