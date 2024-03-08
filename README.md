# search-domain

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory. Be aware that it’s not an _über-jar_ as
the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/search-domain-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A Jakarta REST implementation utilizing
  build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the
  extensions that depend on it.
- Elasticsearch REST client ([guide](https://quarkus.io/guides/elasticsearch)): Connect to an Elasticsearch cluster
  using the REST low level client

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

==============================================================

# Delete an index
## deletando o index city 

```
curl --location --request DELETE 'http://localhost:9200/city'
```


# Create an index
## Criando o index city e configurando os indices de consultas com busca fonetica e outras praticas

```

curl --location --request PUT 'http://localhost:9200/city' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
  "mappings": {
    "properties": {
      "skuIBGE": { "type": "text" },
      "siglaPais": { 
        "type": "text",
        "fields": {
          "phonetic": {
            "type": "text",
            "analyzer": "phonetic_analyzer",
            "search_analyzer": "standard"
          }
        }
      },
      "type": { "type": "keyword" },  // Alteração para keyword
      "ordemAlfabetica": { "type": "text" },
      "skuFeriaz": { "type": "text" },
      "municipio": { 
        "type": "keyword",  // Alteração para keyword
        "fields": {
          "phonetic": {
            "type": "text",
            "analyzer": "phonetic_analyzer",
            "search_analyzer": "standard"
          }
        }
      },
      "pais": { 
        "type": "keyword",  // Alteração para keyword
        "fields": {
          "phonetic": {
            "type": "text",
            "analyzer": "phonetic_analyzer",
            "search_analyzer": "standard"
          }
        }
      },
      "regiao": { "type": "text" },
      "uf": { "type": "text" },
      "regiaoTuristica": { 
        "type": "keyword",  // Alteração para keyword
        "fields": {
          "phonetic": {
            "type": "text",
            "analyzer": "phonetic_analyzer",
            "search_analyzer": "standard"
          }
        }
      },
      "arrecadacao": { "type": "text" },
      "cluster": { "type": "text" },
      "paisCamel": { 
        "type": "keyword",  // Alteração para keyword
        "fields": {
          "phonetic": {
            "type": "text",
            "analyzer": "phonetic_analyzer",
            "search_analyzer": "standard"
          }
        }
      },
      "nomeEstado": { 
        "type": "keyword",  // Alteração para keyword
        "fields": {
          "phonetic": {
            "type": "text",
            "analyzer": "phonetic_analyzer",
            "search_analyzer": "standard"
          }
        }
      },
      "continente": { "type": "text" }
    }
  },
  "settings": {
    "analysis": {
      "filter": {
        "phonetic_filter": {
          "type": "phonetic",
          "encoder": "metaphone",
          "replace": false
        }
      },
      "analyzer": {
        "phonetic_analyzer": {
          "tokenizer": "standard",
          "filter": ["lowercase", "phonetic_filter"]
        }
      }
    }
  }
}
'

```




# Validate index with count
## Consulta a quantidade de registros

```

curl --location --request GET 'http://localhost:9200/city/_count'

```

# Search city by fonetic algorithm
## Consulta a quantidade de registros

```

curl --location --request GET 'http://localhost:9200/city/_search' \
--header 'Content-Type: application/json' \
--data-raw '{
  "query": {
    "bool": {
      "should": [
        {
          "multi_match": {
            "query": "sao paulo",
            "fields": [
              "municipio^2",
              "nomeEstado^2",
              "paisCamel^2",
              "regiaoTuristica^2",
              "municipio.phonetic^2",
              "nomeEstado.phonetic^2",
              "paisCamel.phonetic^2",
              "regiaoTuristica.phonetic^2",
              "municipio.lowercase^2",
              "nomeEstado.lowercase^2",
              "paisCamel.lowercase^2",
              "regiaoTuristica.lowercase^2"
            ],
            "type": "best_fields",
            "operator": "or",
            "tie_breaker": 0.3  // Adicionando o tie_breaker
          }
        },
        {
          "multi_match": {
            "query": "sao paulo",
            "fields": [
              "municipio",
              "nomeEstado",
              "paisCamel",
              "regiaoTuristica",
              "municipio.phonetic^2",
              "nomeEstado.phonetic^2",
              "paisCamel.phonetic^2",
              "regiaoTuristica.phonetic^2",
              "municipio.lowercase^2",
              "nomeEstado.lowercase^2",
              "paisCamel.lowercase^2",
              "regiaoTuristica.lowercase^2"
            ],
            "fuzziness": "AUTO"
          }
        }
      ]
    }
  },
  "size": 5,
  "sort": [
    {
      "type": { "order": "desc" }  // Classificação por type em ordem decrescente
    },
  
    {
      "_score": { "order": "desc" }  // Classificação por score (relevância)
    }
  ]
}
'

```

