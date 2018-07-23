# vega
## branch JSP-JDBC

Riprendo da dove ero arrivato con la parte jsp, in cui sostanzialmente non c'era persistenza.
In questo branch aggiungo la persistenza dei dati tramite jdbc.

Il supporto dell'application server è limitato alla disponibilità di un oggetto DataSource reperibile via JNDI. Il nome jndi cella risorsa sarà fornito in un parametro di avviamento dell'applicazione.

Ho fatto uno schema di massima del db qui: https://app.sqldbm.com/MySQL/Share/Dn47AWj1WVnmmmD2bKGaFkGFrngIE8md_DYjF4jNYw0

Qui ci sarà il classico meccanismo dei DAO la cui implementazione userà l'API classica JDBC

ROADMAP: Data layer in jdbc semplice, frontend jsf/facelets, primefaces o equivalente, zkoss?
