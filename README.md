## Objective

This repo contains an example stage plugin. Orca allows for plugins, so 
this repo will show how to add a stage by implementing two interfaces.


## Packaging

Plugins should contain a manifest file, similar to the one present here. 
Recommended fields include:
- name
- description
- version
- manifestVersion
- options
  - plugin configuration options
- resources
  - list of resources needed for each Spinnaker service

## Building
### The Backend
`./gradlew clean build`

This will build a jar file that lives in `./build/libs` that will be the plugin jar that gets passed into Orca

### The Frontend
`yarn && yarn run build`

This will build a compiled javascript bundle that lives in `./dist/index.js`.
