package at.fh.swengb.android_resifo

import android.app.Activity
import android.os.Bundle
import android.view.View

/**
  * Created by Martin on 19.01.2017.
  */
class FremdeActivity extends Activity{

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.reisedokument_fremde)
  }

  def goBack(view:View): Unit ={
    finish()
  }

}
