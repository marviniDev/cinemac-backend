# cinemac-backend

<div align="center">

[![GitHub stars](https://img.shields.io/github/stars/marviniDev/cinemac-backend)](https://github.com/marviniDev/cinemac-backend/stargazers)<space> <space>[![GitHub license](https://img.shields.io/github/license/marviniDev/cinemac-backend)](https://github.com/marviniDev/cinemac-backend//blob/master/LICENSE)<space> <space>[![GitHub forks](https://img.shields.io/github/forks/marviniDev/cinemac-backend)](https://github.com/marviniDev/cinemac-backend//network)

</div>

## 📋 Índice

- [Sobre](#-Sobre)
- [Tecnologias utilizadas](#-Tecnologias-utilizadas)
- [Configurando o ambiente](#-Preparando-o-ambiente)
- [Como executar o projeto](#-Como-executar-o-projeto)

---

## 📖 Sobre

Este é um projeto Rest, uma api desenvolvida com o intuito de estudos e práticas sobre desenvolvimento de aplicações backend.

---

## 🚀 Tecnologias utilizadas

O projeto está desenvolvido utilizando as seguintes tecnologias:

- Java
- Maven
- Spring Boot
- Hibernate
- Postgres 

---
## ⌨ Preparando o ambiente
	
#instale o Java JDK

Link: https://download.oracle.com/java/17/latest/jdk-17_windows-x64_bin.msi
Versão Atual	

---
	
#instale o Postgres
	
Link: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
Baixe o instalador para Windows
	
Defina a senha 123 para acesso ao banco e crie um database chamado cinemac
	
---

#instale o Maven

Link: https://maven.apache.org/download.cgi
Tipo do download: Binary zip archive
	
---

#Descompacte o arquivo baixado no Disco Local: (‘C’) do Windows
	
---

#Configuração de variáveis do ambiente
Busque por "Editar variáveis de ambiente do sistema" > 
na tela de propriedades clique no botão "Variáveis de Ambiente">
na seção de "Variáveis de usuário para (usuário)" clique em novo>

-> nome da variável : M2_HOME
-> valor da variável : C:\apache-maven-3.8.4(caminho relativo ao pacote)
Clique em confirmar...

Na mesma sessão procure por um nome de variável chamada "path">
clique em editar e cadastre este dois parâmetros

;%M2_HOME%\bin;
C:\apache-maven-3.8.4\bin

Será necessário reiniciar a máquina.
	
---

## ⌨ Como executar o projeto

```bash
# Clonar o repositório
git clone https://github.com/marviniDev/cinemac-backend

# Entrar no diretório
cd cinemac-backend
	
# Verificar a disponibilidade do mavem
mvn --version
	
# limpar os resíduos
mvn clean

# Baixar as dependências
mvn install

# Executar o servidor
mvn spring-boot:run
```

Feito isso, abra o seu navegador e acesse `http://localhost:8080/api/swagger-ui/`

---

Desenvolvido por MarviniDev
