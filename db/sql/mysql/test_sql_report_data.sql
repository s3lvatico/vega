USE sandbox;


SELECT *
FROM vg_allergene;

SELECT count(1)
FROM vg_allergene;

SELECT *
FROM vg_categoria;

SELECT count(1)
FROM vg_categoria;


SELECT *
FROM vg_categoria_allergene;


-- categorie con allergens

SELECT
  cat.name   category,
  allrg.name allergen
FROM
  vg_categoria_allergene jn
  JOIN
  vg_categoria cat ON jn.id_categoria = cat.uuid
  JOIN
  vg_allergene allrg ON jn.id_allergene = allrg.uuid
ORDER BY category, allergen;
--


SELECT *
FROM vg_report;

SELECT *
FROM vg_report_data;

SELECT count(1)
FROM vg_report_data;

-- pulizia

DELETE FROM vg_categoria;
DELETE FROM vg_report_data;
DELETE FROM vg_report;
DELETE FROM vg_allergene;


