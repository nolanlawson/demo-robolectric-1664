package com.nolanlawson.robolectric.foo;

import android.app.Activity;
import android.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 21)
public class BasicTest {

  @Test
  public void testBasic() throws Exception {
    assertTrue(Robolectric.buildActivity(MainActivity.class).create().get() != null);
  }

  @Test
  public void testBasic2() throws Exception {
    Activity activity = Robolectric.setupActivity(MainActivity.class);

    Robolectric.getForegroundThreadScheduler().advanceBy(5000);
  }
}
