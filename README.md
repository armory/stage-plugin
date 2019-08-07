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
- jars
  - list of jars required for the plugin

