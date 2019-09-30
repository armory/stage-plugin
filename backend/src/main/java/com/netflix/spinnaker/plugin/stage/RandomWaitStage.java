/*
 * Copyright 2019 Armory, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.plugin.stage;

import com.netflix.spinnaker.orca.api.SimpleStage;
import com.netflix.spinnaker.orca.api.SimpleStageInput;
import com.netflix.spinnaker.orca.api.SimpleStageOutput;
import com.netflix.spinnaker.orca.api.SimpleStageStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;

/**
 * Example stage that implements the Orca API Stage interface. By implementing Stage,
 * your stage is available for use in Spinnaker.
 */
@Slf4j
@Component
public class RandomWaitStage implements SimpleStage<RandomWaitInput> {

  @Value("${plugins.armory/mysuperduperplugin.example.url}")
  /**
   * Service configuration values are accessible by the plugin
   */
  public String TEST_VALUE;

  /**
   * This method is not required. For this example, this method is used to show service configuration
   * values are available during startup.
   */
  @PostConstruct
  public void init() {
    log.error(TEST_VALUE);
  }

  /**
   * This sets the name of the stage
   * @return the name of the stage
   */
  @Override
  public String getName() {
    return "randomWait";
  }

  /**
   * This is what gets ran when the stage is executed. It takes in an object that you create. That
   * object contains fields that one wishes to pull out of the pipeline context. This gives us a
   * strongly typed object that you have full control over. The function returns a SimpleStageOutput object.
   * The SimpleStageOutput class contains the status of the stage and any stage outputs that should be
   * put back into the pipeline context.
   * @param stageInput<RandomWaitInput>
   * @return the status of the stage and any context that should be passed to the pipeline context
   */
  @Override
  public SimpleStageOutput execute(SimpleStageInput<RandomWaitInput> stageInput) {
    Random rand = new Random();
    int maxWaitTime = stageInput.getValue().getMaxWaitTime();
    int timeToWait = rand.nextInt(maxWaitTime);

    try {
      TimeUnit.SECONDS.sleep(timeToWait);
    } catch(Exception e) {
      log.error("{}", e);
    }

    SimpleStageOutput<Output, Context> stageOutput = new SimpleStageOutput();
    Output output = new Output(timeToWait);
    Context context = new Context(maxWaitTime);

    stageOutput.setOutput(output);
    stageOutput.setContext(context);
    stageOutput.setStatus(SimpleStageStatus.SUCCEEDED);

    return stageOutput;
  }
}
