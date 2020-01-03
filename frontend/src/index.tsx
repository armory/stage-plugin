import * as React from 'react';
import { IStageTypeConfig, IStageConfigProps } from '@spinnaker/core';

const customStage: IStageTypeConfig = {
  label: 'Random Wait',
  description: 'Stage that waits a random amount of time up to the max inputted',
  key: 'randomWait',
  component: RandomWaitStage,
};

function setMaxWaitTime(event: React.SyntheticEvent, props: IStageConfigProps) {
  let target = event.target as HTMLInputElement;
  // @ts-ignore
  props.updateStageField({'maxWaitTime': target.value});
}

// Our stage component
function RandomWaitStage(props: IStageConfigProps) {
  console.error(props);
  return (
    <div>
      <label>
          Max Time To Wait
          <input onChange={(e) => setMaxWaitTime(e, props)} id="maxWaitTime" />
      </label>
    </div>
  );
}

const plugin = {
  name: 'randomWait',
  stages: [customStage],
};

export { plugin };
