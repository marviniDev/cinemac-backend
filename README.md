# cinemac-backend

Sobre o Projeto

Este é um projeto Restful, uma api desenvolvida com o intuito de estudos e práticas sobre desenvolvimento de aplicações backend.

Ferramentas
Java JDK (versão atual)
Maven
Spring Boot
Hibernate
Postgres 
Vs Code

Configuração para básica para o projeto
instale o Java JDK

Link: https://download.oracle.com/java/17/latest/jdk-17_windows-x64_bin.msi
Versão Atual	

instale o Postgres
	
Link: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
Baixe o instalador para Windows

instale o Maven

Link: https://maven.apache.org/download.cgi
Tipo do download: Binary zip archive

Descompacte o arquivo baixado no Disco Local: (‘C’) do Windows

Configuração de variáveis do ambiente
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


 
