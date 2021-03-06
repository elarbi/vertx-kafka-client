/*
 * Copyright 2016 Red Hat Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.vertx.kafka.client.tests;

import io.debezium.kafka.KafkaCluster;
import io.debezium.util.Testing;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * Base class for tests providing a Kafka cluster
 */
@RunWith(VertxUnitRunner.class)
public class KafkaClusterTestBase extends KafkaTestBase {

  private File dataDir;
  private KafkaCluster kafkaCluster;

  protected KafkaCluster kafkaCluster() {
    if (kafkaCluster != null) {
      throw new IllegalStateException();
    }
    dataDir = Testing.Files.createTestingDirectory("cluster");
    kafkaCluster = new KafkaCluster().usingDirectory(dataDir).withPorts(2181, 9092);
    return kafkaCluster;
  }

  @After
  public void afterTest(TestContext ctx) {
    if (kafkaCluster != null) {
      kafkaCluster.shutdown();
      kafkaCluster = null;
      dataDir.delete();
    }
  }
}
