import * as React from 'react';
// IPluginInitialize is function interface
// that takes in the IStageRegistry interface.
// The IStageRegistry is used to register the stage.
import { IPluginInitialize, IStageRegistry } from '@spinnaker/plugins';

// Our stage component
class LikeButton extends React.Component {
  render() {
    return ( <div>Hello world, Hello Spinnaker</div> );
  }
}

// This function implements the IPluginInitialize interface
// This is where the stage gets registered.
function initialize(registry: IStageRegistry): void {
  registry.pipeline.registerStage({
    label: 'My Stage',
    description: 'My Stage',
    key: 'myStage',
    alias: 'myStage',
    addAliasToConfig: true,
    cloudProvider: 'kubernetes',
    component: LikeButton,
    supportsCustomTimeout: true,
    producesArtifacts: true,
  });
};

// Make the initialize function be the interface
let init: IPluginInitialize = initialize;
const plugin = {
  initialize: init,
};

// Call spinnaker settings to actually load the stage
// plugin for us
window.spinnakerSettings.onPluginLoaded(plugin);
