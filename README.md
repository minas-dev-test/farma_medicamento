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

    - "/medicamentos": 
        - get: pesquisa de medicamentos, retorna uma página de Medicamento; parâmetros: MedicamentoFilter e Pageable
        - post: criar medicamento, retorna um ResponseEntity; parâmetros: Medicamento, HttpServletResponse
    - "/medicamentos/{codigo}"
        - get: busca pelo código do medicamento, retorna um Medicamento; parâmetros: String codigo
    - "/medicamentos/filtrar-nomes/{nome}"
        - get: busca pelo nome do medicamento, retorna lista de Medicamento; parâmetros: String nome
    
    ##obs: lote é o ID do Medicamento
    - "/medicamentos/{lote}"
        - put: atualiza um Medicamento(exceto o 'lote'), retorna um ResponseEntity; parâmetros: String lote, Medicamento
    - "/medicamentos/atualizar-status/{lote}"
        - put: atualiza um Medicamento(exceto o 'lote', 'estoque', 'tipo'), retorna um ResponseEntity; parâmetros: String lote, Medicamento
    - "/medicamentos/acrescentar-ao-estoque/{lote}"
        - put : atualiza o campo 'estoque' do Medicamento, retorna um ResponseEntity; parâmetros: String lote, Medicamento

    ## lote é o ID do Estoque
    - "/estoque/acrescentar-ao-estoque/{lote}"
        - put: atualiza o objeto do tipo Estoque, retorna um ResponseEntity; parâmetros: String lote, Estoque

    - "/solicitacao"
        - post: cria uma nova solicitação, retorna um ResponseEntity; parâmetros: Solicitacao, HttpServletResponse
    
    - "/solicitacao/excluir-solicitacao/{lote}"
        - put: exclui uma solicitação, retorna um ResponseEntity; parâmetros: String lote, Solicitacao

    - "/solicitacao/confirmar-solicitacao/{lote}"
        - put: altera o status da solicitação para 'confirmada' e remove os medicamentos referentes do estoque; parâmetros: String lote, Solicitacao

    - "/status"
        - get: retorna uma lista com todos os Status; parâmetros: n/a

    - "/tipos"
        - get: retorna uma lista com todos os TipoMedicamento; parâmetros: n/a
