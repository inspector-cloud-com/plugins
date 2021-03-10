// Copyright 2019 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package io.flutter.plugins.camera.features.exposurelock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import android.hardware.camera2.CaptureRequest;
import io.flutter.plugins.camera.CameraProperties;
import org.junit.Test;

public class ExposureLockFeatureTest {
  @Test
  public void getDebugName_should_return_the_name_of_the_feature() {
    CameraProperties mockCameraProperties = mock(CameraProperties.class);
    ExposureLockFeature exposureLockFeature = new ExposureLockFeature(mockCameraProperties);

    assertEquals("ExposureLockFeature", exposureLockFeature.getDebugName());
  }

  @Test
  public void getValue_should_return_auto_if_not_set() {
    CameraProperties mockCameraProperties = mock(CameraProperties.class);
    ExposureLockFeature exposureLockFeature = new ExposureLockFeature(mockCameraProperties);

    assertEquals(ExposureMode.auto, exposureLockFeature.getValue());
  }

  @Test
  public void getValue_should_echo_the_set_value() {
    CameraProperties mockCameraProperties = mock(CameraProperties.class);
    ExposureLockFeature exposureLockFeature = new ExposureLockFeature(mockCameraProperties);
    ExposureMode expectedValue = ExposureMode.locked;

    exposureLockFeature.setValue(expectedValue);
    ExposureMode actualValue = exposureLockFeature.getValue();

    assertEquals(expectedValue, actualValue);
  }

  @Test
  public void checkIsSupported_should_return_true() {
    CameraProperties mockCameraProperties = mock(CameraProperties.class);
    ExposureLockFeature exposureLockFeature = new ExposureLockFeature(mockCameraProperties);

    assertTrue(exposureLockFeature.checkIsSupported());
  }

  @Test
  public void
      updateBuilder_should_set_control_ae_lock_to_false_when_auto_exposure_is_set_to_auto() {
    CameraProperties mockCameraProperties = mock(CameraProperties.class);
    CaptureRequest.Builder mockBuilder = mock(CaptureRequest.Builder.class);
    ExposureLockFeature exposureLockFeature = new ExposureLockFeature(mockCameraProperties);

    exposureLockFeature.setValue(ExposureMode.auto);
    exposureLockFeature.updateBuilder(mockBuilder);

    verify(mockBuilder, times(1)).set(CaptureRequest.CONTROL_AE_LOCK, false);
  }

  @Test
  public void
      updateBuilder_should_set_control_ae_lock_to_false_when_auto_exposure_is_set_to_locked() {
    CameraProperties mockCameraProperties = mock(CameraProperties.class);
    CaptureRequest.Builder mockBuilder = mock(CaptureRequest.Builder.class);
    ExposureLockFeature exposureLockFeature = new ExposureLockFeature(mockCameraProperties);

    exposureLockFeature.setValue(ExposureMode.locked);
    exposureLockFeature.updateBuilder(mockBuilder);

    verify(mockBuilder, times(1)).set(CaptureRequest.CONTROL_AE_LOCK, true);
  }
}
