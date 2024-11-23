# QR Code Generator API

Este projeto implementa uma API para a geração de QR Codes. Ela permite que os usuários enviem um conteúdo, e a API retorna uma imagem gerada do QR Code correspondente ao conteúdo fornecido. A API foi desenvolvida usando o framework Spring Boot e oferece suporte a exceções personalizadas e tratamento adequado de erros.

## Funcionalidades

- Geração de QR Code a partir de um conteúdo fornecido no corpo da requisição.
- Validação de conteúdo: rejeita conteúdo nulo ou vazio.
- Tratamento de erros com mensagens específicas para falhas de conteúdo ou falhas internas do servidor.

## Endpoints

### `POST /api/v1/qr/generate`

Este endpoint gera um QR Code com base no conteúdo enviado.

**Requisição**

```json
POST /api/v1/qr/generate
Content-Type: application/json
````

**Body**

````json
{
  "content": "https://example.com"
}
````
**Resposta de Sucesso**
- Status: 200 OK
- Cabeçalho: Content-Type: image/png
- Corpo: Imagem em formato PNG contendo o QR Code gerado. 

**Resposta de Erro**

1. Conteúdo inválido (nulo ou vazio)

- Status: 400 Bad Request
- Corpo: Mensagem de erro, como "Content cannot be null or empty"
2. Erro interno (geração do QR Code falhou)
- Status: 500 Internal Server Error
- Corpo: Mensagem genérica de erro

**Requisitos**
- Java 21 ou superior
- Gradle para gerenciamento de dependências

**Como Rodar o Projeto**
## Como Rodar o Projeto

### Usando Gradle

1. Clone o repositório:

    ```bash
    git clone https://github.com/lindolfomoizinho/qr-code.git
    cd qr-code-generator
    ```

2. Certifique-se de que o Gradle está instalado em sua máquina. Caso não tenha o Gradle instalado, siga as instruções da [documentação oficial do Gradle](https://gradle.org/install/).

3. Compile e rode a aplicação:

    ```bash
    ./gradlew bootRun
    ```

4. A API estará disponível em `http://localhost:8080`.