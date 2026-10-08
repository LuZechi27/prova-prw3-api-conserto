# API de Consertos

API para gerenciamento de consertos de veículos.

## Endpoints

| Método   | Endpoint                 | Descrição                                        |
| -------- | ------------------------ | ------------------------------------------------ |
| `POST`   | `/conserto`              | Cadastra um novo conserto                        |
| `GET`    | `/conserto`              | Retorna todos os consertos detalhadamente        |
| `GET`    | `/conserto/simplificado` | Retorna todos os consertos de forma simplificada |
| `GET`    | `/conserto/:id`          | Retorna um conserto específico                   |
| `PUT`    | `/conserto/:id`          | Atualiza um conserto                             |
| `DELETE` | `/conserto/:id`          | Deleta logicamente um conserto                   |

> **Observação:** nos exemplos abaixo, valores representados por `0` são valores numéricos (`number`/`Integer`), e não strings.

---

## POST `/conserto`

Cadastra um novo conserto.

### Corpo da requisição

```json
{
    "dataEntrada": "XX/XX/XXXX",
    "dataSaida": "XX/XX/XXXX",
    "mecanicoResponsavel": {
        "nome": "NOME",
        "anosExperiencia": 0
    },
    "veiculo": {
        "marca": "MARCA",
        "modelo": "MODELO",
        "ano": "XXXX",
        "cor": "COR"
    }
}
```

### Exemplo

```json
{
    "dataEntrada": "05/08/2026",
    "dataSaida": "10/08/2026",
    "mecanicoResponsavel": {
        "nome": "Mc Anico",
        "anosExperiencia": 10
    },
    "veiculo": {
        "marca": "Fiat",
        "modelo": "Uno",
        "ano": "2021",
        "cor": "Vermelho"
    }
}
```

### Campos

* `dataEntrada`: data de entrada do veículo no formato `DD/MM/AAAA`.
* `dataSaida`: data de saída do veículo no formato `DD/MM/AAAA`.
* `mecanicoResponsavel.nome`: nome do mecânico responsável.
* `mecanicoResponsavel.anosExperiencia`: quantidade de anos de experiência do mecânico. É um número.
* `veiculo.marca`: marca do veículo.
* `veiculo.modelo`: modelo do veículo.
* `veiculo.ano`: ano do veículo.
* `veiculo.cor`: cor do veículo.

---

## GET `/conserto`

Retorna todos os consertos cadastrados, apresentando os dados de forma detalhada.

### Requisição

Não é necessário enviar corpo na requisição.

```http
GET /conserto
```

---

## GET `/conserto/simplificado`

Retorna todos os consertos cadastrados utilizando uma versão simplificada dos dados.

### Requisição

```http
GET /conserto/simplificado
```

Não é necessário enviar corpo na requisição.

---

## GET `/conserto/:id`

Retorna os dados de um conserto específico.

Substitua `:id` pelo ID do conserto desejado.

### Exemplo

```http
GET /conserto/1
```

Nesse exemplo, serão retornados os dados do conserto cujo ID é `1`.

Não é necessário enviar corpo na requisição.

---

## PUT `/conserto/:id`

Atualiza os dados de um conserto existente.

Substitua `:id` pelo ID do conserto que deseja alterar.

### Exemplo

```http
PUT /conserto/1
```

### Corpo da requisição

```json
{
    "dataSaida": "XX/XX/XXXX",
    "nomeMecanico": "NOME",
    "anosExperiencia": 0
}
```

Todos os campos são **opcionais**. Portanto, é possível alterar apenas um ou alguns campos.

### Alterar apenas a data de saída

```json
{
    "dataSaida": "15/08/2026"
}
```

### Alterar apenas o nome do mecânico

```json
{
    "nomeMecanico": "João Silva"
}
```

### Alterar apenas os anos de experiência

```json
{
    "anosExperiencia": 15
}
```

### Alterar vários campos

```json
{
    "dataSaida": "15/08/2026",
    "nomeMecanico": "João Silva",
    "anosExperiencia": 15
}
```

> **Importante:** no `PUT`, os campos `nomeMecanico` e `anosExperiencia` ficam diretamente no corpo da requisição. Não devem ser colocados dentro de `mecanicoResponsavel`.

Correto:

```json
{
    "anosExperiencia": 15
}
```

Incorreto:

```json
{
    "mecanicoResponsavel": {
        "anosExperiencia": 15
    }
}
```

---

## DELETE `/conserto/:id`

Realiza a exclusão lógica de um conserto.

Substitua `:id` pelo ID do conserto que deseja excluir.

### Exemplo

```http
DELETE /conserto/1
```

A exclusão é **lógica**, portanto o registro não é removido fisicamente do banco de dados.

Em vez disso, o campo:

```text
ativo
```

é alterado para:

```text
false
```

Dessa forma, o registro permanece armazenado no banco, mas é considerado inativo.

---

## Formato das datas

As datas devem ser enviadas no formato:

```text
DD/MM/AAAA
```

Exemplo:

```text
05/08/2026
```

---

## Tipos dos dados

Nos exemplos deste documento:

* Valores entre aspas (`"texto"`) são `String`.
* Valores como `0`, `10` ou `15` são números.
* Datas são enviadas como `String` no formato `DD/MM/AAAA`.
* `id` é um número que identifica o conserto.

Por exemplo:

```json
{
    "anosExperiencia": 10
}
```

`10` é um número.

Não deve ser enviado como:

```json
{
    "anosExperiencia": "10"
}
```

---

## Resumo

### Criar

```http
POST /conserto
```

Envia os dados completos do conserto.

### Listar detalhadamente

```http
GET /conserto
```

Retorna todos os consertos com seus dados detalhados.

### Listar de forma simplificada

```http
GET /conserto/simplificado
```

Retorna todos os consertos com dados simplificados.

### Buscar por ID

```http
GET /conserto/:id
```

Retorna um único conserto.

### Atualizar

```http
PUT /conserto/:id
```

Atualiza um ou mais campos do conserto.

### Deletar

```http
DELETE /conserto/:id
```

Realiza uma exclusão lógica, alterando `ativo` para `false`.
