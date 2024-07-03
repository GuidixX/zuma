/*
 * Copyright (C) 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.test;

import static org.junit.Assert.assertEquals;

import android.platform.test.annotations.AppModeFull;

import com.android.tradefed.testtype.DeviceJUnit4ClassRunner;
import com.android.tradefed.testtype.junit4.BaseHostJUnit4Test;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DeviceJUnit4ClassRunner.class)
public class Boot16KbTest extends BaseHostJUnit4Test {
    @Test
    @AppModeFull
    public void check16kbPageSize() throws Exception {

        getDevice().enableAdbRoot();

        String result = getDevice().executeShellCommand("getconf PAGE_SIZE");
        assertEquals("16384", result.strip());

        // Developer option shouldn't be enabled on 16kb targets
        result = getDevice().getProperty("ro.product.build.16k_page.enabled");
        assertEquals("false", result.strip());
    }
}
