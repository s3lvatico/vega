# vega

## Branch JSP-JDBC-SPRING

A partire dalla versione semplice in jsp+jdbc provo ad aggiungere il container spring.

### Sommario delle modifiche da apportare adottando Spring-data

- uso di una implementazione specifica del `DataSource`
- proprietà del DataSource definite in un file specifico (qualcosa tipo `datasource.properties`)
- creazione dell'IoC container nel listener di avvio
- recupero del DataSource nel listener di avvio e iniezione nella DaoFactory
- [fatto] modifica dei DAO in modo che utilizzino internamente un `JdbcTemplate`
- [fatto] `JdbcTemplate` si inizializza con un `DataSource`, perciò i DAO appena creati devono ricevere un DataSource per inizializzare il proprio JdbcTemplate 
- togli la definizione del datasource dal context.xml

...altro...