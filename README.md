# Financial Wallet

## 🚀 Documentação da API de Pagamentos (Wallet & Pix).
Bem-vindo ao projeto! Para facilitar a integração e o entendimento do sistema, disponibilizamos três formas principais de explorar e testar nossos endpoints.

### Pré-requisitos (Banco de dados)

1. Baixar, instalar e iniciar o Docker
2. Executar o seguinte comando na raiz desse projeto:

```bash 
docker-compose up -d 
```

3. Após execução bem-sucedida você deve ter conseguido subir o container com as tabelas já criadas

4. Obs: Caso possua um Postgres em execução na sua máquina, é recomendado interrompê-lo

### 📚 Opções de Documentação
Escolha a opção que melhor se adapta à sua necessidade (após execução):

| Opção           | Formato             | URL de Acesso                                   | Uso Recomendado                                                  |
|-----------------|---------------------|--------------------------------------------------|------------------------------------------------------------------|
| Swagger UI      | Interface Visual    | http://localhost:8080/swagger-ui/index.html      | Testes manuais interativos e exploração rápida.                  |
| OpenAPI JSON    | Dados Estruturados  | http://localhost:8080/v3/api-docs                | Automação e importação em ferramentas de terceiros.              |
| OpenAPI YAML    | Texto Legível       | http://localhost:8080/v3/api-docs.yaml           | Leitura técnica e documentação estática.                         |

