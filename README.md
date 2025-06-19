
# API simulação de credito - Testes Automatizados

Este projeto realiza a automação de testes de API simulando funcionalidades de uma API de crédito. Os testes foram implementados com Java, Cucumber, JUnit e Maven.

## 📌 Objetivo

Validar o comportamento de endpoints REST para:

- Consultar e manipular **simulações de crédito**
- Consultar **restrições de CPF**

## 🔧 Tecnologias Utilizadas

- Java 11+
- Cucumber
- JUnit
- Maven
- Rest Assured

## 📁 Estrutura do Projeto

```
src
├── main
│   ├── java
│   │   ├── model              # Modelos para Simulação e Restrição
│   │   ├── runners            # Runners do Cucumber
│   │   ├── singleton          # Controle de instâncias compartilhadas
│   │   ├── stepsDefinitions   # Definições de passos Gherkin
│   │   ├── testAPI            # Testes organizados por contexto (simulação, restrição)
│   │   └── utils              # Classe auxiliar com funções genéricas
│   └── resources
│       ├── Dados              # Dados de entrada (CPFs, IDs etc.)
│       └── features           # Cenários escritos em Gherkin
```

## 🚀 Como Executar os Testes

1. Clone este repositório:

```bash
git clone https://github.com/AntonioSantos005/apiCredito-teste-automation.git
cd apiCredito-teste-automation
```

2. Execute os testes com Maven:

```bash
mvn clean test
```

## 🧪 Funcionalidades Testadas

### 📄 `ManterSimulacao.feature`
- Criar nova simulação
- Consultar simulações existentes
- Atualizar simulação
- Excluir simulação

### 📄 `Restricoes.feature`
- Verificar existência de restrições para CPFs específicos

## 👤 Autor

Antônio de Sousa – [LinkedIn](https://www.linkedin.com/in/antoniosousas/)
- Projeto desenvolvido em 2021
