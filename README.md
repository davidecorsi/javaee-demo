# JavaEE demo
Applicazione demo per dimostrare alcune funzionalità di Java EE.
## Descrizione
### Api
L'applicazione fornisce un API rest per la gestione completa di un entità studenti.
- ***GET api/student*** - fornisce la lista degli studenti
- ***GET api/student/{id}*** - fornisce i dettagli di un singolo studente
- ***POST api/student*** - crea uno studente utilizzando i campi passati nel body della richiesta
- ***PUT api/student/{id}*** - modifica uno studente utilizzando i campi passati nel body della richiesta
- ***DELETE api/student*** - cancella uno studente
- ***GET api/health*** - semplice healthcheck

***NB: il body delle richieste e le risposte sono in formato application/json***
### Filter
L'applicazione utilizza un filtro per impostare nell'`MDC` informazioni relative all'utente che sta eseguendo l'operazione e un `transactionId` generato casualmente.
### Interceptor
Ogni invocazione di un'api genera un log come nel seguente formato:
`[GET, POST, PUT, DELETE]` `[methodName]` `[parameterList]`
### Validation
Se l'api di creazione o modifica dello studente vengono invocate con nome e cognome vuoti è stata creata una gestione dell'eccezione customizzata, per fornire una risposta contente la lista di ogni parametro sbagliato con il relativo messaggio di errore.
### Producer AMQ
Per ogni modifica del database(creazione, modifica, cancellazione di uno studente) viene generato un evento utilizzando il pattern Observer. L'evento viene gestito utilizzando l'annotation `@Observes` di JavaEE.
Il metodo una volta letto l'evento genera un messaggio che viene inviato all'`AMQ broker`.
### Consumer AMQ
I messaggi vengono consumati dal broker utilizzando i `@MessageDrivenBean` di JavaEE.
La gestione del messaggio prevede l'invocazione di un service che crea un log di audit da salvare sul database per tenere traccia delle operazioni effettuate sugli studenti.
### Security
La sicurezza è gestita permettendo l'accesso a 2 tipi di ruoli:
- ***user***
- ***admin***

La definizione degli utenti e ruoli è gestita attraverso 2 file:
- ***users.properties*** - per gli utenti(user=password)
- ***roles.properties*** - per i ruoli(user=ruolo)

Le api sono protette in questo modo:
- ***GET api/student*** - user, admin
- ***GET api/student/{id}*** - user, admin
- ***POST api/student*** - admin
- ***PUT api/student/{id}*** - admin
- ***DELETE api/student*** - admin
- ***GET api/health*** - non è necessaria l'autenticazione

***NB: l'autenticazione deve essere fornita tramite BASIC authentication***
## Scaricare ed impostare application server
- Scaricare un'istanza di `EAP 7.0`
- Copiare i file `user.properties` e `roles.properties` sotto il path `$JBOSS_HOME/standalone/configuration`
- Aprire il file `cli.sh`
- Impostare la variabile `JBOSS_HOME`
- Lanciare lo script `cli.sh`
## Utilizzo
Dalla `JBOSS_HOME` lanciare il comando
```bash
./bin/standalone.sh -c standalone-full.xml
```
## Test
```bash
mvn clean test
```