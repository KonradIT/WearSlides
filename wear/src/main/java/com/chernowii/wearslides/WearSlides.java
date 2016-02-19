package com.chernowii.wearslides;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

public class WearSlides extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    private static final String next = "/next";
    private static final String prev = "/prev";
    Node wearNode;
    GoogleApiClient wearGoogleApiClient;
    private boolean mResolvingError=false;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wear_slides);
        wearGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                ImageButton setOn = (ImageButton) findViewById(R.id.prev);
                setOn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendPrev();
                    }
                });
                ImageButton setOff = (ImageButton) findViewById(R.id.next);
                setOff.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendNext();
                    }
                });
            }
        });
    }
    private void sendPrev() {
        if (wearNode != null && wearGoogleApiClient!=null && wearGoogleApiClient.isConnected()) {
            Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(200);
            Wearable.MessageApi.sendMessage(
                    wearGoogleApiClient, wearNode.getId(), prev, null).setResultCallback(

                    new ResultCallback<MessageApi.SendMessageResult>() {
                        @Override
                        public void onResult(MessageApi.SendMessageResult sendMessageResult) {

                            if (!sendMessageResult.getStatus().isSuccess()) {
                                Log.e("TAG", "Failed to send message with status code: "
                                        + sendMessageResult.getStatus().getStatusCode());
                            }
                        }
                    }
            );
        }else{
            Toast.makeText(getApplicationContext(),
                    "No connection to phone", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),
                    "Connect watch to phone!", Toast.LENGTH_SHORT).show();

        }

    }
    private void sendNext() {
        if (wearNode != null && wearGoogleApiClient!=null && wearGoogleApiClient.isConnected()) {
            Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(200);
            Wearable.MessageApi.sendMessage(
                    wearGoogleApiClient, wearNode.getId(), next, null).setResultCallback(

                    new ResultCallback<MessageApi.SendMessageResult>() {
                        @Override
                        public void onResult(MessageApi.SendMessageResult sendMessageResult) {

                            if (!sendMessageResult.getStatus().isSuccess()) {
                                Log.e("TAG", "Failed to send message with status code: "
                                        + sendMessageResult.getStatus().getStatusCode());
                            }
                        }
                    }
            );
        }else{
            Toast.makeText(getApplicationContext(),
                    "No connection to phone", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),
                    "Connect watch to phone!", Toast.LENGTH_SHORT).show();

        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (!mResolvingError) {
            wearGoogleApiClient.connect();
        }
    }

    /*
    * Resolve the node = the connected device to send the message to
    */
    private void resolveNode() {

        Wearable.NodeApi.getConnectedNodes(wearGoogleApiClient).setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
            @Override
            public void onResult(NodeApi.GetConnectedNodesResult nodes) {
                for (Node node : nodes.getNodes()) {
                    wearNode = node;
                }
            }
        });
    }


    @Override
    public void onConnected(Bundle bundle) {
        resolveNode();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(getApplicationContext(),                     "Error, not connected to phone!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(),                     "Error, not connected to phone!", Toast.LENGTH_SHORT).show();
    }
}
