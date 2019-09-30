import * as React from 'react';
// IPluginInitialize is function interface
// that takes in the IStageRegistry interface.
// The IStageRegistry is used to register the stage.
import { IPluginInitialize, IStageRegistry } from '@spinnaker/plugins';

// Our stage component
class RandomWaitStage extends React.Component {
  setMaxWaitTime = (event: React.SyntheticEvent) => {
    let target = event.target as HTMLInputElement;
    // @ts-ignore
    this.props.updateStageField({'maxWaitTime': target.value});
  }

  render() {
    return (
      <div>
        <label>
            Max Time To Wait
            <input onChange={this.setMaxWaitTime} id="maxWaitTime" />
        </label>
      </div>
    );
  }
}

// This function implements the IPluginInitialize interface
// This is where the stage gets registered.
function initialize(registry: IStageRegistry): void {
  registry.pipeline.registerStage({
    label: 'Random Wait',
    description: 'Stage that waits a random amount of time up to the max inputted',
    key: 'randomWait',
    component: RandomWaitStage,
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
