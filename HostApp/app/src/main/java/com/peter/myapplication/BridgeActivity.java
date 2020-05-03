package com.peter.myapplication;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

/**flu
 * @author peter
 */
public class BridgeActivity extends FlutterActivity {
    private static final String CHANNEL_NAME = "flutter.bridge.call_platform";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        getFlutterEngine();
        //config a method channel
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL_NAME)
                .setMethodCallHandler((call, result) -> {
                    switch (call.method) {
                        case "testMethod":
                            result.success("This is respond result from Android native method");
                            break;
                        default:
                            result.notImplemented();
                            break;
                    }
                });
    }
}
