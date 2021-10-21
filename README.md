# RMI-Practice

## To Compile

```
javac -d . Program/*.java

```
## To Run

```
rmiregistry
java -Djava.security.policy=server.policy -Djava.rmi.server.codebase=file:/RMI-Practice/ my.subasta.SubastaModelo
java -Djava.security.policy=server.policy -Djava.rmi.server.codebase=file:/RMI-Practice/ my.subasta.Principal
```
