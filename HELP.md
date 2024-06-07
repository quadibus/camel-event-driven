# Getting Started

## context

Here, we are creating a simple application with : 
- a panier service that is able to create/modify a panier by adding article to it
- a catalogue that is able to calculate total cost of panier
- an expedition that is able to calculate total weight of panier.

Aim is to start event driven with one runtime, and with help of camel.

## the move_to_kafka branch
This branch aim is to demonstrate the help of camel when migrate from no broker architecture to "with kafka" with the help of camel.
As we can see, the impact is minimal in code.

to start kafka, please use the docker compose.
