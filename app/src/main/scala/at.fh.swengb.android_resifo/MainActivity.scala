package at.fh.swengb.android_resifo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

/**
  * This Application helps entering data for a residence registration form.
  * The application consists of different screens which make it very easy to input the data.
  */

class MainActivity extends AppCompatActivity {

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}
