# apache camel monolith-first event driven

the purpose here is to demonstrate how camel can help us building event driven architecture by starting with a monolith and decoupling 
service with event and bus. Camel here is to help us connection to a message broker in the future.
The main branch show how use pure camel to decoupling services.
The move-to-kafka branch, show a first step inserting kafka and showing impact on code. 

## context

Here, we are creating a simple application with :
- a panier service that is able to create/modify a panier by adding article to it
- a catalogue that is able to calculate total cost of panier
- an expedition that is able to calculate total weight of panier.

Aim is to start event driven with one runtime, and with help of camel. -> main branch

## the move_to_kafka branch
This branch aim is to demonstrate the help of camel when migrate from no broker architecture to "with kafka" with the help of camel.
As we can see, the impact is minimal in code.

to start kafka, please use the docker compose.
