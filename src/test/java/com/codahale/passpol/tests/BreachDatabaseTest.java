/*
 * Copyright © 2018 Coda Hale (coda.hale@gmail.com)
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
package com.codahale.passpol.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.codahale.passpol.BreachDatabase;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class BreachDatabaseTest {

  @Test
  void haveIBeenPwned() throws IOException {
    assertThat(BreachDatabase.haveIBeenPwned().contains("password")).isTrue();
    assertThat(BreachDatabase.haveIBeenPwned().contains("8e29c409899d7782ef97")).isFalse();
  }

  @Test
  void top100K() throws IOException {
    assertThat(BreachDatabase.top100K().contains("password")).isTrue();
    assertThat(BreachDatabase.top100K().contains("8e29c409899d7782ef97")).isFalse();
  }

  @Test
  void allOf() throws IOException {
    final BreachDatabase db = BreachDatabase.allOf("funk"::equals, "soul"::equals);
    assertThat(db.contains("funk")).isTrue();
    assertThat(db.contains("soul")).isTrue();
    assertThat(db.contains("brother")).isFalse();
  }
}
