# Conteudou

O que é?
________________________

O Conteudo é um sistema para compatilhamento de conteúdo para estudos. Hoje, a internet é um celeiro repleto de contéudo, porém é díficil para um estudante achar aquele conteúdo que realmente é relevante para seus estudos. O Conteudo tem objeto de garimpar conteúdo realmente relevante e de boa qualidade aos usuário através da coooperação entre eles.


Sistema
________________________

O backend foi desenvolvido em Java 8, utilizando Spring boot, JPA, Hibernate e PostgreSQL.


REQUESTS
________________________

AVALIACAO

- Header

Content-Type:application/json

Authorization Bearer 4d49c51d-fa3a-4df6-9a6b-f74244b4a5f8

- Salvar
 
POST - http://127.0.0.1:8090/avalicao
 
{ "conteudo": { "iConteudo": 1 }, "avaliacao": 5 }

- Alterar

PUT - http://127.0.0.1:8090/avalicao

{ "iAvaliacao": 1, "conteudo": { "iConteudo": 1 }, "avaliacao": 5 }

- Excluir

DELETE  http://127.0.0.1:8090/avalicao/{id}

- Buscar Um

GET - http://127.0.0.1:8090/avalicao/{id}

- Burcar Todos

GET - http://127.0.0.1:8090/avalicoes

- Burcar com Filtro

GET - http://127.0.0.1:8090/avalicoes?ordem=ASC

GET - http://127.0.0.1:8090/avalicoes?tamanho=10

GET - http://127.0.0.1:8090/avalicoes?paginaAtual=1

GET - http://127.0.0.1:8090/avalicoes?filtros=avaliacao=1

GET - http://127.0.0.1:8090/avalicoes?filtros=avaliacao<>1

GET - http://127.0.0.1:8090/avalicoes?filtros=avaliacao=2&ordem=ASC&tamanho=10&paginaAtual=1