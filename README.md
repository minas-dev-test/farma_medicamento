# Farmácia Medicamentos

Microserviço de gerenciamento de medicamentos

## Instalação

Tenha instalado o Docker e o docker-compose (Versões mais recentes).

Após baixar os arquivos da imagem, execute:

    docker-compose up

O Servidor da API irá ser inicializado em <localhost:3000>.

## Configuração

O microserviço necessita que o banco esteja rodando antes do mesmo iniciar.
Por isto, usando-o com o docker-compose, é necessário esperar a inicialização do banco
(usando o script `wait-for-it`).

O serviço é executado na porta 8080, sendo possível mapear para outra porta.

Informando variáveis de ambiente é possível modificar as configurações do arquivo [`application.properties`](./src/main/resources/application.properties). O formato deve estar em uppercase e usando `_` ao invés de pontos.

As principais variáveis são:

- `SPRING_DATASOURCE_URL`: URL do banco de dados no formato `jdbc:mysql://ip:porta/banco`
- `SPRING_DATASOURCE_USERNAME`: Usuário do BD
- `SPRING_DATASOURCE_PASSWORD`: Senha do usuário do BD

## Rotas

TODO: Listar rotas e instruções