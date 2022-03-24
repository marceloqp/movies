### Especificações
Desenvolva uma API RESTful para possibilitar a leitura da lista de indicados e vencedores
da categoria Pior Filme do Golden Raspberry Awards.

## Configurando ambiente para teste

### Criando base local
- Como o projeto está rodando com liquibase, basta iniciar a aplicação que a criação das tabelas será de forma automática.

### Gerando um war para testes locais
- ./gradlew war na raiz do projeto.

### Informação sobre os endpoints
- Iniciado o projeto, basta acessar o Swagger pelo endereço:
  [localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)

P.S. Optei por criar um endpoint para subir o CSV, para facilitar testes futuros, podendo ser colocado qualquer CSV com os mesmos campos pelo endpoint.