package com.nolanlawson.robolectric.foo;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends FragmentActivity implements
    LoaderManager.LoaderCallbacks<Cursor> {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getSupportLoaderManager().initLoader(0, null, this);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};

    return new CursorLoader(
        this,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        projection,
        null,
        null,
        MediaStore.Images.Media._ID + " DESC"
    );
  }

  @Override
  public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
    if(loader.getId() == 0) {
      String message = "thumbnail load finished. Count: " + cursor.getCount();
      Log.d("foo", message);
      ((TextView)findViewById(android.R.id.text1)).setText(message);

    }
  }

  @Override
  public void onLoaderReset(Loader<Cursor> loader) {
  }
}
