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

import com.netflix.spinnaker.orca.api.Stage;
import com.netflix.spinnaker.orca.api.StageInput;
import com.netflix.spinnaker.orca.api.StageOutput;
import com.netflix.spinnaker.orca.api.StageStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Example stage that implements the Orca API Stage interface. By implementing Stage,
 * your stage is available for use in Spinnaker.
 */
@Slf4j
@Component
public class HelloStage implements Stage<HelloDataModel> {

  @Value("${plugins.armory/mysuperduperplugin.superserver.url}")
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
    return "helloStage";
  }

  /**
   * This is what gets ran when the stage is executed. It takes in an object that you create. That
   * object contains fields that one wishes to pull out of the pipeline context. This gives us a
   * strongly typed object that you have full control over. The function returns a StageOutput object.
   * The StageOutput class contains the status of the stage and any stage outputs that should be
   * put back into the pipeline context.
   * @param stageInput<HelloDataModel>
   * @return the status of the stage and any context that should be passed to the pipeline context
   */
  @Override
  public <HelloDataModel>StageOutput execute(StageInput<HelloDataModel> stageInput) {
    StageOutput output = new StageOutput();
    Map<String, String> data = new HashMap<>();

    data.put("hello", "world");
    output.setOutputs(data);
    output.setStatus(StageStatus.COMPLETED);

    return output;
  }
}
