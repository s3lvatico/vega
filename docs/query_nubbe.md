### Categorie
Tutte le categorie lo ometto per semplicità.
Eliminazione categoria? Anche questa deve essere logica e  non fisica, sempre per via dei report che fanno riferimento anche al nome della categoria.

### Allergeni
tutti gli allergeni
  ```sql
  select * 
  from allergen ag
    join category cat on 
      cat.id = ag.id_category
  where
    ag.deleted = 0
  order by cat.e_name
  ```
  
allergene specifico per nome
  ```sql
  select id, e_name
  from allergene
  where e_name = ? and deleted = 0
  ```
allergene specifico per id
  ```sql
  select id, e_name
  from allergene
  where id = ? and deleted = 0
  ```
controllo esistenza allergene per nome
  ```sql
  select count(*)
  from allergene
  where e_name = ? and deleted = 0
  ```
  
