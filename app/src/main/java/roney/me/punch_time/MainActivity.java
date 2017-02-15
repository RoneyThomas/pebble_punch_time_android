package roney.me.punch_time;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private PebbleKit.PebbleDataReceiver dataReceiver;
    private UUID appUuid = UUID.fromString("1a7a6bb5-39ee-44ae-9887-c4ca3957338d");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dataReceiver==null) {
            // Create a new receiver to get AppMessages from the C app
            dataReceiver = new PebbleKit.PebbleDataReceiver(appUuid) {

                @Override
                public void receiveData(Context context, int transaction_id,
                                        PebbleDictionary dict) {
                    // A new AppMessage was received, tell Pebble
                    PebbleKit.sendAckToPebble(context, transaction_id);
                    Log.d(TAG, "receiveData: "+dict.getString(0)+ "  "+dict.getString(1)+"  "+dict.getString(2)+ "  "+dict.getString(3)+"  "+dict.getString(4));
                }

            };
        }
        // Register the receiver
        PebbleKit.registerReceivedDataHandler(getApplicationContext(), dataReceiver);
    }
}
