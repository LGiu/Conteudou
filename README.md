# Conteudou

## O que é?
________________________

O Conteudo é um sistema para compatilhamento de conteúdo para estudos. Hoje, a internet é um celeiro repleto de contéudo, porém é díficil para um estudante achar aquele conteúdo que realmente é relevante para seus estudos. O Conteudo tem objeto de garimpar conteúdo realmente relevante e de boa qualidade aos usuário através da coooperação entre eles.


## Sistema
________________________

O backend foi desenvolvido em Java 8, utilizando Spring boot, JPA, Hibernate e PostgreSQL.


## REQUESTS E EXEMPLOS
________________________

### AVALIACAO

#### Header

    Content-Type:application/json

    Authorization: Bearer 4d49c51d-fa3a-4df6-9a6b-f74244b4a5f8

#### Salvar
 
    POST - http://127.0.0.1:8080/avalicao
 
    { "conteudo": { "id": 1 }, "avaliacao": 5 }

#### Alterar

    PUT - http://127.0.0.1:8080/avalicao
    
    { "id": 1, "conteudo": { "id": 1 }, "avaliacao": 5 }

#### - Excluir

    DELETE  http://127.0.0.1:8080/avalicao/{id}

#### Buscar Um

    GET - http://127.0.0.1:8080/avalicao/{id}

#### Burcar Todos

    GET - http://127.0.0.1:8080/avalicoes

#### Burcar com Filtro

    GET - http://127.0.0.1:8080/avalicoes?ordem=ASC
    
    GET - http://127.0.0.1:8080/avalicoes?tamanho=10
    
    GET - http://127.0.0.1:8080/avalicoes?paginaAtual=1
    
    GET - http://127.0.0.1:8080/avalicoes?filtros=id=1
    
    GET - http://127.0.0.1:8080/avalicoes?filtros=avaliacao<>1
    
    GET - http://127.0.0.1:8080/avalicoes?filtros=id=2&ordem=ASC&tamanho=10&paginaAtual=1

__________________

### COMENTÁRIO

#### Header

    Content-Type:application/json
    
    Authorization: Bearer 4d49c51d-fa3a-4df6-9a6b-f74244b4a5f8

#### Salvar
 
    POST - http://127.0.0.1:8080/comentario
     
    { "conteudo": { "id": 1 }, "comentario": "teste" }

#### Alterar

    PUT - http://127.0.0.1:8080/comentario
    
    { "id": 1, "conteudo": { "id": 1 }, "comentario": "teste alterado" }

#### Excluir

    DELETE  http://127.0.0.1:8080/comentario/{id}

#### Buscar Um

    GET - http://127.0.0.1:8080/comentario/{id}

#### Burcar Todos

    GET - http://127.0.0.1:8080/comentarios

#### Burcar com Filtro

    GET - http://127.0.0.1:8080/comentarios?filtros=id=1
    
    GET - http://127.0.0.1:8080/comentarios?filtros=comentario CONTEM teste
    
    GET - http://127.0.0.1:8080/comentarios?filtros=id=2&ordem=ASC&tamanho=10&paginaAtual=1

__________________

### CONTEUDO

#### Header

    Content-Type:application/json
    
    Authorization: Bearer 4d49c51d-fa3a-4df6-9a6b-f74244b4a5f8

#### Salvar
 
    POST - http://127.0.0.1:8080/conteudo
     
    { "usuarioMateria": { "id": 1 }, "nome": "teste", "descricao": "teste", "cor": "#ffffff" }

#### Alterar

    PUT - http://127.0.0.1:8080/conteudo
    
    { "id": 1, "usuarioMateria": { "id": 1 }, "nome": "teste", "descricao": "teste", "cor": "#ffffff" }

#### Excluir

    DELETE  http://127.0.0.1:8080/conteudo/{id}

#### Buscar Um

    GET - http://127.0.0.1:8080/conteudo/{id}

#### Burcar Todos

    GET - http://127.0.0.1:8080/conteudos

#### Burcar com Filtro

    GET - http://127.0.0.1:8080/conteudos?filtros=id=1
    
    GET - http://127.0.0.1:8080/conteudos?filtros=nome DIFERENTE teste
    
    GET - http://127.0.0.1:8080/conteudos?filtros=usuarioMateria.iSubMateria=1
    
    GET - http://127.0.0.1:8080/conteudos?filtros=id=2&ordem=ASC&tamanho=10&paginaAtual=1

__________________

### CURSO

#### Header

    Content-Type:application/json
    
    Authorization: Bearer 4d49c51d-fa3a-4df6-9a6b-f74244b4a5f8

#### Salvar
 
    POST - http://127.0.0.1:8080/curso
     
    { "nome": "teste", "descricao": "teste", "cor": "#ffffff", "posicao": "1" }

#### Alterar

    PUT - http://127.0.0.1:8080/curso
    
    { "id": 1, "nome": "teste", "descricao": "teste", "cor": "#ffffff", "posicao": "2" }

#### Excluir

    DELETE  http://127.0.0.1:8080/curso/{id}

#### Buscar Um

    GET - http://127.0.0.1:8080/curso/{id}

#### Burcar Todos

    GET - http://127.0.0.1:8080/cursos

#### Burcar com Filtro

    GET - http://127.0.0.1:8080/cursos?filtros=id=1
    
    GET - http://127.0.0.1:8080/cursos?filtros=nome=teste
        
    GET - http://127.0.0.1:8080/cursos?filtros=id=2&ordem=ASC&tamanho=10&paginaAtual=1

__________________

### LINK

#### Header

    Content-Type:application/json
    
    Authorization: Bearer 4d49c51d-fa3a-4df6-9a6b-f74244b4a5f8

#### Salvar
 
    POST - http://127.0.0.1:8080/link
     
    { "conteudo": { "id": 1 }, "icone": "", "url": "www.google.com", "posicao": "1" }

#### Alterar

    PUT - http://127.0.0.1:8080/link
    
    { "id": 1, "conteudo": { "id": 1 }, "icone": "", "url": "www.google.com", "posicao": "1" }

#### Excluir

    DELETE  http://127.0.0.1:8080/link/{id}

#### Buscar Um

    GET - http://127.0.0.1:8080/link/{id}

#### Burcar Todos

    GET - http://127.0.0.1:8080/links

#### Burcar com Filtro

    GET - http://127.0.0.1:8080/links?filtros=id=1
    
    GET - http://127.0.0.1:8080/links?filtros=conteudo.nome DIFERENTE teste
        
    GET - http://127.0.0.1:8080/links?filtros=id=2&ordem=ASC&tamanho=10&paginaAtual=1

__________________

### MATÉRIA

#### Header

    Content-Type:application/json
    
    Authorization: Bearer 4d49c51d-fa3a-4df6-9a6b-f74244b4a5f8

#### Salvar
 
    POST - http://127.0.0.1:8080/usuario-materia
     
    { "curso": { "id": 1 }, "nome": "teste", "descricao": "teste", "cor": "#ffffff" }

#### Alterar

    PUT - http://127.0.0.1:8080/usuario-materia
    
    { "id": 1, "curso": { "id": 1 }, "nome": "teste", "descricao": "teste", "cor": "#ffffff" }

#### Excluir

    DELETE  http://127.0.0.1:8080/usuario-materia/{id}

#### Buscar Um

    GET - http://127.0.0.1:8080/usuario-materia/{id}

#### Burcar Todos

    GET - http://127.0.0.1:8080/usuario-materias

#### Burcar com Filtro

    GET - http://127.0.0.1:8080/usuario-materias?filtros=id=1
    
    GET - http://127.0.0.1:8080/usuario-materias?filtros=curso.nome=teste
        
    GET - http://127.0.0.1:8080/usuario-materias?filtros=id=2&ordem=ASC&tamanho=10&paginaAtual=1

__________________

### SUB-MATÉRIA

#### Header

    Content-Type:application/json
    
    Authorization: Bearer 4d49c51d-fa3a-4df6-9a6b-f74244b4a5f8

#### Salvar
 
    POST - http://127.0.0.1:8080/usuario-materia
     
    { "materia": { "id": 1 }, "nome": "teste", "descricao": "teste", "cor": "#ffffff" }

#### Alterar

    PUT - http://127.0.0.1:8080/usuario-materia
    
    { "id": 1, "materia": { "id": 1 }, "nome": "teste", "descricao": "teste", "cor": "#ffffff" }

#### Excluir

    DELETE  http://127.0.0.1:8080/usuario-materia/{id}

#### Buscar Um

    GET - http://127.0.0.1:8080/usuario-materia/{id}

#### Burcar Todos

    GET - http://127.0.0.1:8080/usuario-materias

#### Burcar com Filtro

    GET - http://127.0.0.1:8080/usuario-materias?filtros=id=1
    
    GET - http://127.0.0.1:8080/usuario-materias?filtros=materia.nome IGUAL teste
        
    GET - http://127.0.0.1:8080/usuario-materias?filtros=id=2&ordem=ASC&tamanho=10&paginaAtual=1

__________________

### USUARIO

#### Header

    Content-Type:application/json
    
    Authorization: Bearer 4d49c51d-fa3a-4df6-9a6b-f74244b4a5f8

#### Salvar
 
    POST - http://127.0.0.1:8080/usuario
     
    { "email": "teste@teste.com", "senha": "teste", "nome": "teste" }

#### Alterar

    PUT - http://127.0.0.1:8080/usuario
        
    { "id": 1, "email": "teste@teste.com", "senha": "teste", "nome": "teste" }

#### Excluir

    DELETE  http://127.0.0.1:8080/usuario/{id}

#### Buscar Um

    GET - http://127.0.0.1:8080/usuario/{id}

#### Burcar Todos

    GET - http://127.0.0.1:8080/usuarios
    
__________________

### LOGIN

#### Header

    Content-Type:application/json
    
    Authorization: Basic Y2xpZW50LWlkOnNlY3JldC1pZA==

#### LOGAR
 
    POST - http://localhost:8080/oauth/token