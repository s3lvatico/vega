# vega

## branch "yabu" (ex JSP-JDBC)

Riprendo da dove ero arrivato con la parte jsp, in cui sostanzialmente non c'era persistenza.
In questo branch aggiungo la persistenza dei dati tramite jdbc.

Il supporto dell'application server è limitato alla disponibiliàdi un oggetto DataSource reperibile via JNDI. Il nome jndi cella risorsa sarà fornito in un parametro di avviamento dell'applicazione.

Ho fatto uno schema di massima del db qui: https://goo.gl/YQyU7d

Qui ci sarà il classico meccanismo dei DAO la cui implementazione userà l'API classica JDBC

ROADMAP: Data layer in jdbc semplice, sicurezza basic o con mini-db lato server, frontend jsf/facelets, primefaces o equivalente, zkoss?

### altre note

Sembra che sia necessaria la presenza del driver jdbc nella lib di tomcat
