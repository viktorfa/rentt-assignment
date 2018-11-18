# Vue Kotlin Demo App

I made a simple employee management CRUD app with a single view of a list. It's very basic, but I feel I got the main UI things to work decently. Should have some validation of input and confirmation when deleting I guess.

The server was pretty much copied from a Javalin tutorial, and the client was made ground up from vue-cli create app. I feel the client got to the point that it needed better state management with some Flux-like library. I didn't actually implement the list as a table like in the wireframe image in the assignment pdf, as I just glanced on it. I tried to make it mobile friendly, so using a table would be difficult anyway.


Client expects server to be running on localhost:7000. Start server by running `Main.kt`, and start client with `yarn serve`.