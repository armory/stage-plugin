## Objective
This repo contains an example stage plugin. Orca allows for plugins, so 
this repo will show how to add a stage by implementing two interfaces.


## Plugin Manifest
[TODO]

## Building
To build both the front end and back end run `./gradlew clean build`.

### The Backend
Check out the README in the backend project.

### The Frontend
`yarn && yarn run build`

This will build a compiled javascript bundle that lives in `./dist/index.js`.

## Packaging Everything
To package up both the front and back ends together run `./gradle distZip`. This will create a zip file in `./build/distributions/` that will contain the zip for the back end code along with the compiled Javascript for the front end. This command has to be ran after compiling happens. It can be appended like so `./gradlew clean build distZip`.
