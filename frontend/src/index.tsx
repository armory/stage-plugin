import * as React from 'react';
import { IPluginInitialize, IStageRegistry } from '@spinnaker/plugins';

class LikeButton extends React.Component {
  render() {
    return ( <div>Hello world, Hello Spinnaker</div> );
  }
}

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

let init: IPluginInitialize = initialize;
const plugin = {
  initialize: init,
};

window.spinnakerSettings.onPluginLoaded(plugin);
