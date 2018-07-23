tutte le categorie lo ometto per semplicità

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
  select id, e_name
  from allergene
  where e_name = ? and deleted = 0
  
allergene specifico per id
  select id, e_name
  from allergene
  where id = ? and deleted = 0
  
