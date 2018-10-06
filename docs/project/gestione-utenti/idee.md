# Gestione utenti

## Intro

Raccolgo qui le idee su come impostare la gestione degli utenti.

## Stato dell'arte

### 18917

Un utente è un'entità che ha i seguenti attributi

- identificativo (id) che lo rende unico
- password
- nome reale
- uno o più ruoli

## Funzionalità da aggiungere

0. pagina del profilo utente
0. pagina di gestione degli utenti

### Pagina del profilo utente

**Accessibilità** : ogni utente

#### Informazioni visualizzate

- nome completo utente
- id utente
- lista ruoli

#### Operazioni disponibili

- modifica nome completo
- modifica password

### Pagina di gestione degli utenti

**Accessibilità** : solo utenti con ruolo _v-admin_

#### Informazioni visualizzate

Tabella utenti

.|.|.|.|.
---|---|---|:---:|:---:
id | nome completo | ruoli | [mod] | [del]

#### Operazioni disponibili

- modifica utente (nome completo e/o password e/o ruoli)
- eliminazione utente    
- creazione nuovo utente
