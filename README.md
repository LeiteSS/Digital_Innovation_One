# Diario de Bordo: Consultas com JDBC

> [Java Database Connectivity](https://en.wikipedia.org/wiki/Java_Database_Connectivity) (**JDBC**) is an application programming interface (API) for the programming  language Java, which defines how a client may access a database. It is a Java-based data access technology used for Java database connectivity.  It is part of the Java Standard Edition platform, from Oracle  Corporation.

**Objetivo:** Desenvolver um pequeno projeto para realizar consultas em um banco de dados usando o **mysql** e **JDBC**. 

---

21 de março de 2021 - 15:00

- Instalar o **xampp**, pois este vem com o **mysql-server** e com o **mariadb** também.

21 de março de 2021 - 15:10

- Criar o um banco de dados chamado `digital_innovation_one` e passar a usa-la.

- Cria uma tabela chamada `aluno` com os atributos `id`, `nome`, `idade` e `estado`.

- Inserir os seguintes dados: 

  ```
  nome=Pedro; idade= 20; estado=RJ
  nome=Maria; idade=35; estado=AC
  nome=Joao; idade=10; estado=SC
  nome=Ana; idade=51; estado=GO
  ```

21 de março de 2021 - 15:20

- Ao criar o projeto selecionar o **gradle**.

- No documento **build.gradle** implementar o(s) driver(s) do(s) banco(s) de dados.

  ```properties
      implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.23'
      implementation 'org.mariadb.jdbc:mariadb-java-client:2.1.2'
  ```

- Implementar neste mesmo arquivo o **Lombok** 

  ```properties
      compileOnly 'org.projectlombok:lombok:1.18.16'
      annotationProcessor 'org.projectlombok:lombok:1.18.16'
  
      testCompileOnly 'org.projectlombok:lombok:1.18.16'
      testAnnotationProcessor 'org.projectlombok:lombok:1.18.16'
  ```

- Dentro da pasta **resources**, fazer um novo arquivo ***properties*** chamado `connection.properties` com as propriedades: 

  ```properties
  #jdbc.driver=mysql
  jdbc.driver=mariadb
  db.address=localhost
  db.name=digital_innovation_one
  db.user.login=root
  db.user.password=#
  ```
---
21 de março de 2021 - 15:35

- Escrever a classe de conexão chamada  `ConnectionFactory`, do qual usará o `connection.properties` para armazenar em variaveis do tipo `String` as propriedades: `jdbc.driver`, `db.address`, `db.name`,  `db.user.login` e `db.user.password` e através do `StringBuilder` termos a seguinte `url` de conexão:  `jdbc:mariadb://localhost/digital_innovation_one` para que através do `DriverManager.getConnection(url de conexao, usuario, senha)` seja possivel criar conexão com o banco de dados.
---
21 de março de 2021 - 15: 45
- Escrever um classe chamada `Aluno`, do qual será a entidade, teremos os mesmos atributos do banco de dados e para não precisarmos escrever os ***getters*** e ***setters*** de cada atributo, será usado o **Lombok** através da anotação `@Data`. Carregar essa classe com três tipos de construtores: um com os quatro atributos, outro sem o atributo `id` e por fim, um construtor vazio.
---
21 de março de 2021 - 15:55
- Escrever uma classe chamada `AlunoDAO`, do qual terá as consultas (Listar, procurar, deletar, inserir e atualizar).

  > [**Objeto de acesso a dados**](https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados) (acrônimo do inglês **Data Access Object** - **DAO**), é um padrão para aplicações que utilizam persistência de dados, onde tem a separação das regras de negócio das regras de acesso a banco de dados, implementada com linguagens de programação orientadas a objetos (como por exemplo Java) e arquitetura MVC, onde todas as funcionalidades de bancos de dados, tais como obter conexões, mapear objetos para tipos de dados SQL ou executar comandos SQL, devem ser feitas por classes DAO.

- Testar as consulta na classe `Main`, aqui é boa prática aplicar o **TDD** ao escrever cada consulta. Ou seja, escrever a consulta `list()` e testar no `Main`. Feito isso, escrever o `getById(int id)` e testar no `Main`; e assim por diante.