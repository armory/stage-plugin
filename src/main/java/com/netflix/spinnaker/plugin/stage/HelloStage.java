/*
 * Copyright 2016 Netflix, Inc.
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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netflix.spinnaker.orca.pipeline.StageDefinitionBuilder;
import com.netflix.spinnaker.orca.pipeline.TaskNode;
import com.netflix.spinnaker.orca.pipeline.model.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

import javax.annotation.PostConstruct;

@Component
public class HelloStage implements StageDefinitionBuilder {


  private static final Logger log = LoggerFactory.getLogger(HelloStage.class);

  @Value("${plugins.armory/mysuperduperplugin.superserver.url}")
  public String TEST_VALUE;

  @PostConstruct
  public void init() {
    log.error(TEST_VALUE);
  }

  public static String STAGE_TYPE = "hello";

  @Override
  public void taskGraph(Stage stage, TaskNode.Builder builder) {
    // Task name can be changed per stage,
    builder.withTask("hello", HelloTask.class);
  }

  public static final class HelloStageContext {
    private final String yourName;

    @JsonCreator
    public HelloStageContext(
      @JsonProperty("yourName") @Nullable String yourName
    ) {
      this.yourName = yourName;
    }

    public @Nullable String getYourName() {
      return yourName;
    }
  }
}
