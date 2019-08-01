/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.plugin.stage;

import com.netflix.spinnaker.orca.ExecutionStatus;
import com.netflix.spinnaker.orca.Task;
import com.netflix.spinnaker.orca.TaskResult;
import com.netflix.spinnaker.orca.pipeline.model.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@Component
public class HelloTask implements Task {

  private final String yourName;
  public static String TASK_NAME = "yourName";


  @Autowired
  public HelloTask(String yourName) {
    this.yourName = yourName;
  }

  /**
   * This code executes when the task is ran. This is a required method.
   * @param stage
   * @return
   */
  @Override
  public @Nonnull
  TaskResult execute(@Nonnull Stage stage) {

    Map<String, String> outputs = new HashMap<>();
    outputs.put("yourName", yourName);
    HelloStage.HelloStageContext context = stage.mapTo(HelloStage.HelloStageContext.class);

    if (context.getYourName() == null) {
      return TaskResult.ofStatus(ExecutionStatus.TERMINAL);
    }

    return TaskResult.builder(ExecutionStatus.SUCCEEDED).context(outputs).build();
  }

}
